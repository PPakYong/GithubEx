package com.yhpark.githubex.api;

import com.yhpark.githubex.model.UserResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by YongHyeon on 2017-04-05.
 */

public interface GitHubUsers {
    @GET("/users/{username}")
    Call<UserResult> getSingleUser(@Path(value = "username", encoded = true) String username);

    @GET("/user")
    Call<UserResult> getAuthenticatedUser();

    @PATCH("/user")
    Call<UserResult> updateAuthenticatedUser(
            @Query("name") String name,
            @Query("email") String email,
            @Query("blog") String blog,
            @Query("company") String company,
            @Query("location") String location,
            @Query("hireable") boolean hireable,
            @Query("bio") String bio
    );

    @GET("/users")
    Call<List<UserResult>> getAllUsers(
            @Query("since") String since,
            @Query("page") int page,
            @Query("per_page") int per_page);
}
