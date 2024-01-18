package com.example.githubassessment.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubassessment.dto.UserFollowersModelItem
import com.example.githubassessment.dto.UserFollowingModelItem
import com.example.githubassessment.viewmodels.UserFollowersViewModel

@Composable
fun FollowersScreen(onClick : (username : String) -> Unit) {
    val userFollowersViewModel : UserFollowersViewModel = hiltViewModel()
    val followers: State<List<UserFollowersModelItem>> = userFollowersViewModel.userFollowers.collectAsState()
    LazyColumn(){
        items(followers.value){
            FollowersItem(it, onClick)
        }
    }
}


@Composable
fun FollowersItem(
    userFollowerItem : UserFollowersModelItem,
    onClick: (username: String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0x31, 0x31, 0x31))
            .clickable { onClick(userFollowerItem.login) }
            .padding(25.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(userFollowerItem.avatar_url).crossfade(true).build(), contentScale = ContentScale.Crop, contentDescription = null, modifier = Modifier.size(50.dp,50.dp).clip(
            CircleShape
        ))

        Text(text = "${userFollowerItem.login}",
            style = MaterialTheme.typography.bodySmall,
            overflow = TextOverflow.Ellipsis,
            color = Color.White
        )
    }
}