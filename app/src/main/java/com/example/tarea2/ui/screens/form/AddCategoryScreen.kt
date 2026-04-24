package com.example.tarea2.ui.screens.form

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tarea2.R
import com.example.tarea2.ui.screens.EventMasterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCategoryScreen(
    viewModel: EventMasterViewModel,
    onNavigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_add_category)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { 
                    name = it
                    if (it.isNotEmpty()) isError = false
                },
                label = { Text(stringResource(R.string.label_category_name)) },
                isError = isError,
                supportingText = { if (isError) Text(stringResource(R.string.error_empty_field), color = MaterialTheme.colorScheme.error) },
                modifier = Modifier.fillMaxWidth()
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onNavigateBack, modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.btn_back))
                }
                Button(
                    onClick = {
                        if (name.isBlank()) {
                            isError = true
                        } else {
                            viewModel.addCategory(name)
                            onNavigateBack()
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.btn_save))
                }
            }
        }
    }
}
