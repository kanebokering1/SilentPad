package com.example.silentpad.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf

class SimpleAuthManager(private val context: Context) {
    private val prefs: SharedPreferences = 
        context.getSharedPreferences("silentpad_auth", Context.MODE_PRIVATE)
    
    val isLoading = mutableStateOf(false)
    val authError = mutableStateOf<String?>(null)
    val currentUser = mutableStateOf<String?>(null)
    val isLoggedIn = mutableStateOf(false)
    
    init {
        // Check if user is already logged in
        val savedEmail = prefs.getString("logged_in_email", null)
        if (savedEmail != null) {
            currentUser.value = savedEmail
            isLoggedIn.value = true
        }
    }
    
    fun createUserWithEmailPassword(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        try {
            isLoading.value = true
            authError.value = null
            
            // Basic validation
            if (email.isBlank() || !email.contains("@")) {
                authError.value = "Please enter a valid email address"
                isLoading.value = false
                onComplete(false, "Please enter a valid email address")
                return
            }
            
            if (password.length < 6) {
                authError.value = "Password must be at least 6 characters"
                isLoading.value = false
                onComplete(false, "Password must be at least 6 characters")
                return
            }
            
            // Check if user already exists
            if (prefs.contains("user_$email")) {
                authError.value = "Account with this email already exists"
                isLoading.value = false
                onComplete(false, "Account with this email already exists")
                return
            }
            
            // Save user credentials
            prefs.edit()
                .putString("user_$email", password)
                .putString("logged_in_email", email)
                .apply()
            
            currentUser.value = email
            isLoggedIn.value = true
            isLoading.value = false
            onComplete(true, null)
            
        } catch (e: Exception) {
            authError.value = "Registration failed: ${e.message}"
            isLoading.value = false
            onComplete(false, "Registration failed: ${e.message}")
        }
    }
    
    fun signInWithEmailPassword(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        try {
            isLoading.value = true
            authError.value = null
            
            // Basic validation
            if (email.isBlank() || !email.contains("@")) {
                authError.value = "Please enter a valid email address"
                isLoading.value = false
                onComplete(false, "Please enter a valid email address")
                return
            }
            
            if (password.isBlank()) {
                authError.value = "Please enter your password"
                isLoading.value = false
                onComplete(false, "Please enter your password")
                return
            }
            
            // Check if user exists and password matches
            val savedPassword = prefs.getString("user_$email", null)
            if (savedPassword == null) {
                authError.value = "No account found with this email"
                isLoading.value = false
                onComplete(false, "No account found with this email")
                return
            }
            
            if (savedPassword != password) {
                authError.value = "Incorrect password"
                isLoading.value = false
                onComplete(false, "Incorrect password")
                return
            }
            
            // Login successful
            prefs.edit()
                .putString("logged_in_email", email)
                .apply()
            
            currentUser.value = email
            isLoggedIn.value = true
            isLoading.value = false
            onComplete(true, null)
            
        } catch (e: Exception) {
            authError.value = "Login failed: ${e.message}"
            isLoading.value = false
            onComplete(false, "Login failed: ${e.message}")
        }
    }
    
    fun signOut() {
        prefs.edit()
            .remove("logged_in_email")
            .apply()
        
        currentUser.value = null
        isLoggedIn.value = false
        authError.value = null
    }
    
    fun clearError() {
        authError.value = null
    }
}