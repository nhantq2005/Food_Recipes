package com.example.foodrecipes.feature_food_recipes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodrecipes.feature_food_recipes.presentation.state.AuthState
import com.example.foodrecipes.feature_food_recipes.presentation.state.SignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AuthViewModel : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }


    }

    fun resetState() {
        _state.update { AuthState() }
    }
}