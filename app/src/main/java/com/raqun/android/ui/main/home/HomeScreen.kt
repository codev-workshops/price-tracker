package com.raqun.android.ui.main.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.raqun.android.data.DataBean
import com.raqun.android.data.DataState
import com.raqun.android.model.Product
import com.raqun.android.model.WebApp

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onProductClick: (Product) -> Unit,
    onMoreTopClick: () -> Unit,
    onMoreRecentClick: () -> Unit,
    onMoreDiscountClick: () -> Unit
) {
    val topProducts by viewModel.getTopFollowedProducts().observeAsState()
    val recentProducts by viewModel.getRecentFollowedProducts().observeAsState()
    val discountedProducts by viewModel.getDiscountedProducts().observeAsState()
    val topWebApps by viewModel.getTopWebApps().observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)
    ) {
        ProductSection(
            title = "Top Products",
            bean = topProducts,
            onProductClick = onProductClick,
            onMoreClick = onMoreTopClick
        )
        ProductSection(
            title = "Recent Products",
            bean = recentProducts,
            onProductClick = onProductClick,
            onMoreClick = onMoreRecentClick
        )
        ProductSection(
            title = "Discounted Products",
            bean = discountedProducts,
            onProductClick = onProductClick,
            onMoreClick = onMoreDiscountClick
        )
    }
}

@Composable
fun ProductSection(
    title: String,
    bean: DataBean<List<Product>>?,
    onProductClick: (Product) -> Unit,
    onMoreClick: () -> Unit
) {
    val state = bean?.getState()
    val products = bean?.getData()

    Column(modifier = Modifier.padding(top = 12.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            TextButton(onClick = onMoreClick) {
                Text("More")
            }
        }
        when {
            state == DataState.FETCHING -> {
                CircularProgressIndicator(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
            }
            products != null -> {
                LazyRow(contentPadding = PaddingValues(horizontal = 8.dp)) {
                    items(products) { product ->
                        ProductCard(product = product, onClick = { onProductClick(product) })
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .width(140.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "${product.price} ${product.currency}",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}
