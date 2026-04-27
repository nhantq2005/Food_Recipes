package com.example.foodrecipes.util

import android.content.Context
import android.widget.Toast

// 1. Class quản lý việc hiển thị Toast
// Hilt sẽ tự động cung cấp ApplicationContext cho class này
class ToastManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun showToast(message: String) {
        // Luôn sử dụng ApplicationContext được inject, rất an toàn
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

// 2. Sử dụng trong ViewModel
@HiltViewModel
class MyViewModel @Inject constructor(
    private val toastManager: ToastManager // Inject ToastManager vào ViewModel
) : ViewModel() {

    fun onSomeAction() {
        // Gọi Toast từ logic của ViewModel
        toastManager.showToast("Action completed successfully!")
    }
}
