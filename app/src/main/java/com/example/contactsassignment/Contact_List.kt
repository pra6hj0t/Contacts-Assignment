package com.example.contactsassignment

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Contact_List : AppCompatActivity() {
    lateinit var database : DatabaseReference
    lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_list)


        val btn_add = findViewById<Button>(R.id.btn_add)
        val contact_name = findViewById<TextInputEditText>(R.id.et_contact_name)
        val contact_number = findViewById<TextInputEditText>(R.id.et_contact_number)


        btn_add.setOnClickListener {

            val Contact_Name = contact_name.text.toString()
            val Contact_Number = contact_number.text.toString()

            val contact_data = Contact_Data(Contact_Name,Contact_Number)
            database = FirebaseDatabase.getInstance().getReference("Contacts")
            database.child(Contact_Number).setValue(contact_data).addOnSuccessListener {

                contact_name.text?.clear()
                contact_number.text?.clear()
//                Toast.makeText(this,"Contact Added",Toast.LENGTH_SHORT).show()

                dialog = Dialog(this)
                dialog.setContentView(R.layout.alert_dialog_box_layout)
                dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.bg_alert_box))
                val btn_ok = dialog.findViewById<Button>(R.id.btn_ok)

                btn_ok.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()


            }

        }

    }
}