package com.example.tarea2.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tarea2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: EventMasterViewModel,
    onNavigateToAddCategory: () -> Unit,
    onNavigateToAddEvent: () -> Unit,
    onNavigateToEventDetail: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.title_home)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            Column(horizontalAlignment = Alignment.End) {
                ExtendedFloatingActionButton(
                    text = { Text(stringResource(R.string.btn_create_category)) },
                    icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                    onClick = onNavigateToAddCategory,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                ExtendedFloatingActionButton(
                    text = { Text(stringResource(R.string.btn_create_event)) },
                    icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                    onClick = onNavigateToAddEvent,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(viewModel.categories) { category ->
                val categoryEvents = viewModel.events.filter { it.categoryId == category.id }
                
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    if (categoryEvents.isEmpty()) {
                        Text(
                            text = stringResource(R.string.msg_no_events),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(categoryEvents) { event ->
                                EventCard(event = event, onClick = { onNavigateToEventDetail(event.id) })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EventCard(event: Event, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = event.date,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
