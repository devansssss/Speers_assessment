package com.example.githubassessment.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.githubassessment.dto.UserDataModeldto
import com.example.githubassessment.dto.UserFollowersModelItem
import com.example.githubassessment.viewmodels.FollowUserViewModel
import com.example.githubassessment.viewmodels.UserViewModel

@Composable
fun UserScreen() {
    val userViewModel : FollowUserViewModel = hiltViewModel()
    val user: State<UserDataModeldto> = userViewModel.user.collectAsState()
    if (user.value.login.isNotEmpty()){
        FollowedUserItem(user = user)
    }

}


@Composable
fun FollowedUserItem(
    user : State<UserDataModeldto>
) {
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
        }
    }
}