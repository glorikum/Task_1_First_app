package com.oshovenko.firstapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.oshovenko.firstapp.calculator.CalculatorActivity
import com.oshovenko.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClickButton(view: View) {
        Toast.makeText(applicationContext, "Hello", LENGTH_SHORT).show()
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
    }
}