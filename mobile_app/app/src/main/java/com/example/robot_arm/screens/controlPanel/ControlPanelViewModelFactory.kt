package com.example.robot_arm.screens.controlPanel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ControlPanelViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ControlPanelViewModel::class.java)) {
            return ControlPanelViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
