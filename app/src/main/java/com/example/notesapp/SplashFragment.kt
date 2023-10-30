package com.example.notesapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.TasksViewModel
import com.example.notesapp.databinding.FragmentTasksBinding


/**
 * Splash fragment that checks if a user is already logged in
 * before continuing to a sign up or sign in screen.
 */
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        val viewModel : TasksViewModel by activityViewModels()

        super.onStart()
        val currentUser = viewModel.getCurrentUser()

        val handler = Handler(Looper.myLooper()!!)
        handler.postDelayed({
            if (currentUser != null) {
                val action = SplashFragmentDirections.actionSplashFragmentToTasksFragment()
                this.findNavController().navigate(action)
            }
            else {
                this.findNavController().navigate(R.id.action_splashFragment_to_signInFragment)

            }

        }, 2000)
    }


}