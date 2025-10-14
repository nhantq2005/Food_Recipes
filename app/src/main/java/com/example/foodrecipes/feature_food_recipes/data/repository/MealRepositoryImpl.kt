package com.example.foodrecipes.feature_food_recipes.data.repository

import android.util.Log
import com.example.foodrecipes.feature_food_recipes.domain.model.Meal
import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem
import com.example.foodrecipes.feature_food_recipes.domain.repository.MealRepository
import com.example.foodrecipes.util.Collection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : MealRepository {
    private val uid = auth.currentUser?.uid

    //GET MEALS
    override fun getMeals(collection: String): Flow<List<MealItem>> = callbackFlow {

        if (uid == null) {
            trySend(emptyList())
            close()
            return@callbackFlow
        }

        val listener = db.collection("foodrecipesdb").document(uid)
            .collection(collection)
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w("Firestore", "Listen failed.", e)
                    trySend(emptyList())
                    return@addSnapshotListener
                }

                val meals = snapshot?.documents?.map { doc ->
                    MealItem(
                        doc.getString("idMeal") ?: "",
                        doc.getString("strMeal") ?: "",
                        doc.getString("strMealThumb") ?: ""
                    )
                } ?: emptyList()

                trySend(meals)
            }

        awaitClose { listener.remove() }
    }

    override fun addMeal(meal: MealItem) {
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

    override fun deleteMeal(mealId: String) {
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

    override fun addRecentMeal(meal: Meal) {
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

                    val mealMap = MealItem(
                        meal.idMeal,
                        meal.strMeal,
                        meal.strMealThumb,
                        System.currentTimeMillis()
                    )

                    historyRef
                        .document(meal.idMeal).set(mealMap)
                }
        }
    }

    override fun findMeal(name: String): Flow<List<MealItem>> = callbackFlow {
        if (uid == null) {
            trySend(emptyList())
            close()
            return@callbackFlow
        }

        val listener = db.collection("foodrecipesdb").document(uid)
            .collection(Collection.Meals.collectionName)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w("Firestore", "Listen failed.", e)
                    trySend(emptyList())
                    return@addSnapshotListener
                }

                val meals = snapshot?.documents?.map { doc ->
                    MealItem(
                        doc.getString("idMeal") ?: "",
                        doc.getString("strMeal") ?: "",
                        doc.getString("strMealThumb") ?: ""
                    )
                }?.filter { it.strMeal.contains(name, ignoreCase = true) } ?: emptyList()
                Log.d("Firestore", "List of meals: $meals")
                trySend(meals)
            }

        awaitClose { listener.remove() }
    }
}