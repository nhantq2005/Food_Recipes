package com.example.foodrecipes.util

sealed class Collection(
    val collectionName:String
){
    data object Meals:Collection("meals")
    data object Current:Collection("current")
}