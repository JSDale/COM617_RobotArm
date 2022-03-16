package com.example.robot_arm.screens.controlPanel

import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.util.Log
import androidx.lifecycle.AndroidViewModel

class ControlPanelViewModel(application: Application) : AndroidViewModel(application) {

    fun onShoulderUp() {
        Log.i("ControlPanelViewModel", "onShoulderUp")
    }

    fun onShoulderDown() {
        Log.i("ControlPanelViewModel", "onShoulderDown")
    }

    fun onRotateLeft() {
        Log.i("ControlPanelViewModel", "onRotateLeft")
    }

    fun onRotateRight() {
        Log.i("ControlPanelViewModel", "onRotateRight")
    }

    fun onElbowUp() {
        Log.i("ControlPanelViewModel", "onShoulderUp")
    }

    fun onElbowDown() {
        Log.i("ControlPanelViewModel", "onShoulderDown")
    }

    fun onWristUp() {
        Log.i("ControlPanelViewModel", "onShoulderUp")
    }

    fun onWristDown() {
        Log.i("ControlPanelViewModel", "onShoulderDown")
    }

    fun onGripOpen() {
        Log.i("ControlPanelViewModel", "onShoulderUp")
    }

    fun onGripClosed() {
        Log.i("ControlPanelViewModel", "onShoulderDown")
    }
}
