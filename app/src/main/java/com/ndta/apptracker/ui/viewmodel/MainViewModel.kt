package com.ndta.apptracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ndta.apptracker.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

}