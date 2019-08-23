package com.itw.todolistkotlinapp.app

import android.support.multidex.MultiDexApplication
import com.itw.todolistkotlinapp.utils.ToDoPref

class ToDoApplication : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        ToDoPref.getInstance(this)
    }
}