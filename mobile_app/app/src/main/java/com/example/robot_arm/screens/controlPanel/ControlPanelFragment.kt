package com.example.robot_arm.screens.controlPanel

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

class ControlPanelFragment : Fragment() {
    private lateinit var binding: ControlpanelFragmentBinding
    private lateinit var viewModel: ControlPanelViewModel

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
        val viewModelFactory = ControlPanelViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[ControlPanelViewModel::class.java]

        // Set up the viewModel binding so that it can handle events defined in the layout
        binding.controlPanelViewModel = viewModel

        return binding.root
    }
}
