package com.sewain.mobileapp.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatalogsResponse(
	val count: Int? = null,
	val pageContext: PageContext? = null,
	val results: List<CatalogItem> = emptyList(),
) : Parcelable

@Parcelize
data class CatalogItem(
	val dayRent: Int? = null,
	val size: String? = null,
	val price: Int? = null,
	val name: String? = null,
	val photoUrl: String? = null,
	val id: String
) : Parcelable

@Parcelize
data class PageContext(
	val perPage: Int? = null,
	val page: Int? = null,
	val totalPages: Int? = null
) : Parcelable
