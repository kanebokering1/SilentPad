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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
import com.example.silentpad.ui.theme.SilentPadButton

import com.example.silentpad.ui.theme.SocialButton
import com.example.silentpad.ui.theme.GoogleWhite
import com.example.silentpad.ui.theme.FacebookBlue

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SilentPadTheme {
                LoginScreen(
                    onLoginClick = {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    },
                    onSignUpClick = {
                        startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Pure Black Background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SilentPadColors.background)
        )
        
        // Back Button - Top Left Corner
        val context = androidx.compose.ui.platform.LocalContext.current
        BackButton(
            onClick = {
                val intent = Intent(context, WelcomeActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 24.dp, start = 8.dp)
        )
        
        // Wolf Moon Image - centered and properly sized
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.wolfmoon),
                contentDescription = "Wolf Moon Background",
                modifier = Modifier
                    .size(400.dp)
                    .offset(y = 80.dp),
                contentScale = ContentScale.Fit
            )
        }
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(400.dp))
            
            // WELCOME, GLAD TO SEE YOU AGAIN! Text - positioned below wolf moon
            Text(
                text = "WELCOME,\nGLAD TO SEE YOU AGAIN!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Serif,
                color = SilentPadColors.textPrimary,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                modifier = Modifier.padding(bottom = 60.dp)
            )
            
            // Email Address Field - Input Field
            SilentPadInputField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email Address",
                width = 330.dp,
                height = 60.dp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Password Field - Input Field
            SilentPadInputField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                width = 330.dp,
                height = 60.dp,
                isPassword = true,
                passwordVisible = passwordVisible,
                onPasswordVisibilityToggle = { passwordVisible = !passwordVisible }
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Forgot Password Link - positioned to the right
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forgot Password?",
                    fontSize = 12.sp,
                    color = SilentPadColors.textPrimary,
                    modifier = Modifier.clickable { /* Forgot password logic */ }
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // LOGIN Button - Large like WelcomeActivity buttons
            SilentPadButton(
                text = "LOGIN",
                onClick = onLoginClick,
                width = 330.dp,
                height = 60.dp,
                fontSize = 18.sp
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // "Or Login With" text with line separators
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(SilentPadColors.textPrimary)
                )
                Text(
                    text = "Or Login With",
                    fontSize = 14.sp,
                    color = SilentPadColors.textPrimary,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(SilentPadColors.textPrimary)
                )
            }
            
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
                    onClick = { /* Google login */ },
                    size = 50.dp,
                    backgroundColor = GoogleWhite,
                    textColor = SilentPadColors.inputBackground,
                    fontSize = 20.sp
                )
                
                Spacer(modifier = Modifier.width(24.dp))
                
                // Facebook Button - Square with proper Facebook styling
                SocialButton(
                    text = "f",
                    onClick = { /* Facebook login */ },
                    size = 50.dp,
                    backgroundColor = FacebookBlue,
                    textColor = SilentPadColors.textPrimary,
                    fontSize = 24.sp
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Don't have Account? Sign Up Now
            Text(
                text = "Don't have Account? Sign Up Now",
                fontSize = 14.sp,
                color = SilentPadColors.textSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable { onSignUpClick() }
            )
            
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

