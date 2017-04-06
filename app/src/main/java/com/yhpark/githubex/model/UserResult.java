package com.yhpark.githubex.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by YongHyeon on 2017-04-06.
 */

public class UserResult implements Parcelable {
    @SerializedName("login")
    public String login;
    @SerializedName("id")
    public String id;
    @SerializedName("avatar_url")
    public String avatar_url;
    @SerializedName("gravatar_id")
    public String gravatar_id;
    @SerializedName("url")
    public String url;
    @SerializedName("html_url")
    public String html_url;
    @SerializedName("followers_url")
    public String followers_url;
    @SerializedName("following_url")
    public String following_url;
    @SerializedName("gists_url")
    public String gists_url;
    @SerializedName("starred_url")
    public String starred_url;
    @SerializedName("subscriptions_url")
    public String subscriptions_url;
    @SerializedName("organizations_url")
    public String organizations_url;
    @SerializedName("repos_url")
    public String repos_url;
    @SerializedName("events_url")
    public String events_url;
    @SerializedName("received_events_url")
    public String received_events_url;
    @SerializedName("type")
    public String type;
    @SerializedName("site_admin")
    public String site_admin;
    @SerializedName("name")
    public String name;
    @SerializedName("company")
    public String company;
    @SerializedName("blog")
    public String blog;
    @SerializedName("location")
    public String location;
    @SerializedName("email")
    public String email;
    @SerializedName("hireable")
    public String hireable;
    @SerializedName("bio")
    public String bio;
    @SerializedName("public_repos")
    public String public_repos;
    @SerializedName("public_gists")
    public String public_gists;
    @SerializedName("followers")
    public String followers;
    @SerializedName("following")
    public String following;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("updated_at")
    public String updated_at;

    protected UserResult(Parcel in) {
        login = in.readString();
        id = in.readString();
        avatar_url = in.readString();
        gravatar_id = in.readString();
        url = in.readString();
        html_url = in.readString();
        followers_url = in.readString();
        following_url = in.readString();
        gists_url = in.readString();
        starred_url = in.readString();
        subscriptions_url = in.readString();
        organizations_url = in.readString();
        repos_url = in.readString();
        events_url = in.readString();
        received_events_url = in.readString();
        type = in.readString();
        site_admin = in.readString();
        name = in.readString();
        company = in.readString();
        blog = in.readString();
        location = in.readString();
        email = in.readString();
        hireable = in.readString();
        bio = in.readString();
        public_repos = in.readString();
        public_gists = in.readString();
        followers = in.readString();
        following = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<UserResult> CREATOR = new Creator<UserResult>() {
        @Override
        public UserResult createFromParcel(Parcel in) {
            return new UserResult(in);
        }

        @Override
        public UserResult[] newArray(int size) {
            return new UserResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(id);
        dest.writeString(avatar_url);
        dest.writeString(gravatar_id);
        dest.writeString(url);
        dest.writeString(html_url);
        dest.writeString(followers_url);
        dest.writeString(following_url);
        dest.writeString(gists_url);
        dest.writeString(starred_url);
        dest.writeString(subscriptions_url);
        dest.writeString(organizations_url);
        dest.writeString(repos_url);
        dest.writeString(events_url);
        dest.writeString(received_events_url);
        dest.writeString(type);
        dest.writeString(site_admin);
        dest.writeString(name);
        dest.writeString(company);
        dest.writeString(blog);
        dest.writeString(location);
        dest.writeString(email);
        dest.writeString(hireable);
        dest.writeString(bio);
        dest.writeString(public_repos);
        dest.writeString(public_gists);
        dest.writeString(followers);
        dest.writeString(following);
        dest.writeString(created_at);
        dest.writeString(updated_at);
    }
}
