package com.example.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Schema for the notes database
 */

import com.google.firebase.database.Exclude

/**
 * Schema for the note item
 */
data class Task(
    @get:Exclude
    var taskId: String = "",
    var taskName: String = "",
    var description: String = ""
)


