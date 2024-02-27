package com.example.tradernet.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.tradernet.MainViewModel
import com.example.tradernet.ui.component.LazyListItem
import com.example.tradernet.ui.theme.lazyColumnItemsGap
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {

    val items by viewModel.mainScreenState.collectAsState()

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(lazyColumnItemsGap)
        ) {
            items(items.size) { index ->
                LazyListItem(items[index])
            }
        }
    }
}