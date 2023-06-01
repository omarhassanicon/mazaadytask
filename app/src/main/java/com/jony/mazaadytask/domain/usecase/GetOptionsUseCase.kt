package com.jony.mazaadytask.domain.usecase

import com.jony.mazaadytask.data.model.response.GetOptionsChildResponse
import com.jony.mazaadytask.data.remote.ApiServices
import com.jony.mazaadytask.domain.entity.Resource
import com.jony.mazaadytask.domain.mapper.ResourceMapper
import javax.inject.Inject

class GetOptionsUseCase @Inject constructor(
    private val apiServices: ApiServices,
    private val mapper: ResourceMapper<GetOptionsChildResponse?>
){
    suspend fun execute(optionId: Int): Resource<GetOptionsChildResponse?> = mapper.map(apiServices.getOptionsChild(optionId))
}