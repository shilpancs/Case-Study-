package com.target.casestudy.target.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
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
import com.target.casestudy.target.ui.theme.GRAY8888
import com.target.casestudy.target.ui.theme.Gray3333
import com.target.casestudy.target.ui.theme.Gray6666
import com.target.casestudy.target.ui.theme.GrayD3D3
import com.target.casestudy.target.ui.theme.GrayEAEAEA
import com.target.casestudy.target.ui.theme.Red
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DealsDetailPage(id: String, viewModel: DealsViewModel, navHostController: NavHostController) {

    val productData = viewModel.product_deal?.observeAsState()

    LaunchedEffect(key1 = Unit) {
        withContext(Dispatchers.Default) {
            viewModel.fetchProductDeal(id.toInt())
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.White,
                title = {
                    androidx.compose.material3.Text(
                        text = "Details",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Left,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Red
                        )
                    }
                },
            )
        }
    ) {
        productData?.let {
            Column()
            {
                Column(modifier = Modifier.padding(20.dp)) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        elevation = 2.dp,
                        backgroundColor = Color.White
                    ) {
                        productData?.value?.image_url?.let {
                            Image(
                                painter = rememberAsyncImagePainter(it),
                                contentDescription = null,
                                modifier = Modifier.size(280.dp),
                                contentScale = ContentScale.Fit,
                            )
                        }
                    }
                    productData?.value?.title?.let { str ->
                        Text(
                            text = str,
                            fontSize = 18.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 15.dp),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Row(modifier = Modifier.padding(top = 20.dp)) {
                        productData?.value?.sale_price?.display_string?.let { str ->
                            Text(
                                text = str,
                                fontSize = 20.sp,
                                color = Red,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                        productData?.value?.regular_price?.display_string?.let { str ->
                            Text(
                                text = "reg. $str",
                                Modifier.padding(top = 5.dp),
                                style = MaterialTheme.typography.titleMedium,
                                fontSize = 15.sp,
                                color = Gray3333,
                            )
                        }
                    }
                    productData?.value?.fulfillment?.let { type ->
                        Text(
                            text = type,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(top = 5.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Gray6666
                        )
                    }
                }
                Divider(thickness = 1.dp, color = GrayEAEAEA)
                HorizontalDivider(thickness = 15.dp, color = GrayD3D3)
                Divider(thickness = 1.dp, color = GrayEAEAEA)
                Column(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
                        .height(200.dp)
                ) {
                    Text(
                        text = "Product details",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    productData?.value?.description?.let {
                        Text(
                            text = it,
                            fontSize = 16.sp,
                            color = GRAY8888,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 10.dp)
                                .verticalScroll(rememberScrollState())
                        )
                    }
                }
                Divider(thickness = 1.dp, color = GrayEAEAEA)
                Button(
                    onClick = {},
                    modifier = Modifier.padding(
                        top = 5.dp,
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 5.dp
                    ).fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Red),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(
                        text = "Add to Cart",
                        fontSize = 18.sp,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}