package com.example.githubassessment.viewmodels

import android.service.autofill.UserData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubassessment.dto.UserDataModeldto
import com.example.githubassessment.repository.RESTRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository : RESTRepository) : ViewModel() {

    val User :StateFlow<UserDataModeldto>
        get() = repository.user

    fun getUser(username: String) {
        viewModelScope.launch {
            repository.getUser(username)
        }
    }
}