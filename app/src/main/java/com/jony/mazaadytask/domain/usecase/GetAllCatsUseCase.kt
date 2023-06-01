package com.jony.mazaadytask.domain.usecase

import com.jony.mazaadytask.data.model.response.GetAllCatsResponse
import com.jony.mazaadytask.data.remote.ApiServices
import com.jony.mazaadytask.domain.entity.Resource
import com.jony.mazaadytask.domain.mapper.ResourceMapper
import javax.inject.Inject

class GetAllCatsUseCase @Inject constructor(
    private val apiServices: ApiServices,
    private val mapper: ResourceMapper<GetAllCatsResponse?>
){
    suspend fun execute(): Resource<GetAllCatsResponse?> = mapper.map(apiServices.getAllCats())
}