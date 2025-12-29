package com.example.silentpad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.silentpad.ui.theme.SilentPadTheme
import kotlinx.coroutines.delay

val BackgroundBlack = Color(0xFF050505)
val PeriwinkleBlue = Color(0xFFA6C6FF)
val CardTextBlack = Color(0xFF1A1A1A)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SilentPadTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = BackgroundBlack) {
                    MainMenuScreen()
                }
            }
        }
    }
}

@Composable
fun MainMenuScreen() {
    val notesList = remember { mutableStateListOf("TITLE...", "TITLE...", "TITLE...") }
    var showMenu by remember { mutableStateOf(false) }
    
    val moonAlpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 1500, easing = EaseInOut),
        label = "moonAlpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundBlack)
    ) {
        // Bottom Moon Image - Layer paling bawah
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(400.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.menu11),
                contentDescription = "Moon Wolf",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(400.dp)
                    .offset(y = 50.dp)
                    .graphicsLayer(alpha = moonAlpha)
            )
        }
        
        // Content layer - di atas moon
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Menu dropdown di kanan atas
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Menu",
                        tint = PeriwinkleBlue,
                        modifier = Modifier.size(28.dp)
                    )
                }
                
                // Dropdown menu dengan alignment yang benar
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false },
                    offset = DpOffset((400).dp, 0.dp)
                ) {
                    DropdownMenuItem(
                        text = { 
                            Text(
                                "Settings",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        },
                        onClick = { 
                            showMenu = false
                        }
                    )
                    DropdownMenuItem(
                        text = { 
                            Text(
                                "About",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        },
                        onClick = { 
                            showMenu = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Logo Wolf + Quote Bubble
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 40.dp)
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(
                            PeriwinkleBlue, 
                            RoundedCornerShape(
                                topEnd = 25.dp, 
                                bottomEnd = 25.dp, 
                                topStart = 10.dp, 
                                bottomStart = 10.dp
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "\"Silence writes memory\"",
                        color = CardTextBlack,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 30.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(80.dp)
                        .border(3.dp, PeriwinkleBlue, CircleShape)
                        .padding(1.dp)
                        .background(Color.Black, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.menu3_text),
                        contentDescription = "Wolf Logo",
                        modifier = Modifier.size(500.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Content box dengan padding untuk tidak tertutup moon
            Box(
                modifier = Modifier.weight(1f)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    contentPadding = PaddingValues(bottom = 280.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(notesList) { index, title ->
                        NoteCapsuleItem(
                            title = title,
                            onNoteClick = {},
                            onEditClick = {}
                        )
                    }

                    item {
                        var isPressed by remember { mutableStateOf(false) }
                        val scale by animateFloatAsState(
                            targetValue = if (isPressed) 0.9f else 1f,
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessLow
                            ),
                            label = "addButtonScale"
                        )
                        
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .scale(scale)
                                    .background(PeriwinkleBlue, CircleShape)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(bounded = true, color = Color.White),
                                        onClick = {
                                            notesList.add("TITLE...")
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Note",
                                    tint = Color.Black,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NoteCapsuleItem(
    title: String,
    onNoteClick: () -> Unit,
    onEditClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "noteCardScale"
    )
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            PeriwinkleBlue,
                            PeriwinkleBlue.copy(alpha = 0.9f)
                        )
                    ),
                    shape = RoundedCornerShape(60)
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true, color = Color.White),
                    onClick = {
                        isPressed = true
                        onNoteClick()
                    }
                )
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = CardTextBlack,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 4.dp)
                    .size(48.dp)
                    .background(Color.Black, CircleShape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true, color = PeriwinkleBlue),
                        onClick = onEditClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Open",
                    tint = PeriwinkleBlue,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
    
    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(200)
            isPressed = false
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainMenuPreview() {
    SilentPadTheme {
        MainMenuScreen()
    }
}
