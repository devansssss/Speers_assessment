package com.example.githubassessment.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.githubassessment.dto.UserDataModeldto
import com.example.githubassessment.viewmodels.UserViewModel

@Composable
fun HomeScreen(onFollowersClick : (username : String) -> Unit, onFollowingClick : (username : String) -> Unit) {
    val userViewModel : UserViewModel = hiltViewModel()
    val user : State<UserDataModeldto> = userViewModel.User.collectAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        SearchBar(userViewModel = userViewModel)
        Spacer(modifier = Modifier.height(8.dp))
        if (user.value.login.isNotEmpty()){
            UserItem(user = user, onFollowersClick, onFollowingClick)
        }
    }
}


@Composable
fun UserItem(user : State<UserDataModeldto>, onFollowersClick: (username: String) -> Unit, onFollowingClick: (username: String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0x51, 0x51, 0x51))
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(modifier = Modifier
                .size(width = 100.dp, height = 100.dp)
                .clip(
                    RoundedCornerShape(8.dp)
                ) ,model = user.value.avatar_url, contentDescription = null)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = if(user.value.login!=null) user.value.login else "Null", color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = if(user.value.name!=null) user.value.name else "Null", color = Color.White, style = MaterialTheme.typography.headlineSmall )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = if(user.value.bio!=null) user.value.bio else "Null", color = Color.LightGray, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                FollowDetailsItem( type = "Followers", username = user.value.login, value = user.value.followers.toString(), onFollowersClick)
                Spacer(modifier = Modifier.width(8.dp))
                FollowDetailsItem(type = "Following", username = user.value.login,value = user.value.following.toString(), onFollowingClick)
            }
        }
    }
}

@Composable
fun FollowDetailsItem( type : String, username: String,  value : String, onClick: (username: String) -> Unit) {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .border(width = 3.dp, color = Color.White)
        .clickable { onClick(username) }
        .padding(15.dp)){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = type, color = Color.White, style = MaterialTheme.typography.bodySmall)
            Text(text = value, color = Color.White, style = MaterialTheme.typography.bodySmall)
        }
    }
}




@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(userViewModel: UserViewModel) {
    var username by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Enter GitHub username") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                keyboardController?.hide()
                userViewModel.getUser(username)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }
    }

}