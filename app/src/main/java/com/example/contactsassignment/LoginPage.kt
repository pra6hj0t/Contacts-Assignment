package com.example.contactsassignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginPage : AppCompatActivity() {
    lateinit var dataBase : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_page)

        val userid = findViewById<TextInputEditText>(R.id.et_userid_login)
        val userpassword = findViewById<TextInputEditText>(R.id.et_password_login)
        val btn_Login = findViewById<Button>(R.id.btn_login_login)
        val btn_join = findViewById<TextView>(R.id.btn_join)
        val btn_forgot = findViewById<TextView>(R.id.btn_forgot)

        btn_join.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_Login.setOnClickListener {
            val UserId = userid.text.toString()
            val UserPassword = userpassword.text.toString()

            dataBase = FirebaseDatabase.getInstance().getReference("Users")

            if (UserId.trim().equals("")){
                userid.setError("Required")
            return@setOnClickListener
            }
            if (UserPassword.trim().equals("")){
                userpassword.setError("Required")
                return@setOnClickListener
            }else{
                dataBase.child(UserId).get().addOnSuccessListener {
                    if (it.exists()){
                        val dbPassword = it.child("password").value
                        if (UserPassword.equals(dbPassword)){
                            Toast.makeText(this,"Logged In",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,Contact_List::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"Invalid",Toast.LENGTH_SHORT).show()
                        }

                    }else{
                        Toast.makeText(this,"Invalid",Toast.LENGTH_SHORT).show()
                    }


                }
            }

        }

        btn_forgot.setOnClickListener {
            Toast.makeText(this,"Coming Soon \uD83D\uDE07",Toast.LENGTH_SHORT).show()
        }


    }
}