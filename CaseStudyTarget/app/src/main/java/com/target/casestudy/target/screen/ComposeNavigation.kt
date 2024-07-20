package com.target.casestudy.target.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.target.casestudy.target.DealsViewModel
import com.target.casestudy.target.util.Screen

@Composable
fun ComposeNavigation(viewModel: DealsViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.ListPage.route){
        composable(Screen.ListPage.route){
            DealsListPage(viewModel, navController)
        }
        composable(Screen.DetailPage.route){
            val id = it.arguments?.getString("id") ?: "0"
            DealsDetailPage(id, viewModel, navController)
        }
    }
}