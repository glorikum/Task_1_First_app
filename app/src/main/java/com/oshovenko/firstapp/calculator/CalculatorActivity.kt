package com.oshovenko.firstapp.calculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.oshovenko.firstapp.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding
    private lateinit var calculatorPresenter: CalculatorPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        calculatorPresenter = CalculatorPresenter(binding)
    }

    fun onClickCalculatorButton(view: View){
        calculatorPresenter.onClick(view)
    }

}