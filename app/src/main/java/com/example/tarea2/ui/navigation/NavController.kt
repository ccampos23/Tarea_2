package com.example.tarea2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tarea2.ui.screens.EventMasterViewModel
import com.example.tarea2.ui.screens.HomeScreen
import com.example.tarea2.ui.screens.EventDetailScreen
import com.example.tarea2.ui.screens.form.AddCategoryScreen
import com.example.tarea2.ui.screens.form.AddEventScreen
import kotlinx.serialization.Serializable

import androidx.navigation.toRoute

@Serializable object Home
@Serializable object AddCategory
@Serializable object AddEvent
@Serializable data class EventDetail(val eventId: Int)

@Composable
fun Navigation() {
    val navController = rememberNavController()
    // Manual ViewModel Instantiation
    val viewModel = EventMasterViewModel()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToAddCategory = { navController.navigate(AddCategory) },
                onNavigateToAddEvent = { navController.navigate(AddEvent) },
                onNavigateToEventDetail = { eventId -> navController.navigate(EventDetail(eventId)) }
            )
        }
        
        composable<AddCategory> {
            AddCategoryScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.navigateUp() }
            )
        }

        composable<AddEvent> {
            AddEventScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.navigateUp() }
            )
        }

        composable<EventDetail> { backStackEntry ->
            val eventDetail = backStackEntry.toRoute<EventDetail>()
            EventDetailScreen(
                eventId = eventDetail.eventId,
                viewModel = viewModel,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}
