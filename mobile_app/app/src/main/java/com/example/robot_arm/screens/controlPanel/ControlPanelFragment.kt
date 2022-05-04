package com.example.robot_arm.screens.controlPanel

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.robot_arm.R
import com.example.robot_arm.databinding.ControlpanelFragmentBinding
import com.example.robot_arm.model.BtFactory
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.annotations.AfterPermissionGranted

const val REQUEST_ENABLE_BT: Int = 1
const val UUID_STRING: String = "2fda49c8-a1de-4196-aa2f-9c38ef98bf52"

class ControlPanelFragment : Fragment() {
    private lateinit var binding: ControlpanelFragmentBinding
    private lateinit var viewModel: ControlPanelViewModel

    private val btAdapter = BtFactory().getAdapter()
//    private var remoteDevice: BluetoothDevice? = null

    private val pi_mac = "DC:A6:32:3F:42:35"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("ControlPanelFragment", "Called ViewModelProvider.get")
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.controlpanel_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        if (btAdapter?.isEnabled == false) {
            Log.i("ControlPanelFragment", "Try to enable BT adapter")
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }

        val viewModelFactory = ControlPanelViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[ControlPanelViewModel::class.java]

        // Set up the viewModel binding so that it can handle events defined in the layout
        binding.controlPanelViewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // We should only start the bluetooth connection after onCreateView() has completed to ensure
        // that the context is set
        connectBluetooth()
    }

    // Permission checking handled by EasyPermissions wrapper, hopefully linting can be improved
    // to remove the need for suppression when using wrappers
    @SuppressLint("MissingPermission")
    @AfterPermissionGranted(REQUEST_ENABLE_BT)
    private fun connectBluetooth() {
        // The context is null until onCreateView() completes. If, somehow, we get here before
        // then we simply exit. Logging could be added before returning to help with debugging
        val context = context ?: return

        if (EasyPermissions.hasPermissions(context, Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.BLUETOOTH_SCAN))
        {
            // All good, proceed
            Log.i("ControlPanelFragment", "Locating bluetooth devices")
            val pairedDevices: Set<BluetoothDevice>? = btAdapter?.bondedDevices
            pairedDevices?.forEach { device ->
                val deviceName = device.name
                val deviceHardwareAddress = device.address // MAC address
                if (deviceHardwareAddress == pi_mac) {
                    Log.i("ControlPanelFragment", "Found paired Pi - $deviceName")
                    viewModel.initRemoteDevice(btAdapter, device)
                }
            }
        } else {
            // Request permissions
            Log.i("ControlPanelFragment", "Requesting Bluetooth permissions")
            EasyPermissions.requestPermissions(
                host = this,
                rationale = "Permission to access bluetooth is required so that paired devices can be accessed",
                requestCode = REQUEST_ENABLE_BT,
                perms = arrayOf(Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN)
            )
        }
    }

    fun getDevice()
    {
    }
}
