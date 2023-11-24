package com.sewain.mobileapp.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sewain.mobileapp.R
import com.sewain.mobileapp.ui.theme.DarkPurple
import com.sewain.mobileapp.ui.theme.Purple500
import com.sewain.mobileapp.ui.theme.Purple700
import com.sewain.mobileapp.ui.theme.PurpleGrey40
import com.sewain.mobileapp.ui.theme.SewainAppTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToRegister: () -> Unit,
) {
    LoginContent(
        modifier = modifier,
        navigateToRegister = navigateToRegister
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    modifier: Modifier,
    navigateToRegister: () -> Unit,
) {
    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    val passwordHidden by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Box(
                modifier = modifier
                    .offset((-20).dp, 20.dp)
                    .requiredSize(250.dp, 400.dp)
                    .blur(
                        radius = 200.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded
                    )
                    .background(Purple500)
            )

            Box(
                modifier = modifier
                    .offset(175.dp, (-100).dp)
                    .requiredSize(250.dp, 400.dp)
                    .blur(
                        radius = 200.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded
                    )
                    .background(Purple700)
            )

            Box(
                modifier = modifier
                    .offset(200.dp, 180.dp)
                    .requiredSize(300.dp, 250.dp)
                    .blur(
                        radius = 200.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded
                    )
                    .background(DarkPurple)
            )

            Column(modifier = modifier.fillMaxSize()) {
                Box(
                    modifier = modifier
                        .padding(top = 50.dp)
                        .size(108.dp)
                        .clip(CircleShape)
                        .align(CenterHorizontally)
                        .background(Color.Black)
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_sewain),
                        contentDescription = null,
                        modifier = modifier
                            .size(70.dp)
                            .align(Alignment.Center),
                    )
                }

                Text(
                    text = stringResource(R.string.sign_in),
                    modifier = modifier
                        .padding(top = 32.dp)
                        .align(CenterHorizontally),
                    color = Color.Black,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = stringResource(R.string.sign_in_message),
                    modifier = modifier
                        .align(CenterHorizontally),
                    color = Color.Black,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Normal
                )

                OutlinedTextField(
                    value = inputEmail,
                    onValueChange = { newInputEmail ->
                        inputEmail = newInputEmail
                    },
                    modifier = modifier
                        .padding(
                            top = 48.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                        .size(360.dp, 58.dp)
                        .align(CenterHorizontally),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.email),
                            fontSize = 18.sp
                        )
                    },
                    trailingIcon = {
                        Icon(
                            Icons.Outlined.Email,
                            contentDescription = null,
                            tint = PurpleGrey40
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(textColor = PurpleGrey40)
                )

                OutlinedTextField(
                    value = inputPassword,
                    onValueChange = { newInputPassword ->
                        inputPassword = newInputPassword
                    },
                    modifier = modifier
                        .padding(
                            top = 13.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                        .size(360.dp, 58.dp)
                        .align(CenterHorizontally),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.password),
                            fontSize = 18.sp
                        )
                    },
                    trailingIcon = {
                        Icon(
                            Icons.Outlined.Lock,
                            contentDescription = null,
                            tint = PurpleGrey40
                        )
                    },
                    visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(textColor = PurpleGrey40)
                )

                TextButton(
                    onClick = { },
                    modifier = modifier
                        .padding(end = 8.dp)
                        .align(End)
                ) {
                    Text(
                        stringResource(R.string.forgot_password),
                        color = Color.Black
                    )
                }

                Button(
                    onClick = { },
                    modifier
                        .padding(
                            top = 16.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                        .size(360.dp, 58.dp)
                        .align(CenterHorizontally),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(
                        text = stringResource(R.string.sign_in),
                        fontSize = 18.sp
                    )
                }

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Divider(
                        modifier = modifier
                            .width(150.dp)
                            .padding(start = 16.dp)
                            .align(CenterVertically),
                        thickness = 1.dp,
                        color = Color.Black
                    )

                    Text(
                        text = stringResource(R.string.or)
                    )

                    Divider(
                        modifier = modifier
                            .width(150.dp)
                            .padding(end = 16.dp)
                            .align(CenterVertically),
                        thickness = 1.dp,
                        color = Color.Black
                    )
                }

                TextButton(
                    onClick = { navigateToRegister() },
                    modifier = modifier
                        .padding(top = 16.dp)
                        .align(CenterHorizontally)
                ) {
                    Text(
                        stringResource(R.string.create_new_account),
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL
)
@Composable
fun PreviewLoginScreen() {
    SewainAppTheme {
        LoginScreen(navigateToRegister = { })
    }
}