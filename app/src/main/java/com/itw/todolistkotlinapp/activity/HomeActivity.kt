package com.itw.todolistkotlinapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.itw.todolistkotlinapp.R
import com.itw.todolistkotlinapp.adapter.UserListAdapter
import com.itw.todolistkotlinapp.dbmanager.DBHelper
import com.itw.todolistkotlinapp.model.UserModel
import com.itw.todolistkotlinapp.utils.ToDoPref
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    // Initializing an empty ArrayList to be filled with animals
    var users: ArrayList<UserModel> = ArrayList()
    private lateinit var databaseHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        fetchUserListFromDatabase()
        initUserList();

    }

    private fun fetchUserListFromDatabase() {
        databaseHelper = DBHelper(this)
        if (databaseHelper.getAllUser().size > 0) {
            users = databaseHelper.getAllUser();
        }
    }

    fun initUserList() {
        rvListUser.layoutManager = LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        rvListUser.adapter = UserListAdapter(users, this)
    }

}