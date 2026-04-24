package com.example.tarea2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tarea2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(
    eventId: Int,
    viewModel: EventMasterViewModel,
    onNavigateBack: () -> Unit
) {
    val event = viewModel.getEventById(eventId)
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(event?.title ?: "Detalle del Evento") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.btn_back))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        if (event == null) {
            Box(modifier = Modifier.padding(padding).fillMaxSize()) {
                Text("Evento no encontrado", modifier = Modifier.align(androidx.compose.ui.Alignment.Center))
            }
            return@Scaffold
        }
        
        val categoryName = viewModel.getCategoryById(event.categoryId)?.name ?: "Sin categoría"

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = stringResource(R.string.label_title), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                    Text(text = event.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(text = stringResource(R.string.label_description), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                    Text(text = event.desc, style = MaterialTheme.typography.bodyLarge)
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(text = stringResource(R.string.label_date), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                    Text(text = event.date, style = MaterialTheme.typography.bodyLarge)
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(text = stringResource(R.string.label_category), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                    Text(text = categoryName, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
