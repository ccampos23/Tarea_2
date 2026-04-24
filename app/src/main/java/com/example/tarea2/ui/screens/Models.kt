package com.example.tarea2.ui.screens

data class Category(
    val id: Int,
    val name: String
)

data class Event(
    val id: Int,
    val title: String,
    val desc: String,
    val date: String,
    val categoryId: Int
)
