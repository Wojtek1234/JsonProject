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
public abstract class GSONparser<T> {



    public List<T> parseGitHub(InputStream content) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<T>>() {
        }.getType();
        Reader jsonReader = new InputStreamReader(content);
        return gson.fromJson(jsonReader, collectionType);
    }

}
