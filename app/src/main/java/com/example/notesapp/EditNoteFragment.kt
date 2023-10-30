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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.notesapp.databinding.FragmentEditTaskBinding
import com.google.android.material.appbar.MaterialToolbar


/**
 * A simple [Fragment] subclass.
 * Use the [EditNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditNoteFragment : Fragment() {
    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        val view = binding.root
        val taskId = EditNoteFragmentArgs.fromBundle(requireArguments()).taskId

        val viewModel : TasksViewModel by activityViewModels()
        viewModel.taskId = taskId
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                view.findNavController()
                    .navigate(R.id.action_editTaskFragment_to_tasksFragment)
                viewModel.onNavigatedToList()
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
        inflater.inflate(R.menu.note_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val viewModel : TasksViewModel by activityViewModels()
        val taskId = EditNoteFragmentArgs.fromBundle(requireArguments()).taskId

        when (item.itemId) {
            R.id.toolbar_delete -> {
                // Handle Item 1 click
                Log.d("ID", taskId)
                if (taskId != "") {
                    binding.viewModel?.deleteTask(taskId)
                }
                val action = EditNoteFragmentDirections.actionEditTaskFragmentToTasksFragment()
                findNavController().navigate(action)
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