package com.example.githubassessment.repository

import com.example.githubassessment.api.RESTapi
import com.example.githubassessment.dto.UserDataModeldto
import com.example.githubassessment.dto.UserFollowersModelItem
import com.example.githubassessment.dto.UserFollowingModelItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RESTRepository @Inject constructor(private val resTapi: RESTapi) {

    private val _user = MutableStateFlow<UserDataModeldto>(UserDataModeldto("","","","","","","",0,"",0,"","","","","",0,"","","","","",0,0,"","",false,"","","","","",""))
    val user : StateFlow<UserDataModeldto>
        get() = _user




    private val _followers = MutableStateFlow<List<UserFollowersModelItem>>(emptyList())
    val followers : StateFlow<List<UserFollowersModelItem>>
        get() = _followers

    private val _following = MutableStateFlow<List<UserFollowingModelItem>>(emptyList())
    val following : StateFlow<List<UserFollowingModelItem>>
        get() = _following

    suspend fun getUser(username : String){
        val response =resTapi.getUser(username)
        if (response.isSuccessful && response.body()!=null){
            _user.emit(response.body()!!)
        }
    }

    suspend fun getFollowers(username: String){
        val response = resTapi.getUserFollowers(username)
        if (response.isSuccessful && response.body()!=null){
            _followers.emit(response.body()!!)
        }
    }


    suspend fun getFollowing(username: String){
        val response = resTapi.getUserFollowing(username)
        if (response.isSuccessful && response.body()!=null){
            _following.emit(response.body()!!)
        }
    }
}