package pl.wojtek.jsonproject.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2014-11-09.
 */
public class GitHubUser {
    @SerializedName("login")
    private String name;

    @SerializedName("avatar_url")
    private String avatar_url;



    @SerializedName("repos_url")
    private String repos;

    public String getName() {
        return name;
    }

    public String getRepos() {
        return repos;
    }

    public void setRepos(String repos) {
        this.repos = repos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_url() {

        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }


}
