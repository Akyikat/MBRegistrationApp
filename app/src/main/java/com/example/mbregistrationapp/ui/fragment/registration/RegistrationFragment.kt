package com.example.mbregistrationapp.ui.fragment.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.mbregistrationapp.R
import com.example.mbregistrationapp.databinding.FragmentRegistrationBinding
import com.example.mbregistrationapp.ui.fragment.registrtationform.RegistrationFormFragment
import com.example.mbregistrationapp.utils.ResponseResultStatus
import com.example.mbregistrationapp.utils.replaceFragmentExt
import com.example.mbregistrationapp.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding get() = _binding!!

    private val viewModel: RegistrationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCountries()
        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews() {
        binding.btnNext.setOnClickListener {
            if (binding.etPhone.text.toString().trim().isNotEmpty()) {
                viewModel.checkNumber(binding.etPhone.text.toString())
            }
        }
    }

    private fun subscribeToLiveData() {
        subscribeToCountriesList()
        viewModel.checkPhone.observe(viewLifecycleOwner, { state ->
            when (state.status) {
                ResponseResultStatus.SUCCESS -> {
                    when (state.result) {
                        PhoneState.EXIST -> {
                            requireContext().showToast("Такой номер уже есть")
                        }
                        PhoneState.NOT_EXIST -> {
                            binding.btnNext.setOnClickListener {
                                replaceFragmentExt(
                                    R.id.main_container,
                                    RegistrationFormFragment.newInstance(binding.etPhone.text.toString())
                                )
                            }
                        }
                    }
                }
                ResponseResultStatus.LOADING -> {}
                ResponseResultStatus.ERROR -> {}
            }
        })
    }

    private fun subscribeToCountriesList() {
        viewModel.availableCountries.observe(viewLifecycleOwner, { result ->
            when (result.status) {
                ResponseResultStatus.SUCCESS -> {
                    result?.let { countries ->
                        addCountriesIntoSpinner(countries.result ?: listOf())
                    }
                    binding.progress.isVisible = false
                    binding.btnNext.isClickable = true
                    binding.btnNext.isFocusable = true
                }
                ResponseResultStatus.LOADING -> {
                    binding.progress.isVisible = true
                    binding.btnNext.isClickable = false
                    binding.btnNext.isFocusable = false
                }
                ResponseResultStatus.ERROR -> {
                    binding.progress.isVisible = false
                    binding.btnNext.isClickable = true
                    binding.btnNext.isFocusable = true

                    requireContext().showToast(getString(R.string.default_network_error))
                }
            }

        })
    }

    private fun addCountriesIntoSpinner(countries: List<String>) {
        val countriesAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, countries)
        binding.spinner.adapter = countriesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = RegistrationFragment()

        const val PHONE_KEY: String = "phoneKey"
    }
}