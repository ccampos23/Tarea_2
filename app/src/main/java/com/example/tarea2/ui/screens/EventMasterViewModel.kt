package com.example.tarea2.ui.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class EventMasterViewModel : ViewModel() {
    val categories = mutableStateListOf<Category>()
    val events = mutableStateListOf<Event>()

    init {
        categories.add(Category(1, "Música"))
        categories.add(Category(2, "Tecnología"))
        categories.add(Category(3, "Deportes"))
    }

    fun addCategory(name: String) {
        val newId = (categories.maxOfOrNull { it.id } ?: 0) + 1
        categories.add(Category(newId, name))
    }

    fun addEvent(title: String, desc: String, date: String, categoryId: Int) {
        val newId = (events.maxOfOrNull { it.id } ?: 0) + 1
        events.add(Event(newId, title, desc, date, categoryId))
    }

    fun getEventById(id: Int): Event? {
        return events.find { it.id == id }
    }
    
    fun getCategoryById(id: Int): Category? {
        return categories.find { it.id == id }
    }
}
