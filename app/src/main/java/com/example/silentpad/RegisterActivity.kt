package com.example.silentpad

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.silentpad.ui.theme.SilentPadTheme
import com.example.silentpad.ui.theme.SilentPadColors
import com.example.silentpad.ui.theme.SilentPadTitle
import com.example.silentpad.ui.theme.SilentPadInputField
import com.example.silentpad.ui.theme.SilentPadSmallButton
import com.example.silentpad.ui.theme.SocialButton
import com.example.silentpad.ui.theme.GoogleWhite
import com.example.silentpad.ui.theme.FacebookBlue

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SilentPadTheme {
                RegisterScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Pure Black Background using theme
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SilentPadColors.background)
        )
        
        // Wolf Moon Image - centered
        Image(
            painter = painterResource(id = R.drawable.wolfmoon),
            contentDescription = "Background",
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .offset(y = 80.dp),
            contentScale = ContentScale.Fit
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            
            // Create Account Title using global component
            SilentPadTitle(
                text = "Create Account",
                fontSize = 25.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            // "To get started now!" subtitle using theme colors
            Text(
                text = "To get started now!",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = SilentPadColors.textPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            // Email Field using global component
            SilentPadInputField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email Address",
                width = 163.dp,
                height = 39.dp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Password Field using global component
            SilentPadInputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                width = 110.dp,
                height = 39.dp,
                isPassword = true,
                passwordVisible = passwordVisible,
                onPasswordVisibilityToggle = { passwordVisible = !passwordVisible }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Confirm Password Field using global component
            SilentPadInputField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = "Confirm",
                width = 143.dp,
                height = 39.dp,
                isPassword = true,
                passwordVisible = confirmPasswordVisible,
                onPasswordVisibilityToggle = { confirmPasswordVisible = !confirmPasswordVisible }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Register Button using global component
            SilentPadSmallButton(
                text = "REGISTER",
                onClick = {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                },
                width = 93.dp,
                height = 33.dp,
                fontSize = 10.sp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Social Login Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Google Button using global component
                SocialButton(
                    text = "G",
                    onClick = { /* Google login */ },
                    size = 41.dp,
                    backgroundColor = GoogleWhite,
                    textColor = SilentPadColors.inputBackground
                )
                
                Spacer(modifier = Modifier.width(24.dp))
                
                // Facebook Button using global component
                SocialButton(
                    text = "f",
                    onClick = { /* Facebook login */ },
                    size = 49.dp,
                    backgroundColor = FacebookBlue,
                    textColor = SilentPadColors.textPrimary,
                    fontSize = 24.sp
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Already have Account? Login Now Button using theme colors
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Already have an Account? Login",
                    fontSize = 12.sp,
                    color = SilentPadColors.textSecondary,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Now Button:",
                    fontSize = 12.sp,
                    color = SilentPadColors.textPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable {
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

