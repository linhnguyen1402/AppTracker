package com.ndta.apptracker.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ndta.apptracker.R
import com.ndta.apptracker.databinding.FragmentSetupBinding
import com.ndta.apptracker.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupFragment : Fragment() {
    private lateinit var binding: FragmentSetupBinding
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setup, container, false)
        return binding.root
    }


}