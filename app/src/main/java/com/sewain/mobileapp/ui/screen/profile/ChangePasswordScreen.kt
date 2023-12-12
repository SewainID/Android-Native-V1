package com.sewain.mobileapp.ui.screen.profile

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sewain.mobileapp.R
import com.sewain.mobileapp.ui.theme.Gray700
import com.sewain.mobileapp.ui.theme.MidnightBlue
import com.sewain.mobileapp.ui.theme.RoyalBlue
import com.sewain.mobileapp.ui.theme.SewainAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeScreenPasswordScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // State for input
    var inputCurrentPassword by remember { mutableStateOf("") }
    var inputNewPassword by remember { mutableStateOf("") }
    var inputRepeatNewPassword by remember { mutableStateOf("") }
    val passwordHidden by rememberSaveable { mutableStateOf(true) }

    var loading by remember { mutableStateOf(false) }
    var success by remember { mutableStateOf(false) }
    var enabled by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = modifier
                    .size(50.dp)
                    .clickable {
                        navController.navigateUp()
                    }
            )

            Text(
                text = stringResource(R.string.change_password),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.align(Alignment.Center)
            )
        }

        Image(
            painter = painterResource(R.drawable.change_password),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier
                .padding(top = 24.dp)
                .size(200.dp)
        )

        Text(
            text = stringResource(R.string.password_page_message),
            fontSize = 13.sp,
            modifier = modifier.padding(top = 16.dp),
            textAlign = TextAlign.Center,
        )

        OutlinedTextField(
            value = inputCurrentPassword,
            onValueChange = { newInput ->
                inputCurrentPassword = newInput
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            textStyle = TextStyle(
                color = MidnightBlue,
                fontSize = 20.sp
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.current_password),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = null,
                    tint = Gray700
                )
            },
            visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                cursorColor = RoyalBlue,
                selectionColors = TextSelectionColors(
                    handleColor = RoyalBlue,
                    backgroundColor = RoyalBlue
                ),
                focusedIndicatorColor = RoyalBlue,
            )
        )

        OutlinedTextField(
            value = inputNewPassword,
            onValueChange = { newInput ->
                inputNewPassword = newInput
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            textStyle = TextStyle(
                color = MidnightBlue,
                fontSize = 20.sp
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.new_password),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = null,
                    tint = Gray700
                )
            },
            visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                cursorColor = RoyalBlue,
                selectionColors = TextSelectionColors(
                    handleColor = RoyalBlue,
                    backgroundColor = RoyalBlue
                ),
                focusedIndicatorColor = RoyalBlue,
            )
        )

        OutlinedTextField(
            value = inputRepeatNewPassword,
            onValueChange = { newInput ->
                inputRepeatNewPassword = newInput
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            textStyle = TextStyle(
                color = MidnightBlue,
                fontSize = 20.sp
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.repeat_new_password),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = null,
                    tint = Gray700
                )
            },
            visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                cursorColor = RoyalBlue,
                selectionColors = TextSelectionColors(
                    handleColor = RoyalBlue,
                    backgroundColor = RoyalBlue
                ),
                focusedIndicatorColor = RoyalBlue,
            )
        )

        Button(
            onClick = { },
            modifier
                .padding(top = 48.dp)
                .fillMaxWidth()
                .height(50.dp),
            enabled = enabled,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            if (loading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                Text(
                    text = stringResource(R.string.change_password),
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun ChangeScreenPasswordScreenPreview() {
    SewainAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ChangeScreenPasswordScreen(
                navController = rememberNavController(),
            )
        }
    }
}