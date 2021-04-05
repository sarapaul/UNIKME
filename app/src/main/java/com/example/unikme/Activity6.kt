package com.example.unikme

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Activity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_6)
        val button9 =findViewById<Button>(R.id.button9)
        button9.setOnClickListener {
            val intent = Intent(this, hearActivity::class.java)
            startActivity(intent)
        }
        val button10 = findViewById<Button>(R.id.button10)
        button10.setOnClickListener {
            val intent = Intent(this, speechActivity::class.java)
            startActivity(intent)
        }
        val button11 = findViewById<Button>(R.id.button11)
        button11.setOnClickListener {
            val intent = Intent(this, autismActivity::class.java)
            startActivity(intent)
        }
        val button12 = findViewById<Button>(R.id.button12)
        button12.setOnClickListener {
            val intent = Intent(this, cpActivity::class.java)
            startActivity(intent)
        }
    }
}