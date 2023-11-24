package com.sewain.mobileapp.ui.screen.register

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.sewain.mobileapp.R
import com.sewain.mobileapp.data.remote.model.Register
import com.sewain.mobileapp.di.Injection
import com.sewain.mobileapp.ui.ViewModelFactory
import com.sewain.mobileapp.ui.theme.DarkPurple
import com.sewain.mobileapp.ui.theme.Purple500
import com.sewain.mobileapp.ui.theme.Purple700
import com.sewain.mobileapp.ui.theme.PurpleGrey40
import com.sewain.mobileapp.ui.theme.SewainAppTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideUserRepository(LocalContext.current))
    ),
    navigateToLogin: () -> Unit
) {
    var inputUsername by remember { mutableStateOf("") }
    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    var inputConfirmPassword by remember { mutableStateOf("") }
    val passwordHidden by rememberSaveable { mutableStateOf(true) }

    RegisterContent(
        modifier = modifier,
        inputUsername = inputUsername,
        onInputUsername = { newInputUsername ->
            inputUsername = newInputUsername
        },
        inputEmail = inputEmail,
        onInputEmail = { newInputEmail ->
            inputEmail = newInputEmail
        },
        inputPassword = inputPassword,
        onInputPassword = { newInputPassword ->
            inputPassword = newInputPassword
        },
        inputConfirmPassword = inputConfirmPassword,
        onInputConfirmPassword = { newInputConfirmPassword ->
            inputConfirmPassword = newInputConfirmPassword
        },
        passwordHidden = passwordHidden,
        navigateToLogin = navigateToLogin,
        onClickRegister = {
            viewModel.register(inputUsername, inputEmail, inputPassword)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(
    modifier: Modifier,
    inputUsername: String,
    onInputUsername: (String) -> Unit,
    inputEmail: String,
    onInputEmail: (String) -> Unit,
    inputPassword: String,
    onInputPassword: (String) -> Unit,
    inputConfirmPassword: String,
    onInputConfirmPassword: (String) -> Unit,
    passwordHidden: Boolean,
    navigateToLogin: () -> Unit,
    onClickRegister: () -> Unit,
) {
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
                        .align(Alignment.CenterHorizontally)
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
                    text = stringResource(R.string.sign_up),
                    modifier = modifier
                        .padding(top = 32.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color.Black,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = stringResource(R.string.sign_up_message),
                    modifier = modifier
                        .align(Alignment.CenterHorizontally),
                    color = Color.Black,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Normal
                )

                OutlinedTextField(
                    value = inputUsername,
                    onValueChange = onInputUsername,
                    modifier = modifier
                        .padding(
                            top = 48.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                        .size(360.dp, 58.dp)
                        .align(Alignment.CenterHorizontally),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.username),
                            fontSize = 18.sp
                        )
                    },
                    trailingIcon = {
                        Icon(
                            Icons.Outlined.Person,
                            contentDescription = null,
                            tint = PurpleGrey40
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(textColor = PurpleGrey40)
                )

                OutlinedTextField(
                    value = inputEmail,
                    onValueChange = onInputEmail,
                    modifier = modifier
                        .padding(
                            top = 13.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                        .size(360.dp, 58.dp)
                        .align(Alignment.CenterHorizontally),
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
                    onValueChange = onInputPassword,
                    modifier = modifier
                        .padding(
                            top = 13.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                        .size(360.dp, 58.dp)
                        .align(Alignment.CenterHorizontally),
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

                OutlinedTextField(
                    value = inputConfirmPassword,
                    onValueChange = onInputConfirmPassword,
                    modifier = modifier
                        .padding(
                            top = 13.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                        .size(360.dp, 58.dp)
                        .align(Alignment.CenterHorizontally),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.confirm_password),
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

                Button(
                    onClick = onClickRegister,
                    modifier
                        .padding(
                            top = 19.dp,
                            start = 8.dp,
                            end = 8.dp
                        )
                        .size(360.dp, 58.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(
                        text = stringResource(R.string.sign_up),
                        fontSize = 18.sp
                    )
                }

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            top = 8.dp
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.already_account_message),
                        modifier = modifier.padding(start = 16.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )

                    TextButton(
                        onClick = { navigateToLogin() },
                        modifier = modifier.padding(end = 8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.sign_in),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                    }
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
fun PreviewRegisterScreen() {
    SewainAppTheme {
        RegisterScreen(navigateToLogin = { })
    }
}