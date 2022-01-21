package com.rts.spacex_launch.launchDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rts.spacex_launch.domain.Launch
import com.rts.spacex_launch.repository.LaunchListRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchDetailViewModel @Inject constructor(
    private val launchListRepo: LaunchListRepo
):ViewModel() {
    private val _launchDetails = MutableLiveData<Launch>()
    val launchDetails: LiveData<Launch>
        get() = _launchDetails
    fun getLaunchDetail(id:String){
        viewModelScope.launch {
            _launchDetails.value=launchListRepo.getLaunchDetails(id)
        }
    }
}