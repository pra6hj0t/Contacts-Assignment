package com.example.contactsassignment

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactsassignment.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
  //  lateinit var binding: ActivityMainBinding
    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
     //   binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)


        val btn_signup = findViewById<Button>(R.id.btn_signup)
        val userFullName = findViewById<TextInputEditText>(R.id.et_fullName)
        val userId = findViewById<TextInputEditText>(R.id.et_userid)
        val userPassword = findViewById<TextInputEditText>(R.id.et_password)
        val btn_login = findViewById<TextView>(R.id.btn_login)

      btn_signup.setOnClickListener {
          val fullName = userFullName.text.toString()
          val userid = userId.text.toString()
          val password = userPassword.text.toString()

          if(fullName.trim().equals("")){
              userFullName.setError("Required")
              return@setOnClickListener
          }
          if (userid.trim().equals("")){
              userId.setError("Required")
              return@setOnClickListener

          }
          if (password.trim().equals("")){
              userPassword.setError("Required")
              return@setOnClickListener
          }

          val users = User(fullName,userid,password)

          database = FirebaseDatabase.getInstance().getReference("Users")
          database.child(userid).setValue(users).addOnSuccessListener {

            userFullName.text?.clear()
              userId.text?.clear()
              userPassword.text?.clear()

              Toast.makeText(this,"Registered",Toast.LENGTH_SHORT).show()
              Toast.makeText(this,"Now Please Log In",Toast.LENGTH_SHORT).show()


          }

        }

        btn_login.setOnClickListener {
            val intent = Intent(this,LoginPage::class.java)
            startActivity(intent)
            finish()
        }

    }
}