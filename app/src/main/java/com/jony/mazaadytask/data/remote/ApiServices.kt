package com.jony.mazaadytask.data.remote

import com.jony.mazaadytask.data.model.response.GetAllCatsResponse
import com.jony.mazaadytask.data.model.response.GetOptionsChildResponse
import com.jony.mazaadytask.data.model.response.PropertiesCatResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiServices {
    @GET("get_all_cats")
    suspend fun getAllCats(): Response<GetAllCatsResponse?>

    @GET("properties")
    suspend fun getPropertiesCats(@Query("cat") catId: Int): Response<PropertiesCatResponse?>

    @GET("get-options-child/{option_id}")
    suspend fun getOptionsChild(@Path("option_id") optionId: Int): Response<GetOptionsChildResponse?>
}