package pl.wojtek.jsonproject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;


public class GsonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        requestApi();
    }

    private void requestApi() {
        ApiRequestAsyncTask apiRequestAsyncTask = new ApiRequestAsyncTask();
        apiRequestAsyncTask.requestUrl = "https://api.github.com/users/dondimas/following";
        apiRequestAsyncTask.execute();

    }

    private void createAdapterAndList( List<GitHubUser> listaGithubow) {

        GitHubUserAdapter gitHubUserAdapter=new GitHubUserAdapter(this, R.layout.single_githubuser,listaGithubow);
        ListView listView=(ListView)findViewById(R.id.usersListView);
        listView.setAdapter(gitHubUserAdapter);
    }


    private class ApiRequestAsyncTask extends AsyncTask<Void, Void, Void> {

        String requestUrl;
        List<GitHubUser> listaGithubow;

        @Override
        protected Void doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            try {
                HttpResponse response = httpClient.execute(new HttpGet(requestUrl));
                InputStream stream = response.getEntity().getContent();
                listaGithubow=parseGitHubUserList(stream);

            } catch (IOException e) {
                e.printStackTrace();

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            createAdapterAndList(listaGithubow);
        }
    }

    private List<GitHubUser> parseGitHubUserList(InputStream content) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<GitHubUser>>() {
        }.getType();
        Reader jsonReader = new InputStreamReader(content);
        List<GitHubUser> result = gson.fromJson(jsonReader, collectionType);
        return result;
    }

    class GitHubUser {
        @SerializedName("login")
        String name;
        @SerializedName("avatar_url")
        String avatar_url;
    }

}
