package com.jony.mazaadytask.data.model.response


import android.text.Editable
import com.google.gson.annotations.SerializedName

data class PropertiesCatResponse(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("data")
    val `data`: List<Data?>? = null,
    @SerializedName("msg")
    val msg: String? = null
) {
    data class Data(
        @SerializedName("description")
        val description: String? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("list")
        val list: Boolean? = null,
        @SerializedName("name")
        val name: Editable? = null,
        @SerializedName("options")
        val options: List<Option?>? = null,
        @SerializedName("other_value")
        val otherValue: Any? = null,
        @SerializedName("parent")
        val parent: Any? = null,
        @SerializedName("slug")
        val slug: String? = null,
        @SerializedName("type")
        val type: String? = null,
        @SerializedName("value")
        val value: String? = null
    ) {
        data class Option(
            @SerializedName("child")
            val child: Boolean? = null,
            @SerializedName("id")
            val id: Int? = null,
            @SerializedName("name")
            val name: Editable? = null,
            @SerializedName("parent")
            val parent: Int? = null,
            @SerializedName("slug")
            val slug: String? = null
        )
    }
}