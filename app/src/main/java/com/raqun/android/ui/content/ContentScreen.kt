package com.raqun.android.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raqun.android.data.DataState

@Composable
fun ContentScreen(viewModel: ContentViewModel) {
    val contentBean by viewModel.getContentLiveData().observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val data = contentBean?.getData()
        val state = contentBean?.getState()

        when {
            state == DataState.FETCHING -> CircularProgressIndicator()
            data != null -> {
                Text(
                    text = data.title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    text = data.content,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
