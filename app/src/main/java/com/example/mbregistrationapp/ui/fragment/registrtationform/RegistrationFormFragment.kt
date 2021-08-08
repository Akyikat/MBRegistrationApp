package com.example.mbregistrationapp.ui.fragment.registrtationform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mbregistrationapp.databinding.FragmentRegistrationFormBinding
import com.example.mbregistrationapp.ui.fragment.registration.RegistrationFragment.Companion.PHONE_KEY
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFormFragment : Fragment() {

    private var _binding: FragmentRegistrationFormBinding? = null
    private val binding: FragmentRegistrationFormBinding get() = _binding!!

    private val viewModel: RegistrationFormViewModel by viewModel()

    private var phone: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            phone = arguments?.getString(PHONE_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationFormBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCheckboxAgreement()
        setupPhone()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupPhone() {
        binding.etPhone.setText(phone)
        binding.etPhone.isEnabled = false
    }

    private fun setupCheckboxAgreement() {
        binding.chAgreement.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.btnRegister.isEnabled = true
                binding.btnRegister.isClickable = true
                binding.btnRegister.isFocusable = true
            } else {
                binding.btnRegister.isEnabled = false
                binding.btnRegister.isClickable = false
                binding.btnRegister.isFocusable = false
            }
        }
    }

    companion object {
        fun newInstance(phone: String) = RegistrationFormFragment().apply {
            val bundle = Bundle()
            bundle.putString(PHONE_KEY, phone)
            arguments = bundle
        }
    }

    // OOP ->

    // solid
    // s - single responsibility ->
    // o - open closed principe ->
    // l - liskov substitution -> Polymorphism
    // i - interface segregation -> Interface
    // d - dependency inversion -> di

}