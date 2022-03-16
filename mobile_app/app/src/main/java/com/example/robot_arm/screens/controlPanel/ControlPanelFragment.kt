package com.example.robot_arm.screens.controlPanel

import android.Manifest
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.robot_arm.R
import com.example.robot_arm.databinding.ControlpanelFragmentBinding
import com.example.robot_arm.model.BtFactory

class ControlPanelFragment : Fragment() {
    private lateinit var binding: ControlpanelFragmentBinding
    private lateinit var viewModel: ControlPanelViewModel

    private val btAdapter = BtFactory().getAdapter()

    private val REQUEST_ENABLE_BT = 1
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

    private fun connectBluetooth() {
        // The context is null until onCreateView() completes. If, somehow, we get here before
        // then we simply exit. Logging could be added before returning to help with debugging
        val context = context ?: return
        val permission = ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT)

        if (permission == PackageManager.PERMISSION_GRANTED) {
            Log.i("ControlPanelFragment", "Locating bluetooth devices")
            val pairedDevices: Set<BluetoothDevice>? = btAdapter?.bondedDevices
            pairedDevices?.forEach { device ->
                val deviceName = device.name
                val deviceHardwareAddress = device.address // MAC address
                if (deviceHardwareAddress == pi_mac) {
                    Log.i("ControlPanelFragment", "Found paired Pi - $deviceName")
                }
            }
        } else {
            Log.i("ControlPanelFragment", "Access to bluetooth denied")
            if (shouldShowRequestPermissionRationale(Manifest.permission.BLUETOOTH_CONNECT)) {
                Log.i(
                    "ControlPanelFragment",
                    "Permission was previously denied, show rationale for the permission"
                )
                showRationale()
            } else {
                Log.i("ControlPanelFragment", "Request bluetooth permission from the user")
                requestBluetoothPermission()
            }
        }
    }

    private fun showRationale() {
        // The context is null until onCreateView() completes. If, somehow, we get here before
        // then we simply exit. Logging could be added before returning to help with debugging
        val context = context ?: return
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Permission to access bluetooth is required so that paired devices can be accessed")
            .setTitle("Permission Required")
        builder.setPositiveButton("OK") { _, _ ->
            Log.i("ControlPanelFragment", "User has read location permission rationale")
            requestBluetoothPermission()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun requestBluetoothPermission() {
        requestPermissions(arrayOf(Manifest.permission.BLUETOOTH_CONNECT), REQUEST_ENABLE_BT)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_ENABLE_BT -> {
                if (grantResults.isNotEmpty() &&
                    (permissions[0] == Manifest.permission.BLUETOOTH_CONNECT) &&
                    (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    Log.i(
                        "ControlPanelFragment",
                        "User has granted permission to allow access bluetooth"
                    )
                    connectBluetooth()
                }
            }
        }
    }

    fun getDevice()
    {
    }
}
