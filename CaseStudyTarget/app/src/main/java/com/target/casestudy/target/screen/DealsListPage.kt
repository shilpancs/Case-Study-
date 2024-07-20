package com.target.casestudy.target.screen


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.target.casestudy.target.DealsViewModel
import com.target.casestudy.target.ui.theme.GREEN
import com.target.casestudy.target.ui.theme.Gray6666
import com.target.casestudy.target.ui.theme.Gray3333
import com.target.casestudy.target.ui.theme.Red
import com.target.casestudy.target.util.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DealsListPage(viewModel: DealsViewModel, navHostController: NavHostController) {

    val deals = viewModel.deals.observeAsState()

    LaunchedEffect(key1 = Unit) {
        withContext(Dispatchers.Default) {
            viewModel.fetchData()
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.White,
                title = {
                    androidx.compose.material3.Text(
                        text = "List",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp)
        ) {
            deals.value?.let {
                items(it.products) {
                    Card(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clickable(onClick = {
                                navHostController.navigate(Screen.DetailPage.sendId(it.id.toString()))
                            }),
                        shape = MaterialTheme.shapes.medium,
                        elevation = 2.dp,
                        backgroundColor = Color.White
                    ) {
                        Row(
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(it.image_url),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(140.dp)
                                    .padding(8.dp),
                                contentScale = ContentScale.Fit,
                            )
                            Column(Modifier.padding(20.dp)) {
                                Row() {
                                    it.sale_price?.display_string?.let { str ->
                                        Text (
                                            text = str,
                                            fontSize = 20.sp,
                                            color = Red,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                    }
                                    it.regular_price?.display_string?.let { str ->
                                        Text (
                                            text = "reg. $str",
                                            Modifier.padding(top = 5.dp),
                                            style = MaterialTheme.typography.titleMedium,
                                            fontSize = 15.sp,
                                            color = Gray3333,
                                        )
                                    }
                                }

                                it.fulfillment?.let { type ->
                                    Text(
                                        text = type,
                                        fontSize = 15.sp,
                                        modifier = Modifier.padding(top = 5.dp),
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Gray6666
                                    )
                                }


                                Text(
                                    text = it.title,
                                    modifier = Modifier.padding(top = 10.dp),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black
                                )
                                Row(modifier = Modifier.padding(top = 8.dp)) {
                                    it.availability?.let { avail ->
                                        Text (
                                            text = avail,
                                            fontSize = 12.sp,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = GREEN,
                                        )
                                        Spacer(modifier = Modifier.width(5.dp))
                                    }
                                    it.aisle?.let { value ->
                                        Text (
                                            text = "in aisle $value",
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontSize = 12.sp,
                                            color = Gray6666,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}