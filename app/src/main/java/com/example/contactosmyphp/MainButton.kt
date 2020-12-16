package com.example.contactosmyphp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.mainburtton.*
class MainButton: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainburtton)

        buttonContactos.setOnClickListener(){
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        buttonInazuma.setOnClickListener(){
            val intent2=Intent(this,MainInazuma::class.java)
            startActivity(intent2)
        }
    }


}