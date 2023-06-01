package com.jony.mazaadytask.domain.usecase

import com.jony.mazaadytask.data.model.response.PropertiesCatResponse
import com.jony.mazaadytask.data.remote.ApiServices
import com.jony.mazaadytask.domain.entity.Resource
import com.jony.mazaadytask.domain.mapper.ResourceMapper
import javax.inject.Inject

class GetPropertiesUseCase @Inject constructor(
    private val apiServices: ApiServices,
    private val mapper: ResourceMapper<PropertiesCatResponse?>
){
    suspend fun execute(catId: Int): Resource<PropertiesCatResponse?> = mapper.map(apiServices.getPropertiesCats(catId))
}