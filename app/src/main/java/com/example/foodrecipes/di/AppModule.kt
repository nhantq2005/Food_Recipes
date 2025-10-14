package com.example.foodrecipes.di

import com.example.foodrecipes.feature_food_recipes.data.model.api.FoodRecipesApi
import com.example.foodrecipes.feature_food_recipes.data.repository.MealRepositoryImpl
import com.example.foodrecipes.feature_food_recipes.domain.repository.MealRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideMealRepository(
        db: FirebaseFirestore,
        auth: FirebaseAuth
    ): MealRepository = MealRepositoryImpl(db, auth)

    private val interceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providesFoodRecipesApi() : FoodRecipesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(FoodRecipesApi.BASE_URL)
            .client(client)
            .build()
            .create()
    }
}