package com.example.silentpad

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.silentpad.ui.theme.SilentPadTheme
import com.example.silentpad.ui.theme.SilentPadColors
import com.example.silentpad.ui.theme.SilentPadButton
import com.example.silentpad.ui.theme.SilentPadTitle

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val location = intent.getStringExtra("user_location") ?: "Unknown"
        
        setContent {
            SilentPadTheme {
                WelcomeScreen(location = location)
            }
        }
    }
}

@Composable
fun WelcomeScreen(location: String) {
    val context = LocalContext.current
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Pure Black Background
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
            verticalArrangement = Arrangement.Center
        ) {
            
            Spacer(modifier = Modifier.height(300.dp))
            
            // SilentPad Title - Using global theme
            SilentPadTitle(
                text = "SilentPad",
                modifier = Modifier.padding(bottom = 60.dp)
            )
            
            // LOGIN Button - Using global component
            SilentPadButton(
                text = "LOGIN",
                onClick = {
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                }
            )
            
            Spacer(modifier = Modifier.height(45.dp))
            
            // Sign Up Button - Using global component
            SilentPadButton(
                text = "Sign Up",
                onClick = {
                    val intent = Intent(context, RegisterActivity::class.java)
                    context.startActivity(intent)
                }
            )
            
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}


