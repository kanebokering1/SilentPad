package com.example.silentpad

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.silentpad.ui.theme.SilentPadTheme
import com.example.silentpad.ui.theme.Black
import com.example.silentpad.ui.theme.LightBlue
import com.example.silentpad.ui.theme.White

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
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.wolf_647528_1920),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        
        // Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Create Account Text
            Text(
                text = "Create Account",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            // Sub-text
            Text(
                text = "To get started now!",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            // Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address", color = White) },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "Email", tint = LightBlue)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(39.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = White,
                    unfocusedTextColor = White,
                    focusedBorderColor = LightBlue,
                    unfocusedBorderColor = LightBlue,
                    focusedLabelColor = LightBlue,
                    unfocusedLabelColor = LightBlue
                ),
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = White) },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Password", tint = LightBlue)
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            tint = LightBlue
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(39.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = White,
                    unfocusedTextColor = White,
                    focusedBorderColor = LightBlue,
                    unfocusedBorderColor = LightBlue,
                    focusedLabelColor = LightBlue,
                    unfocusedLabelColor = LightBlue
                ),
                shape = RoundedCornerShape(8.dp),
                singleLine = true
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Confirm Password Field
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password", color = White) },
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "Confirm Password", tint = LightBlue)
                },
                trailingIcon = {
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(
                            imageVector = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password",
                            tint = LightBlue
                        )
                    }
                },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(39.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = White,
                    unfocusedTextColor = White,
                    focusedBorderColor = LightBlue,
                    unfocusedBorderColor = LightBlue,
                    focusedLabelColor = LightBlue,
                    unfocusedLabelColor = LightBlue
                ),
                shape = RoundedCornerShape(8.dp),
                singleLine = true
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Sign Up Button
            Button(
                onClick = {
                    // TODO: Implement register logic
                    if (password == confirmPassword && email.isNotEmpty()) {
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .width(205.dp)
                    .height(62.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "SIGN UP",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Divider with "Or Sign Up With"
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = LightBlue.copy(alpha = 0.5f)
                )
                Text(
                    text = "Or Sign Up With",
                    color = LightBlue,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = LightBlue.copy(alpha = 0.5f)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Social Login Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Google Button (placeholder)
                Box(
                    modifier = Modifier
                        .size(41.dp)
                        .background(White, shape = RoundedCornerShape(20.5.dp))
                        .clickable { /* TODO: Google sign up */ },
                    contentAlignment = Alignment.Center
                ) {
                    Text("G", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Black)
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                // Facebook Button (placeholder)
                Box(
                    modifier = Modifier
                        .size(49.dp)
                        .background(White, shape = RoundedCornerShape(24.5.dp))
                        .clickable { /* TODO: Facebook sign up */ },
                    contentAlignment = Alignment.Center
                ) {
                    Text("f", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Black)
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Login Link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an Account? ",
                    fontSize = 14.sp,
                    color = White
                )
                Text(
                    text = "Login Now",
                    fontSize = 14.sp,
                    color = LightBlue,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}

