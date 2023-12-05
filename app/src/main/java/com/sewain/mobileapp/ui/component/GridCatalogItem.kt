package com.sewain.mobileapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sewain.mobileapp.data.local.entity.CatalogEntity
import com.sewain.mobileapp.utils.rp

@Composable
fun GridCatalogItem (item : CatalogEntity){
    Card(
        modifier = Modifier.padding(4.dp),
    ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        AsyncImage(
            model = item.photoUrl, contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .size(150.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Text(
            text = item.name ?: "",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Size ${item.size}",
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        Text(
            text = "${item.price?.rp() ?: 0} / ${item.dayRent ?: 0} Days",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
    }
}

@Preview
@Composable
fun PreviewCatalogItem(){
    GridCatalogItem(CatalogEntity(dayRent = 3, name = "Gojo SAtoru", price = 100000.0, photoUrl = "https://storage.googleapis.com/sewain/etc/sample.jpg"))
}
