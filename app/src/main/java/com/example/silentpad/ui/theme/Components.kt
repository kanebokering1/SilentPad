package com.example.silentpad.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// SilentPad Primary Button Component
@Composable
fun SilentPadButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 205.dp,
    height: Dp = 62.dp,
    backgroundColor: Color = SilentPadColors.buttonPrimary,
    borderColor: Color = SilentPadColors.buttonBorder,
    textColor: Color = SilentPadColors.textPrimary,
    fontSize: TextUnit = 18.sp,
    cornerRadius: Dp = 8.dp,
    borderWidth: Dp = 2.dp
) {
    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(cornerRadius),
                ambientColor = Color.Black.copy(alpha = 0.5f)
            )
            .background(
                backgroundColor,
                shape = RoundedCornerShape(cornerRadius)
            )
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(cornerRadius)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}

// SilentPad Title Text Component
@Composable
fun SilentPadTitle(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 34.sp,
    color: Color = SilentPadColors.textPrimary
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        color = color,
        modifier = modifier
    )
}

// Social Login Button
@Composable
fun SocialButton(
    text: String,
    onClick: () -> Unit,
    size: Dp,
    backgroundColor: Color,
    textColor: Color,
    fontSize: TextUnit = 18.sp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = Color.Black.copy(alpha = 0.3f)
            )
            .background(
                backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}

// SilentPad Input Field Component
@Composable
fun SilentPadInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    width: Dp = 330.dp,
    height: Dp = 60.dp,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onPasswordVisibilityToggle: (() -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { 
            Text(
                placeholder, 
                color = SilentPadColors.textSecondary.copy(alpha = 0.7f),
                fontSize = 16.sp
            ) 
        },
        modifier = modifier
            .width(width)
            .height(height)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(8.dp),
                ambientColor = Color.Black.copy(alpha = 0.3f)
            ),
        shape = RoundedCornerShape(8.dp),
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = if (isPassword && onPasswordVisibilityToggle != null) {
            {
                IconButton(onClick = onPasswordVisibilityToggle) {
                    Icon(
                        if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Toggle password visibility",
                        tint = SilentPadColors.textPrimary
                    )
                }
            }
        } else null,
        colors = TextFieldDefaults.colors(
            focusedTextColor = SilentPadColors.textPrimary,
            unfocusedTextColor = SilentPadColors.textPrimary,
            focusedContainerColor = SilentPadColors.inputBackground,
            unfocusedContainerColor = SilentPadColors.inputBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = SilentPadColors.textPrimary
        ),
        singleLine = true
    )
}

// Back Button Component  
@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .size(40.dp)
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color.Black.copy(alpha = 0.4f)
            )
            .background(
                SilentPadColors.inputBackground.copy(alpha = 0.8f),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = "Back",
            tint = SilentPadColors.textPrimary,
            modifier = Modifier.size(20.dp)
        )
    }
}