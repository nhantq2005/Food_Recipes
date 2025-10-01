package com.example.foodrecipes.feature_food_recipes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes.feature_food_recipes.domain.model.Meal
import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem
import com.example.foodrecipes.feature_food_recipes.presentation.event.FireStoreEvent
import com.example.foodrecipes.util.Collections
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class FireStoreViewModel() : ViewModel() {

    val db = FirebaseFirestore.getInstance()
    val uid = FirebaseAuth.getInstance().currentUser?.uid
    private var job: Job? = null

    init {
        job?.cancel()
        job = viewModelScope.launch {
            getMealsForUser(Collections.Meals.collectionName)
        }
    }

    fun onEvent(event: FireStoreEvent) {
        when (event) {
            is FireStoreEvent.AddMeal -> {
                viewModelScope.launch {
                    addMeal(event.meal)
                }
            }

            is FireStoreEvent.DeleteMeal -> {
                viewModelScope.launch {
                    deleteMeal(event.mealId)
                }
            }

            is FireStoreEvent.GetMeal -> {
                job?.cancel()
                job = viewModelScope.launch {
                    getMealsForUser(event.collection)
                }
            }

            is FireStoreEvent.AddCurrentMeal -> {
                viewModelScope.launch {
                    addRecentMeal(event.meal)
                }
            }
        }
    }

    private fun addMeal(meal: MealItem) {
        if (uid != null) {
            db.collection("foodrecipesdb").document(uid)
                .collection("meals")
                .document(meal.idMeal)
                .set(meal)
                .addOnSuccessListener {
                    Log.e("Firestore", "Food added for user $uid")
                }
                .addOnFailureListener { e ->
                    Log.e("Firestore", "Error adding food", e)
                }
        }
    }

    private fun deleteMeal(mealId: String) {
        if (uid != null) {
            db.collection("foodrecipesdb").document(uid)
                .collection("meals")
                .document(mealId)  // ID của món ăn muốn xoá
                .delete()
                .addOnSuccessListener {
                    Log.d("Firestore", "Food deleted")
                }
                .addOnFailureListener { e ->
                    Log.w("Firestore", "Error deleting food", e)
                }
        }
    }

    suspend fun getMealsForUser(collection: String): List<MealItem> {
        if (uid == null) return emptyList()

        return try {
            val snapshot = db.collection("foodrecipesdb").document(uid)
//                .collection("meals")
                .collection(collection)
                .get()
                .await() // chờ kết quả Firestore trả về

            val meals = snapshot.map { doc ->
                MealItem(
                    doc.getString("idMeal") ?: "",
                    doc.getString("strMeal") ?: "",
                    doc.getString("strMealThumb") ?: ""
                )
            }

            Log.d("FavouriteViewModel", "List of meals: $meals")
            meals
        } catch (e: Exception) {
            Log.w("Firestore", "Error getting foods", e)
            emptyList()
        }
    }

    fun addRecentMeal(meal: Meal) {
        if (uid != null) {
            Log.d("CurrentMeal", "addRecentMeal: $meal")
            val historyRef = db.collection("foodrecipesdb")
                    .document(uid)
                    .collection("current")


            historyRef.orderBy("timestamp")
                .get()
                .addOnSuccessListener { snapshot ->
                    if (snapshot.size() >= 10) {
                        val oldest = snapshot.documents.first()
                        historyRef.document(oldest.id).delete()
                    }

                    val mealMap = hashMapOf(
                        "idMeal" to meal.idMeal,
                        "strMeal" to meal.strMeal,
                        "strMealThumb" to meal.strMealThumb,
                        "timestamp" to System.currentTimeMillis()
                    )

                    historyRef
                        .add(mealMap)
                }
        }
    }
}