package com.itw.todolistkotlinapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.itw.todolistkotlinapp.R
import com.itw.todolistkotlinapp.dbmanager.DBHelper
import com.itw.todolistkotlinapp.model.UserModel
import com.itw.todolistkotlinapp.utils.InputValidation
import kotlinx.android.synthetic.main.activity_reg.*

class RegisterActivity : AppCompatActivity() {

    lateinit var usersDBHelper: DBHelper

    private lateinit var textInputLayoutName: TextInputLayout
    private lateinit var textInputLayoutEmail: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout
    private lateinit var textInputEditTextName: TextInputEditText
    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText

    private lateinit var inputValidation: InputValidation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        usersDBHelper = DBHelper(this)
        inputValidation = InputValidation(this)
        initViews()
    }

    /**
     * This method is to initialize views
     */
    private fun initViews() {

        textInputLayoutName = findViewById<View>(R.id.textInputLayoutName) as TextInputLayout
        textInputLayoutEmail = findViewById<View>(R.id.textInputLayoutEmail) as TextInputLayout
        textInputLayoutPassword =
            findViewById<View>(R.id.textInputLayoutPassword) as TextInputLayout
        textInputEditTextName = findViewById<View>(R.id.textInputEditTextName) as TextInputEditText
        textInputEditTextEmail =
            findViewById<View>(R.id.textInputEditTextEmail) as TextInputEditText
        textInputEditTextPassword =
            findViewById<View>(R.id.textInputEditTextPassword) as TextInputEditText

    }

    fun addUser(v: View) {
        if (!inputValidation!!.isInputEditTextFilled(
                textInputEditTextName,
                textInputLayoutName,
                getString(R.string.error_message_name)
            )
        ) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(
                textInputEditTextEmail,
                textInputLayoutEmail,
                getString(R.string.error_message_email)
            )
        ) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(
                textInputEditTextEmail,
                textInputLayoutEmail,
                getString(R.string.error_message_email)
            )
        ) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(
                textInputEditTextPassword,
                textInputLayoutPassword,
                getString(R.string.error_message_password)
            )
        ) {
            return
        }
        if (!usersDBHelper!!.checkUser(textInputEditTextEmail!!.text.toString().trim())) {
            var result = usersDBHelper.insertUser(
                UserModel(
                    name = textInputEditTextName!!.text.toString().trim(),
                    email = textInputEditTextEmail!!.text.toString().trim(),
                    pass = textInputEditTextPassword!!.text.toString().trim()
                )
            )

            //clear all edittext s
            this.textInputEditTextEmail.setText("")
            this.textInputEditTextName.setText("")
            this.textInputEditTextPassword.setText("")
            this.textview_result.text = "Added user : " + result
            Toast.makeText(this, getString(R.string.success_message), Toast.LENGTH_LONG).show()

            this.ll_entries.removeAllViews()
        } else {
            Toast.makeText(this, getString(R.string.error_email_exists), Toast.LENGTH_LONG).show()
        }

    }

    fun deleteUser(v: View) {
        var userid = this.textInputEditTextPassword.text.toString()
        val result = usersDBHelper.deleteUser(userid)
        this.textview_result.text = "Deleted user : " + result
        this.ll_entries.removeAllViews()
    }

    fun showAllUsers(v: View) {
        var users = usersDBHelper.getAllUser()
        this.ll_entries.removeAllViews()
        users.forEach {
            var tv_user = TextView(this)
            tv_user.textSize = 30F
            tv_user.text =
                it.userid.toString() + " " + it.name.toString() + "  " + it.email.toString()
            this.ll_entries.addView(tv_user)
        }
        this.textview_result.text = "Fetched " + users.size + " users"
    }

    fun openLogin(v: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}
