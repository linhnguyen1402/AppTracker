package com.ndta.apptracker.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ndta.apptracker.R
import com.ndta.apptracker.databinding.FragmentSetupBinding
import com.ndta.apptracker.ui.MainActivity
import com.ndta.apptracker.ui.viewmodel.MainViewModel
import com.ndta.apptracker.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SetupFragment : Fragment() {
    private lateinit var binding: FragmentSetupBinding
    private val mainViewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var sharedPref: SharedPreferences

    @set:Inject
    var isFirstAppOpen = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setup, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isFirstAppOpen) {
            val navOption = NavOptions.Builder().setPopUpTo(R.id.setupFragment, true).build()
            findNavController().navigate(
                R.id.action_setupFragment_to_runFragment,
                savedInstanceState,
                navOption
            )
        }

        binding.apply {
            tvContinue.setOnClickListener {
                val success = writePersonalDataToSharedPref()
                if (success) {
                    findNavController().navigate(R.id.action_setupFragment_to_runFragment)
                } else {
                    Snackbar.make(
                        requireView(),
                        "Please enter all the fields",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun writePersonalDataToSharedPref(): Boolean {
        val name = binding.etName.text.toString()
        val weight = binding.etWeight.text.toString()
        if (name.isEmpty() || weight.isEmpty()) {
            return false
        }
        sharedPref.edit()
            .putString(Constants.KEY_NAME, name)
            .putFloat(Constants.KEY_WEIGHT, weight.toFloat())
            .putBoolean(Constants.KEY_FIRST_TIME_TOGGLE, false)
            .apply()

        val toolbarText = "Let's go, $name!"
        (requireActivity() as MainActivity).updateTitle(toolbarText)
        return true
    }


}