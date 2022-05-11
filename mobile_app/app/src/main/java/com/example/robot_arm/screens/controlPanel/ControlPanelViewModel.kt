package com.example.robot_arm.screens.controlPanel

import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.robot_arm.services.*

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
        armService?.write(MessageFactory().getShoulderMessage(DirectionVertical.Up))
    }

    fun onShoulderDown() {
        Log.i("ControlPanelViewModel", "onShoulderDown")
        armService?.write(MessageFactory().getShoulderMessage(DirectionVertical.Down))
    }

    fun onRotateLeft() {
        Log.i("ControlPanelViewModel", "onRotateLeft")
        armService?.write(MessageFactory().getRotateMessage(DirectionRotate.Left))
    }

    fun onRotateRight() {
        Log.i("ControlPanelViewModel", "onRotateRight")
        armService?.write(MessageFactory().getRotateMessage(DirectionRotate.Right))
    }

    fun onElbowUp() {
        Log.i("ControlPanelViewModel", "onElbowUp")
        armService?.write(MessageFactory().getElbowMessage(DirectionVertical.Up))
    }

    fun onElbowDown() {
        Log.i("ControlPanelViewModel", "onElbowDown")
        armService?.write(MessageFactory().getElbowMessage(DirectionVertical.Down))
    }

    fun onWristUp() {
        Log.i("ControlPanelViewModel", "onWristUp")
        armService?.write(MessageFactory().getWristMessage(DirectionVertical.Up))
    }

    fun onWristDown() {
        Log.i("ControlPanelViewModel", "onWristDown")
        armService?.write(MessageFactory().getWristMessage(DirectionVertical.Down))
    }

    fun onGripOpen() {
        Log.i("ControlPanelViewModel", "onGripOpen")
        armService?.write(MessageFactory().getGripMessage(DirectionGrip.Open))
    }

    fun onGripClosed() {
        Log.i("ControlPanelViewModel", "onGripClosed")
        armService?.write(MessageFactory().getGripMessage(DirectionGrip.Close))
    }
}
