package com.sewain.mobileapp.ui.screen.profile

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sewain.mobileapp.R
import com.sewain.mobileapp.data.local.model.SessionModel
import com.sewain.mobileapp.ui.theme.Gray700
import com.sewain.mobileapp.ui.theme.MidnightBlue
import com.sewain.mobileapp.ui.theme.RoyalBlue
import com.sewain.mobileapp.ui.theme.SewainAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProfileScreen(
    sessionModel: SessionModel,
    modifier: Modifier = Modifier,
) {
    var inputFullName by remember { mutableStateOf(sessionModel.username) }
    var inputEmail by remember { mutableStateOf(sessionModel.email) }
    var inputNumber by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.profile),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Box(
            modifier = modifier
                .padding(top = 24.dp)
                .clickable { },
            contentAlignment = Center
        ) {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                alpha = 0.5f,
                modifier = modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(Gray700)
            )

            Text(
                text = "Click to\nChange",
                color = Color.White
            )
        }



        Text(
            text = stringResource(R.string.full_name),
            fontSize = 16.sp,
            modifier = modifier
                .padding(start = 4.dp, top = 72.dp)
                .align(Start),
        )

        OutlinedTextField(
            value = inputFullName,
            onValueChange = { newInput ->
                inputFullName = newInput
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            textStyle = TextStyle(
                color = MidnightBlue,
                fontSize = 20.sp
            ),
            placeholder = {
                Text(
                    text = sessionModel.username,
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

        Text(
            text = stringResource(R.string.email),
            fontSize = 16.sp,
            modifier = modifier
                .padding(start = 4.dp, top = 16.dp)
                .align(Start),
        )

        OutlinedTextField(
            value = inputEmail,
            onValueChange = { newInput ->
                inputEmail = newInput
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            textStyle = TextStyle(
                color = MidnightBlue,
                fontSize = 20.sp
            ),
            placeholder = {
                Text(
                    text = sessionModel.email,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            trailingIcon = {
                Icon(
                    Icons.Outlined.Email,
                    contentDescription = null,
                    tint = Gray700
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

        Text(
            text = stringResource(R.string.number),
            fontSize = 16.sp,
            modifier = modifier
                .padding(start = 4.dp, top = 16.dp)
                .align(Start),
        )

        OutlinedTextField(
            value = inputNumber,
            onValueChange = { newInput ->
                inputNumber = newInput
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            textStyle = TextStyle(
                color = MidnightBlue,
                fontSize = 20.sp
            ),
            placeholder = {
                Text(
                    text = "+62 888 8888 8888",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            trailingIcon = {
                Icon(
                    Icons.Outlined.Phone,
                    contentDescription = null,
                    tint = Gray700
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
                    text = stringResource(R.string.save),
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
fun DetailProfileScreenPreview() {
    SewainAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            DetailProfileScreen(SessionModel("", "", ""))
        }
    }
}