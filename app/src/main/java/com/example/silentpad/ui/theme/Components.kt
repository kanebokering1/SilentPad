package com.example.silentpad.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    fontSize: TextUnit = 35.sp,
    color: Color = SilentPadColors.textPrimary
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Serif, // Wellfleet style
        color = color,
        modifier = modifier
    )
}

// Small Button for Login/Register forms
@Composable
fun SilentPadSmallButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 53.dp,
    height: Dp = 33.dp,
    backgroundColor: Color = SilentPadColors.inputBackground,
    textColor: Color = SilentPadColors.textPrimary,
    fontSize: TextUnit = 10.sp
) {
    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .background(
                backgroundColor,
                shape = RoundedCornerShape(0.dp)
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
            .background(
                backgroundColor,
                shape = RoundedCornerShape(0.dp)
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
@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun SilentPadInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    width: Dp = 163.dp,
    height: Dp = 39.dp,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onPasswordVisibilityToggle: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = SilentPadColors.textPlaceholder) },
        modifier = modifier
            .width(width)
            .height(height),
        shape = RoundedCornerShape(0.dp),
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
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = SilentPadColors.textPrimary,
            unfocusedTextColor = SilentPadColors.textPrimary,
            focusedBorderColor = SilentPadColors.inputBackground,
            unfocusedBorderColor = SilentPadColors.inputBackground,
            focusedContainerColor = SilentPadColors.inputBackground,
            unfocusedContainerColor = SilentPadColors.inputBackground,
            cursorColor = SilentPadColors.textPrimary
        ),
        singleLine = true
    )
}