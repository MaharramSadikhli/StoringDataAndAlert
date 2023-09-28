package com.imsoft.storingdata

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.imsoft.storingdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    private var agePref: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = this.getSharedPreferences("com.imsoft.storingdata", Context.MODE_PRIVATE)

        agePref = sharedPref.getInt("age", -1)

        if (agePref != -1) {
            binding.textView.text = "$agePref"
        } else {
            binding.textView.text = "Your Age: "
        }
    }


    fun save(view: View) {
        val age = binding.editText.text.toString().toIntOrNull()

        val alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("SAVE")
        alert.setMessage("Are you sure?")

        alert.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                if (age != null) {
                    binding.textView.text = "$age"
                    sharedPref.edit().putInt("age", age).apply()
                    Toast.makeText(this@MainActivity,"Saved!", Toast.LENGTH_LONG).show()
                }
            }
        })

        alert.setNegativeButton("No", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                Toast.makeText(this@MainActivity,"Not Saved!", Toast.LENGTH_LONG).show()
            }
        })

        alert.show()


    }

    fun delete(view: View) {
        agePref = sharedPref.getInt("age", -1)


        val alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("DELETE")
        alert.setMessage("Are you sure?")

        alert.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                if (agePref != -1) {
                    sharedPref.edit().remove("age").apply()
                    binding.textView.text = "Your Age: "
                    Toast.makeText(this@MainActivity, "Deleted!", Toast.LENGTH_LONG).show()
                }
            }
        })

        alert.setNegativeButton("No", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                Toast.makeText(this@MainActivity, "Not Deleted!", Toast.LENGTH_LONG).show()
            }
        })

        alert.show()

    }
}