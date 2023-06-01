package com.jony.mazaadytask.domain.mapper

import android.content.Context
import com.jony.mazaadytask.R
import com.jony.mazaadytask.domain.entity.Resource
import com.jony.mazaadytask.domain.entity.Resource.Companion.apiError
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject

class ResourceMapper<DATA> @Inject constructor(
    @ApplicationContext val appContext: Context,
) {
    fun map(response: Response<DATA?>): Resource<DATA?> {
        val r: Resource<DATA?> = when {
            response.code() == 200 -> Resource.success(response.body())
            response.code() != 200 -> Resource.apiError(response.message(), response.code())
            else -> Resource.apiError(appContext.getString(R.string.something_went_wrong), response.code())
        }
        return r
    }
}