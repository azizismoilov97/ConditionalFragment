package com.example.conditionalfragment.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.conditionalfragment.R
import com.example.conditionalfragment.UserViewModel
import com.example.conditionalfragment.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var authenticated = false

    private val userViewModel : UserViewModel by activityViewModels()

    private val navController by lazy(LazyThreadSafetyMode.NONE) {findNavController()}

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.authenticated.observe(viewLifecycleOwner) { it ->
            authenticated = it
        }

        if (authenticated){
            showWelcomeMessage()
        }else{
            navController.navigate(R.id.navigation_notifications)
        }
    }

    private fun showWelcomeMessage() {
        Toast.makeText(requireContext(), "${fragmentManager?.backStackEntryCount}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}