package com.sewain.mobileapp.ui.screen.profile

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.sewain.mobileapp.R
import com.sewain.mobileapp.data.local.model.SessionModel
import com.sewain.mobileapp.di.Injection
import com.sewain.mobileapp.ui.ViewModelFactory
import com.sewain.mobileapp.ui.component.DialogScreen
import com.sewain.mobileapp.ui.navigation.Screen
import com.sewain.mobileapp.ui.theme.LightBlueGray
import com.sewain.mobileapp.ui.theme.SalmonPink
import com.sewain.mobileapp.ui.theme.SewainAppTheme

@Composable
fun ProfileScreen(
    navController: NavController,
    sessionModel: SessionModel,
    viewModel: ProfileViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideUserRepository(LocalContext.current))
    ),
    modifier: Modifier = Modifier,
) {
    val openDialog = remember { mutableStateOf(false) }

    viewModel.getUserById(sessionModel.id)

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
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
        )

        // Condition photo profile
        if (false) {
            AsyncImage(
                model = "",
                contentDescription = null,
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                modifier = modifier
                    .padding(top = 24.dp)
                    .size(150.dp)
                    .clip(CircleShape),
            )
        } else {
            Image(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center,
                modifier = modifier
                    .padding(top = 24.dp)
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            )
        }

        Text(
            text = "Full Name",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(top = 24.dp)
        )

        Text(
            text = stringResource(R.string.username_profile_format, viewModel.username.value),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = modifier.padding(top = 4.dp)
        )

        Text(
            text = viewModel.email.value,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary,
            modifier = modifier.padding(top = 4.dp)
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .height(50.dp)
                .border(1.dp, LightBlueGray, RoundedCornerShape(8.dp))
                .clickable { },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.switch_account),
                modifier = modifier.padding(start = 16.dp),
                color = MaterialTheme.colorScheme.secondary
            )

            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = modifier.padding(end = 16.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .height(50.dp)
                .border(1.dp, LightBlueGray, RoundedCornerShape(8.dp))
                .clickable { navController.navigate(Screen.DetailProfile.createRoute(sessionModel.id)) },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.edit_profile),
                modifier = modifier.padding(start = 16.dp),
                color = MaterialTheme.colorScheme.secondary
            )

            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = modifier.padding(end = 16.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .height(50.dp)
                .border(1.dp, LightBlueGray, RoundedCornerShape(8.dp))
                .clickable { navController.navigate(Screen.ChangePassword.route) },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.change_password),
                modifier = modifier.padding(start = 16.dp),
                color = MaterialTheme.colorScheme.secondary
            )

            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = modifier.padding(end = 16.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .height(50.dp)
                .border(1.dp, LightBlueGray, RoundedCornerShape(8.dp))
                .clickable { },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.add_addresses),
                modifier = modifier.padding(start = 16.dp),
                color = MaterialTheme.colorScheme.secondary
            )

            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = modifier.padding(end = 16.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .height(50.dp)
                .border(1.dp, LightBlueGray, RoundedCornerShape(8.dp))
                .clickable { },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.add_social_media),
                modifier = modifier.padding(start = 16.dp),
                color = MaterialTheme.colorScheme.secondary
            )

            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = modifier.padding(end = 16.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .height(50.dp)
                .border(1.dp, SalmonPink, RoundedCornerShape(8.dp))
                .clickable { openDialog.value = true },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.logout),
                modifier = modifier.padding(start = 16.dp),
                color = SalmonPink
            )

            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = modifier.padding(end = 16.dp),
                tint = SalmonPink
            )
        }

    }

    when {
        openDialog.value -> {
            DialogScreen(
                onDismissRequest = { openDialog.value = false },
                onConfirmation = {
                    viewModel.logout()
                    navController.navigate(Screen.Login.route)
                    openDialog.value = false
                },
                message = R.string.logout_message,
                icon = Icons.Outlined.Info
            )
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL,
    uiMode = UI_MODE_NIGHT_NO,
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL,
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
fun PreviewProfileScreen() {
    SewainAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ProfileScreen(
                navController = rememberNavController(),
                sessionModel = SessionModel("", "")
            )
        }
    }
}