package com.itw.todolistkotlinapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.Toast
import com.itw.todolistkotlinapp.R
import com.itw.todolistkotlinapp.dbmanager.DBHelper
import com.itw.todolistkotlinapp.utils.InputValidation
import com.itw.todolistkotlinapp.utils.ToDoPref

class LoginActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DBHelper
    private lateinit var textInputLayoutEmail: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout
    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText
    private lateinit var inputValidation: InputValidation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initObjects()
    }

    fun checkUser(v: View) {
        if (!inputValidation!!.isInputEditTextFilled(
                textInputEditTextEmail!!,
                textInputLayoutEmail!!,
                getString(R.string.error_message_email)
            )
        ) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(
                textInputEditTextEmail!!,
                textInputLayoutEmail!!,
                getString(R.string.error_message_email)
            )
        ) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(
                textInputEditTextPassword!!,
                textInputLayoutPassword!!,
                getString(R.string.error_message_password)
            )
        ) {
            return
        }

        if (databaseHelper!!.checkUser(textInputEditTextEmail!!.text.toString().trim())) {
            Toast.makeText(this, getString(R.string.success_message), Toast.LENGTH_LONG).show()
            ToDoPref.getUserId =
                databaseHelper.getUserIdByEmail(textInputEditTextEmail!!.text.toString())
            val accountsIntent = Intent(this, HomeActivity::class.java)
            startActivity(accountsIntent)
            finish()
        } else {
            Toast.makeText(this, "not exist", Toast.LENGTH_LONG).show()
        }
    }

    fun openRegister(v: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun initObjects() {
        inputValidation = InputValidation(this)
        databaseHelper = DBHelper(this)
        textInputLayoutEmail = findViewById<View>(R.id.textInputLayoutEmail) as TextInputLayout
        textInputLayoutPassword =
            findViewById<View>(R.id.textInputLayoutPassword) as TextInputLayout
        textInputEditTextEmail =
            findViewById<View>(R.id.textInputEditTextEmail) as TextInputEditText
        textInputEditTextPassword =
            findViewById<View>(R.id.textInputEditTextPassword) as TextInputEditText

    }

}
