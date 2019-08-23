package com.itw.todolistkotlinapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.itw.todolistkotlinapp.app.ToDoApplication

object ToDoPref {

    private lateinit var sharedPreferences: SharedPreferences

    fun getInstance(context: Context) {
        sharedPreferences = context.getSharedPreferences("ToDoAppPrefs", 0)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var getUserName: String
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = sharedPreferences.getString("userName", "")
        // custom setter to save a preference back to preferences file
        set(value) = sharedPreferences.edit {
            it.putString("userName", value)
        }

    var getUserId: Int
        // custom getter to get a preference of a desired type, with a predefined default value
        get() = sharedPreferences.getInt("userId", 0)
        // custom setter to save a preference back to preferences file
        set(value) = sharedPreferences.edit {
            it.putInt("userId", value)
        }

}