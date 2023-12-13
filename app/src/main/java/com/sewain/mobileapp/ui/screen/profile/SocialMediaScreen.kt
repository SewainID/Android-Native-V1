package com.sewain.mobileapp.ui.screen.profile

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sewain.mobileapp.R
import com.sewain.mobileapp.ui.theme.CharcoalGray
import com.sewain.mobileapp.ui.theme.SewainAppTheme

@Composable
fun SocialMediaScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    var loading by remember { mutableStateOf(false) }
    var success by remember { mutableStateOf(false) }
    var enabled by remember { mutableStateOf(true) }

    Column(
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
                text = stringResource(R.string.social_media),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.align(Alignment.Center)
            )
        }

        Image(
            painter = painterResource(R.drawable.social_media),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier
                .padding(top = 24.dp)
                .size(200.dp)
                .align(CenterHorizontally),
        )

        Text(
            text = stringResource(R.string.social_media_message),
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(top = 18.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(top = 72.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.facebook),
                contentDescription = null,
                modifier = modifier.size(36.dp),
            )

            Text(
                text = stringResource(R.string.facebook_connect),
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(start = 8.dp),
            )

            InputChip(
                selected = true,
                onClick = { },
                label = {
                    Text(
                        text = stringResource(R.string.connect),
                        fontSize = 12.sp,
                    )
                },
                modifier = modifier
                    .padding(start = 8.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                    )
                },
                colors = FilterChipDefaults.filterChipColors(containerColor = CharcoalGray)
            )

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(top = 4.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.instagram),
                contentDescription = null,
                modifier = modifier.size(36.dp),
            )

            Text(
                text = stringResource(R.string.instagram_connect),
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(start = 8.dp),
            )

            InputChip(
                selected = true,
                onClick = { },
                label = {
                    Text(
                        text = stringResource(R.string.connect),
                        fontSize = 12.sp,
                    )
                },
                modifier = modifier.padding(start = 8.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                    )
                },
                colors = FilterChipDefaults.filterChipColors(containerColor = CharcoalGray)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(top = 4.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.tiktok),
                contentDescription = null,
                modifier = modifier.size(36.dp),
            )

            Text(
                text = stringResource(R.string.tiktok_connect),
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(start = 8.dp),
            )

            InputChip(
                selected = true,
                onClick = { },
                label = {
                    Text(
                        text = stringResource(R.string.connect),
                        fontSize = 12.sp,
                    )
                },
                modifier = modifier.padding(start = 28.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                    )
                },
                colors = FilterChipDefaults.filterChipColors(containerColor = CharcoalGray)
            )
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
fun SocialMediaScreenPreview() {
    SewainAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            SocialMediaScreen(
                navController = rememberNavController(),
            )
        }
    }
}