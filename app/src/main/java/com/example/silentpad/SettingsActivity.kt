package com.example.silentpad

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.silentpad.ui.theme.SilentPadTheme

val SettingsBackground = Color(0xFF050505)
val SettingsCardColor = Color(0xFFA6C6FF)
val SettingsTextColor = Color(0xFF1A1A1A)
val SettingsTextLight = Color(0xFFA6C6FF)

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SilentPadTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SettingsBackground
                ) {
                    SettingsScreen()
                }
            }
        }
    }
}

@Composable
fun SettingsScreen() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val activity = context as? ComponentActivity
    val prefs = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
    
    var darkMode by remember { mutableStateOf(prefs.getBoolean("dark_mode", true)) }
    var notifications by remember { mutableStateOf(prefs.getBoolean("notifications", true)) }
    var autoSave by remember { mutableStateOf(prefs.getBoolean("auto_save", true)) }
    var fontSize by remember { mutableStateOf(prefs.getInt("font_size", 16)) }
    var showFontSizeDialog by remember { mutableStateOf(false) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SettingsBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            // Header dengan Back Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { activity?.finish() }) {
                    Surface(
                        modifier = Modifier.size(48.dp),
                        shape = CircleShape,
                        color = SettingsCardColor
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = SettingsTextColor,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Text(
                    text = "Settings",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = SettingsTextLight,
                    fontFamily = FontFamily.SansSerif
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Appearance Section
            SettingsSectionTitle("Appearance")
            
            Spacer(modifier = Modifier.height(16.dp))
            
            SettingsToggleCard(
                icon = Icons.Default.DarkMode,
                title = "Dark Mode",
                subtitle = "Use dark theme",
                checked = darkMode,
                onCheckedChange = { 
                    darkMode = it
                    prefs.edit().putBoolean("dark_mode", it).apply()
                }
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            SettingsClickCard(
                icon = Icons.Default.FormatSize,
                title = "Font Size",
                subtitle = "Current: ${fontSize}sp",
                onClick = { showFontSizeDialog = true }
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Notifications Section
            SettingsSectionTitle("Notifications")
            
            Spacer(modifier = Modifier.height(16.dp))
            
            SettingsToggleCard(
                icon = Icons.Default.Notifications,
                title = "Enable Notifications",
                subtitle = "Get notified about updates",
                checked = notifications,
                onCheckedChange = { 
                    notifications = it
                    prefs.edit().putBoolean("notifications", it).apply()
                }
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Notes Section
            SettingsSectionTitle("Notes")
            
            Spacer(modifier = Modifier.height(16.dp))
            
            SettingsToggleCard(
                icon = Icons.Default.Save,
                title = "Auto Save",
                subtitle = "Automatically save notes",
                checked = autoSave,
                onCheckedChange = { 
                    autoSave = it
                    prefs.edit().putBoolean("auto_save", it).apply()
                }
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // About Section
            SettingsSectionTitle("About")
            
            Spacer(modifier = Modifier.height(16.dp))
            
            SettingsClickCard(
                icon = Icons.Default.Info,
                title = "App Version",
                subtitle = "1.0.0",
                onClick = { }
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            SettingsClickCard(
                icon = Icons.Default.Policy,
                title = "Privacy Policy",
                subtitle = "View privacy policy",
                onClick = { }
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            SettingsClickCard(
                icon = Icons.Default.Description,
                title = "Terms of Service",
                subtitle = "View terms and conditions",
                onClick = { }
            )
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
    
    // Font Size Dialog
    if (showFontSizeDialog) {
        AlertDialog(
            onDismissRequest = { showFontSizeDialog = false },
            title = { Text("Font Size", fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text("Select font size for notes:")
                    Spacer(modifier = Modifier.height(16.dp))
                    Slider(
                        value = fontSize.toFloat(),
                        onValueChange = { fontSize = it.toInt() },
                        valueRange = 12f..24f,
                        steps = 11
                    )
                    Text(
                        text = "${fontSize}sp",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        prefs.edit().putInt("font_size", fontSize).apply()
                        showFontSizeDialog = false
                    }
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showFontSizeDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun SettingsSectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = SettingsTextLight,
        fontFamily = FontFamily.SansSerif
    )
}

@Composable
fun SettingsToggleCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SettingsCardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = SettingsTextColor,
                modifier = Modifier.size(32.dp)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = SettingsTextColor,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = subtitle,
                    fontSize = 13.sp,
                    color = SettingsTextColor.copy(alpha = 0.7f),
                    fontFamily = FontFamily.SansSerif
                )
            }
            
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = SettingsTextColor,
                    checkedTrackColor = SettingsTextColor.copy(alpha = 0.5f)
                )
            )
        }
    }
}

@Composable
fun SettingsClickCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = SettingsCardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = SettingsTextColor,
                modifier = Modifier.size(32.dp)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = SettingsTextColor,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = subtitle,
                    fontSize = 13.sp,
                    color = SettingsTextColor.copy(alpha = 0.7f),
                    fontFamily = FontFamily.SansSerif
                )
            }
            
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Open",
                tint = SettingsTextColor,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
