// Code based on:
// https://github.com/nocholla/Bluetooth-Android-Kotlin/blob/master/app/src/main/java/com/nocholla/androidkotlinbluetooth/services/BluetoothChatService.kt

package com.example.robot_arm.services

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import com.example.robot_arm.screens.controlPanel.UUID_STRING
import java.io.IOException
import java.io.OutputStream
import java.util.*

/**
 * This class does all the work for setting up and managing Bluetooth
 * connections with other devices. It has a thread that listens for
 * incoming connections, a thread for connecting with a device, and a
 * thread for performing data transmissions when connected.
 */

/**
 * Constructor. Prepares a new BluetoothChatActivity session.
 *
 * @param handler A Handler to send messages back to the UI Activity
 */

class RobotArmService(val mAdapter: BluetoothAdapter?) {

    // Creates a single instance of the Object. Similar to 'Static'
    companion object {
        // Unique UUID for this application
        private val MY_UUID = UUID.fromString(UUID_STRING)

        // Constants that indicate the current connection state
        const val STATE_NONE = 0       // we're doing nothing
        const val STATE_CONNECTING = 1 // now initiating an outgoing connection
        const val STATE_CONNECTED = 2  // now connected to a remote device
    }

    // Member fields
    private var mConnectThread: ConnectThread? = null
    private var mConnectedThread: ConnectedThread? = null
    private var mState: Int = 0

    /**
     * Set the current state of the chat connection
     *
     * @param state An integer defining the current connection state
     */
    // Give the new state to the Handler so the UI Activity can update
    private var state: Int
        @Synchronized get() = mState
        @Synchronized private set(state) {
            mState = state
        }

    init {
        mState = STATE_NONE
    }

    /**
     * Start the ConnectThread to initiate a connection to a remote device.
     *
     * @param device The BluetoothDevice to connect
     */
    @Synchronized
    fun connect(device: BluetoothDevice) {
        // Cancel any thread attempting to make a connection
        if (mState == STATE_CONNECTING) {
            if (mConnectThread != null) {
                mConnectThread?.cancel()
                mConnectThread = null
            }
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread?.cancel()
            mConnectedThread = null
        }

        // Start the thread to connect with the given device
        mConnectThread = ConnectThread(device)
        mConnectThread?.start()

        state = STATE_CONNECTING
    }

    /**
     * Start the ConnectedThread to begin managing a Bluetooth connection
     *
     * @param socket The BluetoothSocket on which the connection was made
     * @param device The BluetoothDevice that has been connected
     */
    @Synchronized
    fun connected(socket: BluetoothSocket, device: BluetoothDevice) {
        // Cancel the thread that completed the connection
        if (mConnectThread != null) {
            mConnectThread?.cancel()
            mConnectThread = null
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {
            mConnectedThread?.cancel()
            mConnectedThread = null
        }

        // Start the thread to manage the connection and perform transmissions
        mConnectedThread = ConnectedThread(socket)
        mConnectedThread?.start()

        state = STATE_CONNECTED
    }

    /**
     * Stop all threads
     */
    @Synchronized
    fun stop() {
        if (mConnectThread != null) {
            mConnectThread?.cancel()
            mConnectThread = null
        }

        if (mConnectedThread != null) {
            mConnectedThread?.cancel()
            mConnectedThread = null
        }

        state = STATE_NONE
    }

    /**
     * Write to the ConnectedThread in an synchronized manner
     *
     * @param out The bytes to write
     * @see ConnectedThread.write
     */
    fun write(out: ByteArray) {
        // Create temporary object
        val r: ConnectedThread?

        // Synchronize a copy of the ConnectedThread
        synchronized(this) {
            if (mState != STATE_CONNECTED) {
                Log.i("RobotArmService", "Not in a connected state")
                return
            }
            r = mConnectedThread
        }

        // Perform the write synchronized
        r?.write(out)
    }

    /**
     * Indicate that the connection attempt failed and notify the UI Activity.
     */
    private fun connectionFailed() {
    }

    /**
     * Indicate that the connection was lost and notify the UI Activity.
     */
    private fun connectionLost() {
    }

    /**
     * This thread runs while attempting to make an outgoing connection
     * with a device. It runs straight through; the connection either
     * succeeds or fails.
     */
    @SuppressLint("MissingPermission") // TODO: Revisit and reassess
    private inner class ConnectThread(private val mmDevice: BluetoothDevice) : Thread() {
        private val mmSocket: BluetoothSocket?

        init {
            var tmp: BluetoothSocket? = null
            // Get a BluetoothSocket for a connection with the
            // given BluetoothDevice
            try {
                tmp = mmDevice.createRfcommSocketToServiceRecord(MY_UUID)
            } catch (e: IOException) {
                Log.i("RobotArmService", "Unable to connect to server")
            }

            mmSocket = tmp
        }

        override fun run() {
            name = "ConnectThread"
            // Always cancel discovery because it will slow down a connection
            mAdapter?.cancelDiscovery()
            // Make a connection to the BluetoothSocket
            try {
                // This is a blocking call and will only return on a
                // successful connection or an exception
                mmSocket!!.connect()
                Log.i("RobotArmService", "Connected to server")
            } catch (e: IOException) {
                connectionFailed()
                // Close the socket
                try {
                    mmSocket?.close()
                } catch (e2: IOException) {
                }

                return
            }

            // Reset the ConnectThread because we're done
            synchronized(this@RobotArmService) {
                mConnectThread = null
            }
            // Start the connected thread
            connected(mmSocket, mmDevice)
        }

        fun cancel() {
            try {
                mmSocket?.close()
            } catch (e: IOException) {
            }

        }
    }

    /**
     * This thread runs during a connection with a remote device.
     * It handles all incoming and outgoing transmissions.
     */
    private inner class ConnectedThread(private val mmSocket: BluetoothSocket) : Thread() {
        private val mmOutStream: OutputStream?

        init {
            var tmpOut: OutputStream? = null

            // Get the BluetoothSocket input and output streams
            try {
                tmpOut = mmSocket.outputStream
            } catch (e: IOException) {
            }

            mmOutStream = tmpOut
        }

        /**
         * Write to the connected OutStream.
         *
         * @param buffer The bytes to write
         */
        fun write(buffer: ByteArray) {
            try {
                mmOutStream!!.write(buffer)
                Log.i("RobotArmService", "Sent: $buffer to target")
            } catch (e: IOException) {
                Log.i("RobotArmService", "Sending message to target failed")
            }

        }

        fun cancel() {
            try {
                mmSocket.close()
            } catch (e: IOException) {
            }

        }
    }

}