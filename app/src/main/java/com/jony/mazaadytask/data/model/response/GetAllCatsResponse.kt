package com.jony.mazaadytask.data.model.response


import com.google.gson.annotations.SerializedName

data class GetAllCatsResponse(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("msg")
    val msg: String? = null
) {
    data class Data(
        @SerializedName("ads_banners")
        val adsBanners: List<AdsBanner?>? = null,
        @SerializedName("categories")
        val categories: List<Category?>? = null,
        @SerializedName("google_version")
        val googleVersion: String? = null,
        @SerializedName("huawei_version")
        val huaweiVersion: String? = null,
        @SerializedName("ios_latest_version")
        val iosLatestVersion: String? = null,
        @SerializedName("ios_version")
        val iosVersion: String? = null,
        @SerializedName("statistics")
        val statistics: Statistics? = null
    ) {
        data class AdsBanner(
            @SerializedName("duration")
            val duration: Int? = null,
            @SerializedName("img")
            val img: String? = null,
            @SerializedName("media_type")
            val mediaType: String? = null
        )

        data class Category(
            @SerializedName("children")
            val children: List<Children?>? = null,
            @SerializedName("circle_icon")
            val circleIcon: String? = null,
            @SerializedName("description")
            val description: Any? = null,
            @SerializedName("disable_shipping")
            val disableShipping: Int? = null,
            @SerializedName("id")
            val id: Int? = null,
            @SerializedName("image")
            val image: String? = null,
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("slug")
            val slug: String? = null
        ) {
            data class Children(
                @SerializedName("children")
                val children: Any? = null,
                @SerializedName("circle_icon")
                val circleIcon: String? = null,
                @SerializedName("description")
                val description: Any? = null,
                @SerializedName("disable_shipping")
                val disableShipping: Int? = null,
                @SerializedName("id")
                val id: Int? = null,
                @SerializedName("image")
                val image: String? = null,
                @SerializedName("name")
                val name: String? = null,
                @SerializedName("slug")
                val slug: String? = null
            )
        }

        data class Statistics(
            @SerializedName("auctions")
            val auctions: Int? = null,
            @SerializedName("products")
            val products: Int? = null,
            @SerializedName("users")
            val users: Int? = null
        )
    }
}