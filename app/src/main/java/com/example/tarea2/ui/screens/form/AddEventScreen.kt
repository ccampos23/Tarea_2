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
fun AddEventScreen(
    viewModel: EventMasterViewModel,
    onNavigateBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var categoryId by remember { mutableStateOf<Int?>(null) }
    
    var titleError by remember { mutableStateOf(false) }
    var descError by remember { mutableStateOf(false) }
    var dateError by remember { mutableStateOf(false) }
    var categoryError by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }
    val selectedCategoryName = viewModel.categories.find { it.id == categoryId }?.name ?: ""

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_add_event)) },
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it; if (it.isNotEmpty()) titleError = false },
                label = { Text(stringResource(R.string.label_title)) },
                isError = titleError,
                supportingText = { if (titleError) Text(stringResource(R.string.error_empty_field), color = MaterialTheme.colorScheme.error) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = desc,
                onValueChange = { desc = it; if (it.isNotEmpty()) descError = false },
                label = { Text(stringResource(R.string.label_description)) },
                isError = descError,
                supportingText = { if (descError) Text(stringResource(R.string.error_empty_field), color = MaterialTheme.colorScheme.error) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = date,
                onValueChange = { date = it; if (it.isNotEmpty()) dateError = false },
                label = { Text(stringResource(R.string.label_date)) },
                isError = dateError,
                supportingText = { if (dateError) Text(stringResource(R.string.error_empty_field), color = MaterialTheme.colorScheme.error) },
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    readOnly = true,
                    value = selectedCategoryName,
                    onValueChange = {},
                    label = { Text(stringResource(R.string.label_category)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    isError = categoryError,
                    supportingText = { if (categoryError) Text(stringResource(R.string.error_select_category), color = MaterialTheme.colorScheme.error) }
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    viewModel.categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category.name) },
                            onClick = {
                                categoryId = category.id
                                categoryError = false
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onNavigateBack, modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.btn_back))
                }
                Button(
                    onClick = {
                        val isTitleEmpty = title.isBlank()
                        val isDescEmpty = desc.isBlank()
                        val isDateEmpty = date.isBlank()
                        val isCategoryEmpty = categoryId == null

                        titleError = isTitleEmpty
                        descError = isDescEmpty
                        dateError = isDateEmpty
                        categoryError = isCategoryEmpty

                        if (!isTitleEmpty && !isDescEmpty && !isDateEmpty && !isCategoryEmpty) {
                            viewModel.addEvent(title, desc, date, categoryId!!)
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
