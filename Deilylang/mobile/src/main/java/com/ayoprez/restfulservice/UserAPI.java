package com.ayoprez.restfulservice;

import com.ayoprez.login.User;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by AyoPrez on 8/08/15.
 */
public interface UserAPI {

    @GET("/User/{Social_Id}")
    void getUserLogin(@Path("Social_Id") String social_id,
                           Callback<User> response);
}
