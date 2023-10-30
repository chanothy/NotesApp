package com.example.notesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.notesapp.databinding.FragmentTasksBinding
import com.google.android.material.appbar.MaterialToolbar


/**
 * A simple [Fragment] subclass.
 * Use the [TasksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TasksFragment : Fragment()   {
    /**
     * Serves as home screen
     *
     * Has a recycler view that shows all the note items. Also allows for creation of new notes.
     */
    val TAG = "TasksFragment"
    private var _binding: FragmentTasksBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModel : TasksViewModel by activityViewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val addNoteButton = binding.addNoteButton
        addNoteButton.setOnClickListener {
            val action = TasksFragmentDirections.actionTasksFragmentToNoteFragment()
            this.findNavController().navigate(action)
        }

        viewModel.initializeTheDatabaseReference()

        val logoutButton = binding.logoutButton
        logoutButton.setOnClickListener {
            viewModel.signOut()
        }



        fun taskClicked (task : Task) {
            viewModel.onTaskClicked(task)
            Log.d("clicking box", "box clicked")
        }
        fun yesPressed(taskId : String) {
            Log.d(TAG, "in yesPressed(): taskId = $taskId")
        }
        fun deleteClicked (taskId : String) {
             ConfirmDeleteDialogFragment(taskId,::yesPressed).show(childFragmentManager, ConfirmDeleteDialogFragment.TAG)
        }
        val adapter = TaskItemAdapter(::taskClicked,::deleteClicked)

        binding.tasksList.adapter = adapter


        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToNewTask.observe(viewLifecycleOwner, Observer { taskId ->
            taskId?.let {
                val action = TasksFragmentDirections.actionTasksFragmentToEditTaskFragment(taskId)
                findNavController().navigate(action)
                viewModel.onTaskNavigated()
            }
        })

        viewModel.navigateToSignIn.observe(viewLifecycleOwner, Observer { navigate ->
            if(navigate) {
                this.findNavController().navigate(R.id.action_tasksFragment_to_signInFragment)
                viewModel.onNavigatedToSignIn()
            }
        })

        // Configure the toolbar, set navigation, etc.
        val toolbar: MaterialToolbar = view.findViewById(R.id.toolbar)
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val viewModel : TasksViewModel by activityViewModels()
        when (item.itemId) {
            R.id.add -> {
                // Handle Item 1 click
                val action = TasksFragmentDirections.actionTasksFragmentToNoteFragment()
                this.findNavController().navigate(action)
                Log.d("add","button clicked")
                return true
            }
            R.id.login -> {
                // Handle Item 2 click
                return true
            }
            // Add more cases for additional menu items
            else -> return super.onOptionsItemSelected(item)
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}