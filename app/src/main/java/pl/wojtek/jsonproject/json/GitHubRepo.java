package pl.wojtek.jsonproject.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2014-11-09.
 */
public class GitHubRepo {

    @SerializedName("owner")
    GitHubUser user;

    @SerializedName("String")
    String repoNamne;

    @SerializedName("url")
    String repoURL;

    


}
