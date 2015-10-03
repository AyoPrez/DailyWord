package com.ayoprez.restfulservice;

import com.ayoprez.login.User;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by AyoPrez on 8/08/15.
 */
public interface UserAPI {
    @GET("/User/{Social_Id}/{Type_Id}")
    void getUserLogin(@Path("Social_Id") String social_id,
                      @Path("Type_Id") String type_id,
                      Callback<User> response);

    @FormUrlEncoded
    @POST("/User_quotes")
    void postUserQuote(@Field("user_id") int userId,
                      @Field("quote_id") int quoteId,
                      Callback<Integer> response);

}
