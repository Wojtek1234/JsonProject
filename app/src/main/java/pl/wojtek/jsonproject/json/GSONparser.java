package pl.wojtek.jsonproject.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by user on 2014-11-09.
 */
public class GSONparser<T> {


    private List<T> parseGitHubUserList(InputStream content) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<T>>(){}.getType();
        Reader jsonReader = new InputStreamReader(content);
        List<T> result = gson.fromJson(jsonReader, collectionType);
        return result;
    }

}
