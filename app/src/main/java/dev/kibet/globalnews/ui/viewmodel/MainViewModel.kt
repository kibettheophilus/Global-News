package dev.kibet.globalnews.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.kibet.globalnews.data.model.Article
import dev.kibet.globalnews.repository.NewsRepository
import dev.kibet.globalnews.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {
    private val _getHeadLinesStatus = MutableLiveData<Resource<Article>>()
    val getHeadlinesStatus: LiveData<Resource<Article>> = _getHeadLinesStatus


    private val _getBusinessNewsStatus = MutableLiveData<Resource<Article>>()
    val getBusinessNewsStatus: LiveData<Resource<Article>> = _getBusinessNewsStatus

    fun getHeadlines(){
        _getHeadLinesStatus.postValue(Resource.loading(null))

        viewModelScope.launch {
            val result = repository.getHeadlines()

            _getHeadLinesStatus.postValue(result)
        }
    }

    fun getBusinessNews(){
        _getBusinessNewsStatus.postValue(Resource.loading(null))

        viewModelScope.launch {
            val result = repository.getBusinessNews()

            _getBusinessNewsStatus.postValue(result)
        }
    }
}