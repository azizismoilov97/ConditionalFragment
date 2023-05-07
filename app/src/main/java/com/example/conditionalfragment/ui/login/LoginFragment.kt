package com.example.conditionalfragment.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.conditionalfragment.UserViewModel
import com.example.conditionalfragment.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }

    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var savedStateHandle: SavedStateHandle

    private val navController by lazy(LazyThreadSafetyMode.NONE) {findNavController()}

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginViewModel =
            ViewModelProvider(this)[LoginViewModel::class.java]

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireContext(), "${fragmentManager?.backStackEntryCount}", Toast.LENGTH_SHORT).show()

        userViewModel.authenticated.observe(viewLifecycleOwner){
            if (it){
                savedStateHandle[LOGIN_SUCCESSFUL] = true
                findNavController().popBackStack()
            }else{
                showErrorMessage()
            }
        }

        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle[LOGIN_SUCCESSFUL] = false

        with(binding){
            loginButton.setOnClickListener {
                login(username.text.toString(), password.text.toString())
            }
        }
    }

    private fun login(username: String, password: String) {
        userViewModel.login(username, password)
    }

    private fun showErrorMessage() {
        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}