package com.example.ahmedazz.gift

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private var mRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val uid = intent.extras.getString("uid_key")


        val database = FirebaseDatabase.getInstance()
        mRef = database.getReference("Users").child(uid).child("radioBtnSelected")

        mRef?.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(value: DataSnapshot) {
                 var userChoices = value.getValue(String::class.java)
                if (userChoices.equals("Famous")){
                    btnCheck.visibility = View.GONE
                }
            }

        })


    }
}
