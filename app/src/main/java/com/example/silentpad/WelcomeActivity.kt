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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.silentpad.ui.theme.SilentPadTheme
import com.example.silentpad.ui.theme.Black
import com.example.silentpad.ui.theme.LightBlue
import com.example.silentpad.ui.theme.White

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
        // Background Image - using one of the wolf images
        Image(
            painter = painterResource(id = R.drawable.wolf_647528_1920),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        
        // Overlay untuk readability
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // App Name
            Text(
                text = "SilentPad",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = White,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            // Location Info
            Text(
                text = "Lokasi: $location",
                fontSize = 16.sp,
                color = White,
                modifier = Modifier.padding(bottom = 48.dp)
            )
            
            // Login Button
            Box(
                modifier = Modifier
                    .width(205.dp)
                    .height(62.dp)
                    .background(Black, shape = RoundedCornerShape(8.dp))
                    .clickable {
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Sign Up Button
            Box(
                modifier = Modifier
                    .width(205.dp)
                    .height(62.dp)
                    .background(Black, shape = RoundedCornerShape(8.dp))
                    .clickable {
                        val intent = Intent(context, RegisterActivity::class.java)
                        context.startActivity(intent)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
            }
        }
    }
}

