package com.example.ahmedazz.gift

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private var mRef: DatabaseReference? = null
    private var mAuth: FirebaseAuth? = null

    private var radioButton:RadioButton? = null
    private var userUid: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance()
        mRef = database.getReference("Users")
        mAuth = FirebaseAuth.getInstance()


        btnLogin.setOnClickListener {

            val radioId = radioGroup.checkedRadioButtonId
            radioButton  = findViewById(radioId)

            val userName = userNameEdt.text.toString()
            val email = emailEdt.text.toString()
            val password = passwordEdt.text.toString()
            val radioSelected = radioButton?.text.toString()

            if (email.isNotEmpty()
                    && userName.isNotEmpty()
                    && password.isNotEmpty()
                    && radioSelected.isNotEmpty()) {
                mAuth?.createUserWithEmailAndPassword(email, password)

                userUid = mAuth?.currentUser?.uid
                val newUser = Users(userUid!!, userName, radioSelected)
                mRef!!.child(userUid!!).setValue(newUser)
                secondActivity()

            }

        }
    }



    private fun secondActivity() {
        var noteIntent = Intent(this, SecondActivity::class.java)


        noteIntent.putExtra("uid_key",userUid)
        startActivity(noteIntent)
    }

    fun choices(v: View){
    }


}
