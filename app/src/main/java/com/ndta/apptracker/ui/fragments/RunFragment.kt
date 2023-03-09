package com.ndta.apptracker.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ndta.apptracker.R
import com.ndta.apptracker.databinding.FragmentRunBinding
import com.ndta.apptracker.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RunFragment : Fragment() {
    private lateinit var binding: FragmentRunBinding
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_run, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            fab.setOnClickListener { findNavController().navigate(R.id.action_runFragment_to_trackingFragment) }
        }
    }

}