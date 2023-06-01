package com.jony.mazaadytask.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jony.mazaadytask.data.model.response.GetAllCatsResponse
import com.jony.mazaadytask.data.model.response.GetOptionsChildResponse
import com.jony.mazaadytask.data.model.response.PropertiesCatResponse
import com.jony.mazaadytask.domain.entity.Resource
import com.jony.mazaadytask.domain.usecase.GetAllCatsUseCase
import com.jony.mazaadytask.domain.usecase.GetOptionsUseCase
import com.jony.mazaadytask.domain.usecase.GetPropertiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetAllViewModel @Inject constructor(
    private val mGetAllCatsUseCase: GetAllCatsUseCase,
    private val mGetPropertiesUseCase: GetPropertiesUseCase,
    private val mGetOptionsUseCase: GetOptionsUseCase
) : ViewModel() {
    private val getAllCatsLiveData: MutableLiveData<Resource<GetAllCatsResponse?>> = MutableLiveData()
    private val getPropertiesLiveData: MutableLiveData<Resource<PropertiesCatResponse?>> = MutableLiveData()
    private val getOptionLiveData: MutableLiveData<Resource<GetOptionsChildResponse?>> = MutableLiveData()

    init {
        getAllCatsData()

    }

    private fun getAllCatsData() = viewModelScope.launch(Dispatchers.IO) {
        try {
            getAllCatsLiveData.postValue((Resource.loading()))
            val result = mGetAllCatsUseCase.execute()
            getAllCatsLiveData.postValue(result)
        } catch (e: Exception) {
            getAllCatsLiveData.postValue(Resource.domainError(e))
        }
    }

    fun getPropertiesCatsData(catId: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            getPropertiesLiveData.postValue((Resource.loading()))
            val result = mGetPropertiesUseCase.execute(catId)
            getPropertiesLiveData.postValue(result)
        } catch (e: Exception) {
            getPropertiesLiveData.postValue(Resource.domainError(e))
        }
    }
     fun getOptionData(optionId: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            getOptionLiveData.postValue((Resource.loading()))
            val result = mGetOptionsUseCase.execute(optionId)
            getOptionLiveData.postValue(result)
        } catch (e: Exception) {
            getOptionLiveData.postValue(Resource.domainError(e))
        }
    }
    fun getAllCatsLiveData(): LiveData<Resource<GetAllCatsResponse?>> = getAllCatsLiveData
    fun getPropertiesLiveData(): LiveData<Resource<PropertiesCatResponse?>> = getPropertiesLiveData
    fun getOptionLiveData(): LiveData<Resource<GetOptionsChildResponse?>> = getOptionLiveData
}