package com.sewain.mobileapp.ui.screen.profile

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sewain.mobileapp.R
import com.sewain.mobileapp.ui.theme.MidnightBlue
import com.sewain.mobileapp.ui.theme.RoyalBlue
import com.sewain.mobileapp.ui.theme.SewainAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeScreenPasswordScreen(
    modifier: Modifier = Modifier
) {
    // State for input
    var inputCurrentPassword by remember { mutableStateOf("") }
    var inputNewPassword by remember { mutableStateOf("") }
    var inputRepeatNewPassword by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.profile),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        Image(
            painter = painterResource(R.drawable.change_password),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier
                .size(200.dp)
        )

        Text(
            text = stringResource(R.string.password_page_message_1),
            fontSize = 13.sp,
            modifier = modifier.padding(top = 16.dp)
        )

        Text(
            text = stringResource(R.string.password_page_message_2),
            fontSize = 13.sp,
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
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            if (false) {
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
            ChangeScreenPasswordScreen()
        }
    }
}