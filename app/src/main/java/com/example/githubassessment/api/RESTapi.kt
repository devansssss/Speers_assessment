package com.example.githubassessment.api

import com.example.githubassessment.dto.UserDataModeldto
import com.example.githubassessment.dto.UserFollowersModelItem
import com.example.githubassessment.dto.UserFollowingModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RESTapi {

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username : String): Response<UserDataModeldto>

    @GET("users/{username}/followers")
    suspend fun getUserFollowers(@Path("username") username : String): Response<List<UserFollowersModelItem>>

    @GET("users/{username}/following")
    suspend fun getUserFollowing(@Path("username") username: String): Response<List<UserFollowingModelItem>>
}