package com.example.githubassessment.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubassessment.dto.UserDataModeldto
import com.example.githubassessment.dto.UserFollowersModelItem
import com.example.githubassessment.repository.RESTRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowUserViewModel @Inject constructor(private val repository : RESTRepository, savedStateHandle: SavedStateHandle) : ViewModel(){
    val user : StateFlow<UserDataModeldto>
        get() = repository.user

    init {
        viewModelScope.launch {
            val username = savedStateHandle.get<String>("username") ?: ""
            repository.getUser(username)
        }
    }
}