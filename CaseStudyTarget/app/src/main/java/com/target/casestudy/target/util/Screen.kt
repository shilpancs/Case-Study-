package com.target.casestudy.target.util

sealed class Screen(val route: String) {

    data object ListPage : Screen("list_screen")

    data object DetailPage : Screen("{id}/detail_screen") {
        fun sendId(id: String) = "$id/detail_screen"
    }
}