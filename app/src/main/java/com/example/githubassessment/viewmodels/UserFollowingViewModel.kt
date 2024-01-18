package com.example.githubassessment.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubassessment.dto.UserFollowersModelItem
import com.example.githubassessment.dto.UserFollowingModelItem
import com.example.githubassessment.repository.RESTRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFollowingViewModel @Inject constructor(private val repository : RESTRepository, private val savedStateHandle: SavedStateHandle) : ViewModel(){
    val userFollowing : StateFlow<List<UserFollowingModelItem>>
        get() = repository.following

    init {
        viewModelScope.launch {
            val username = savedStateHandle.get<String>("username") ?: ""
            repository.getFollowing(username)
        }
    }
}