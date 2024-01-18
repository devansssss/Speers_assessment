package com.example.githubassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubassessment.screens.FollowersScreen
import com.example.githubassessment.screens.FollowingScreen
import com.example.githubassessment.screens.HomeScreen
import com.example.githubassessment.screens.UserScreen
import com.example.githubassessment.ui.theme.GithubAssessmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubAssessmentTheme {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x31, 0x31, 0x31))){
                    App()
                }
            }
        }
    }
}


@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable(route = "home"){
            HomeScreen(onFollowersClick = {username ->  navController.navigate("followers/$username")},
                        onFollowingClick = {username ->  navController.navigate("following/$username")})
        }

        composable(route = "followers/{username}", arguments = listOf(
            navArgument("username"){
                type = NavType.StringType
            }
        ))
        {
            FollowersScreen{
                navController.navigate("followeduser/$it")
            }
        }


        composable(route = "following/{username}", arguments = listOf(
            navArgument("username"){
                type = NavType.StringType
            }
        ))
        {
            FollowingScreen{
                navController.navigate("followeduser/$it")

            }
        }


        composable(route = "followeduser/{username}", arguments = listOf(
            navArgument("username"){
                type = NavType.StringType
            }
        ))
        {
            UserScreen()
        }
    }
}
