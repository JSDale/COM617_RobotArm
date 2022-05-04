package com.example.robot_arm.model

import android.bluetooth.BluetoothAdapter
import android.util.Log

class BtFactory {
    fun getAdapter(): BluetoothAdapter? {
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null) {
            Log.i("BtFactory", "Phone doesn't have bluetooth functionality")
        }
        return bluetoothAdapter
    }
}
