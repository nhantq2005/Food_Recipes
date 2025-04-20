package com.example.foodrecipes.feature_food_recipes.domain.util

//class Convert {
    fun convertIngredientName(ingredient: String):String{
        return ingredient.replace(" ","-")
    }
//}