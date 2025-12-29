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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.silentpad.ui.theme.SilentPadTheme
import com.example.silentpad.ui.theme.SilentPadColors
import com.example.silentpad.ui.theme.SilentPadTitle

import com.example.silentpad.ui.theme.SilentPadButton
import com.example.silentpad.ui.theme.SilentPadInputField
import com.example.silentpad.ui.theme.SocialButton
import com.example.silentpad.ui.theme.BackButton
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

@Composable
fun RegisterScreen() {
    val context = LocalContext.current
    
    // State for input fields
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var confirmPasswordText by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

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
        
        // Back Button - Top Left Corner
        BackButton(
            onClick = {
                val intent = Intent(context, WelcomeActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 24.dp, start = 8.dp)
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(400.dp))
            
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
            
            // Email Address Field - Input Field
            SilentPadInputField(
                value = emailText,
                onValueChange = { emailText = it },
                placeholder = "Email Address",
                width = 330.dp,
                height = 60.dp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Password Field - Input Field  
            SilentPadInputField(
                value = passwordText,
                onValueChange = { passwordText = it },
                placeholder = "Password",
                width = 330.dp,
                height = 60.dp,
                isPassword = true,
                passwordVisible = isPasswordVisible,
                onPasswordVisibilityToggle = { isPasswordVisible = !isPasswordVisible }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Confirm Password Field - Input Field
            SilentPadInputField(
                value = confirmPasswordText,
                onValueChange = { confirmPasswordText = it },
                placeholder = "Confirm Password",
                width = 330.dp,
                height = 60.dp,
                isPassword = true,
                passwordVisible = isConfirmPasswordVisible,
                onPasswordVisibilityToggle = { isConfirmPasswordVisible = !isConfirmPasswordVisible }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // REGISTER Button - Large like Login button
            SilentPadButton(
                text = "REGISTER",
                onClick = {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                },
                width = 330.dp,
                height = 60.dp,
                fontSize = 18.sp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Social Login Buttons Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Google Button - Square with proper Google logo styling
                SocialButton(
                    text = "G",
                    onClick = { /* Google signup */ },
                    size = 50.dp,
                    backgroundColor = GoogleWhite,
                    textColor = SilentPadColors.inputBackground,
                    fontSize = 20.sp
                )
                
                Spacer(modifier = Modifier.width(24.dp))
                
                // Facebook Button - Square with proper Facebook styling
                SocialButton(
                    text = "f",
                    onClick = { /* Facebook signup */ },
                    size = 50.dp,
                    backgroundColor = FacebookBlue,
                    textColor = SilentPadColors.textPrimary,
                    fontSize = 24.sp
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Already have an Account? Login Now
            Text(
                text = "Already have an Account? Login Now",
                fontSize = 14.sp,
                color = SilentPadColors.textSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable {
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                }
            )
            
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

