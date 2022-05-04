package com.example.robot_arm.screens.controlPanel

import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.robot_arm.services.RobotArmService

class ControlPanelViewModel(application: Application) : AndroidViewModel(application) {
    private var remoteDevice : BluetoothDevice? = null
    private var armService: RobotArmService? = null


    fun initRemoteDevice(btAdapter: BluetoothAdapter?, device: BluetoothDevice) {
        remoteDevice = device
        armService = RobotArmService(btAdapter)
        armService?.connect(device)
        Log.i("ControlPanelViewModel", "ViewModel initialised")
    }

    fun onShoulderUp() {
        Log.i("ControlPanelViewModel", "onShoulderUp")
        armService?.write(byteArrayOf(1,2,3,4,5,6,7,8,9,10))
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
