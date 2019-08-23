package com.itw.todolistkotlinapp.adapter

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.itw.todolistkotlinapp.R
import com.itw.todolistkotlinapp.model.UserModel
import kotlinx.android.synthetic.main.user_list_item.view.*

class UserListAdapter(val items: ArrayList<UserModel>, val context: Context) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.user_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewName.text = items[position].name
        holder.textViewEmail.text = items[position].email
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewName: TextView
        var textViewEmail: TextView

        init {
            textViewName = view.findViewById<View>(R.id.txtName) as TextView
            textViewEmail = view.findViewById<View>(R.id.txtEmail) as TextView
        }
    }
}