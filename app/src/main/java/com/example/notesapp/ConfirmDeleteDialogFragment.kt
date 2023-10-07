package com.example.notesapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConfirmDeleteDialogFragment(val taskId : Long,val clickListener: (taskId: Long) -> Unit) : DialogFragment() {
    val TAG = "ConfirmDeleteDialogFragment"
    interface myClickListener {
        fun yesPressed()
    }



    var listener: myClickListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.delete_confirmation))
            .setPositiveButton(getString(R.string.yes)) { _,_ -> clickListener(taskId)
                Log.d("taskId","yeah")
//                val application = requireNotNull(this.activity).application
//                val dao = TaskDatabase.getInstance(application).taskDao
//
//                val viewModelFactory = EditTaskViewModelFactory(taskId, dao)
//                val viewModel = ViewModelProvider(this, viewModelFactory)
//                    .get(EditTaskViewModel::class.java)
//
//                Log.d("taskId", taskId.toString())
//                val tasks = dao.getAll().value
//                Log.d("AllTasks", tasks.toString())
//                viewModel.deleteTask()

            }
            .setNegativeButton(getString(R.string.no)) { _,_ -> }

            .create()

    companion object {
        const val TAG = "ConfirmDeleteDialogFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as myClickListener
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
        }
    }

}