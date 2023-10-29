package com.example.notesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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
//        val application = requireNotNull(this.activity).application
//        val dao = TaskDatabase.getInstance(application).taskDao
//        val viewModelFactory = TasksViewModelFactory(dao)
        val viewModel : TasksViewModel by activityViewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
//        viewModel.initializeTheDatabaseReference()

       /* val adapter = TaskItemAdapter{ taskId ->
            viewModel.onTaskClicked(taskId)

        }*/




        val addNoteButton = binding.addNoteButton
        addNoteButton.setOnClickListener {
            val action = TasksFragmentDirections.actionTasksFragmentToNoteFragment()
            this.findNavController().navigate(action)
        }


        fun taskClicked (task : Task) {
            viewModel.onTaskClicked(task)
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

        viewModel.navigateToTask.observe(viewLifecycleOwner, Observer { taskId ->
            taskId?.let {
                val action = TasksFragmentDirections
                    .actionTasksFragmentToEditTaskFragment(taskId)
                this.findNavController().navigate(action)
                viewModel.onTaskNavigated()
            }
        })
        //todo
//        viewModel.navigateToSignIn.observe(viewLifecycleOwner, Observer { navigate ->
//            if(navigate) {
//                this.findNavController().navigate(R.id.action_tasksFragment_to_signInFragment)
//                viewModel.onNavigatedToSignIn()
//            }
//        })

        // Configure the toolbar, set navigation, etc.
        val toolbar: MaterialToolbar = view.findViewById(R.id.toolbar)
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navHostFragment =
            activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val builder = AppBarConfiguration.Builder(navController.graph)
        val appBarConfiguration = builder.build()
        toolbar.setupWithNavController(navController, appBarConfiguration)

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}