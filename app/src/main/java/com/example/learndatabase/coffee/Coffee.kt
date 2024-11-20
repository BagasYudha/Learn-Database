package com.example.learndatabase.coffee

data class Coffee(
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val image: String,
    val id: Int
)