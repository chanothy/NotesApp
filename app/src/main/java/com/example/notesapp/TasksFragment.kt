package com.example.notesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesapp.databinding.FragmentTasksBinding


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
        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val viewModelFactory = TasksViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(TasksViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

       /* val adapter = TaskItemAdapter{ taskId ->
            viewModel.onTaskClicked(taskId)

        }*/




        val addNoteButton = binding.addNoteButton
        addNoteButton.setOnClickListener {
            val action = TasksFragmentDirections.actionTasksFragmentToNoteFragment()
            this.findNavController().navigate(action)
        }


        fun taskClicked (taskId : Long) {
            viewModel.onTaskClicked(taskId)
        }
        fun yesPressed(taskId : Long) {
            Log.d(TAG, "in yesPressed(): taskId = $taskId")
        }
        fun deleteClicked (taskId : Long) {
             ConfirmDeleteDialogFragment(taskId,::yesPressed).show(childFragmentManager, ConfirmDeleteDialogFragment.TAG)
        }
        val adapter = TaskItemAdapter(::taskClicked,::deleteClicked)


        binding.tasksList.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToTask.observe(viewLifecycleOwner, Observer { taskId ->
            taskId?.let {
                val action = TasksFragmentDirections
                    .actionTasksFragmentToEditTaskFragment(taskId)
                this.findNavController().navigate(action)
                viewModel.onTaskNavigated()
            }
        })

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}