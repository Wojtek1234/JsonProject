package pl.wojtek.jsonproject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
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

import pl.wojtek.jsonproject.json.GitHubRepo;

/**
 * Created by user on 2014-11-09.
 */
public class RepoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        doOnIntent(intent);
    }

    private void doOnIntent(Intent intent) {
        try{
            String url=intent.getExtras().getString(GsonActivity.REPO_URL);
            ApiRequestAsyncTask apiRequestAsyncTask=new ApiRequestAsyncTask(url);
            apiRequestAsyncTask.execute();
        }catch(NullPointerException ne){
            ne.printStackTrace();
        }
    }

    private List<GitHubRepo> parseGitHubRepoList(InputStream content) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<GitHubRepo>>() {
        }.getType();
        Reader jsonReader = new InputStreamReader(content);
        List<GitHubRepo> result = gson.fromJson(jsonReader, collectionType);
        return result;
    }

    private void createAdapterAndList(List<GitHubRepo> listaGithubow) {
        Log.d("Title","Tilte");
    }



    private class ApiRequestAsyncTask extends AsyncTask<Void, Void, Void> {

        String requestUrl;
        List<GitHubRepo> listaGithubow;

        private ApiRequestAsyncTask(String requestUrl) {
            this.requestUrl = requestUrl;
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            try {

                HttpResponse response = httpClient.execute(new HttpGet(requestUrl));
                InputStream stream = response.getEntity().getContent();
                listaGithubow=(List<GitHubRepo>) parseGitHubRepoList(stream);

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


}
