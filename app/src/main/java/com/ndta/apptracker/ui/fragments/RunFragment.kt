package com.ndta.apptracker.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ndta.apptracker.R
import com.ndta.apptracker.adapter.RunAdapter
import com.ndta.apptracker.databinding.FragmentRunBinding
import com.ndta.apptracker.ui.viewmodel.MainViewModel
import com.ndta.apptracker.utils.Constants
import com.ndta.apptracker.utils.SortType
import com.ndta.apptracker.utils.TrackingUtility
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class RunFragment : Fragment(), EasyPermissions.PermissionCallbacks {
    private lateinit var binding: FragmentRunBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var runAdapter: RunAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_run, container, false)
        setupView()
        setupRecyclerView()
        when (mainViewModel.sortType) {
            SortType.DATE -> binding.spFilter.setSelection(0)
            SortType.RUNNING_TIME -> binding.spFilter.setSelection(1)
            SortType.DISTANCE -> binding.spFilter.setSelection(2)
            SortType.AVG_SPEED -> binding.spFilter.setSelection(3)
            SortType.CALORIES_BURNED -> binding.spFilter.setSelection(4)
        }

        binding.spFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> mainViewModel.sortRuns(SortType.DATE)
                    1 -> mainViewModel.sortRuns(SortType.RUNNING_TIME)
                    2 -> mainViewModel.sortRuns(SortType.DISTANCE)
                    3 -> mainViewModel.sortRuns(SortType.AVG_SPEED)
                    4 -> mainViewModel.sortRuns(SortType.CALORIES_BURNED)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        mainViewModel.runs.observe(viewLifecycleOwner, Observer {
            runAdapter.submitList(it)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
    }

    private fun setupView() {
        binding.apply {
            fab.setOnClickListener { findNavController().navigate(R.id.action_runFragment_to_trackingFragment) }
        }
    }

    private fun setupRecyclerView() = binding.rvRuns.apply {
        runAdapter = RunAdapter()
        adapter = runAdapter
    }

    private fun requestPermissions() {
        if (TrackingUtility.hasLocationPermissions(requireContext())) {
            return
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                Constants.REQUEST_CODE_LOCATION_PERMISSION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                Constants.REQUEST_CODE_LOCATION_PERMISSION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


}