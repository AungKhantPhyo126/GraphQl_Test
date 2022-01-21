package com.rts.graphqltest.launchList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloNetworkException
import com.rts.graphqltest.ViewState
import com.rts.graphqltest.domain.Launch
import com.rts.graphqltest.repository.LaunchListRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchListViewModel @Inject constructor(
    private val launchListRepo: LaunchListRepo
) :
    ViewModel() {

    private val _viewStateLive = MutableLiveData<ViewState<List<Launch>>>()
    val viewStateLive: LiveData<ViewState<List<Launch>>>
        get() = _viewStateLive


    private fun fetchLaunchList() {
        viewModelScope.launch {
            launchListRepo.fetchLaunchListFromNetwork().fold({
               getLaunchList()
            },{
                if (it is ApolloNetworkException){
                    getLaunchList()
                }else{
                    _viewStateLive.value=ViewState.Error(it)
                }
            })
        }
    }

    private fun getLaunchList(){
        viewModelScope.launch {
            _viewStateLive.value=ViewState.Loading
            _viewStateLive.value=ViewState.Success(launchListRepo.getLaunchListFromDb())
        }
    }

    init {
        fetchLaunchList()
    }
}