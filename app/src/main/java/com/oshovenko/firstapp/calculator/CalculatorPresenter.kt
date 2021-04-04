package com.oshovenko.firstapp.calculator

import android.view.View
import android.widget.Button
import com.oshovenko.firstapp.R
import com.oshovenko.firstapp.databinding.ActivityCalculatorBinding

class CalculatorPresenter(binding: ActivityCalculatorBinding) {
    private lateinit var binding: ActivityCalculatorBinding

    init {
        this.binding = binding
    }

    fun onClick(view: View){

        when (view) {
            binding.buttonAc-> {
                binding.equation.text = ""
            }
            else -> {
                var equation: String = binding.equation.text.toString()
                var button: Button = view as Button
                binding.equation.text = equation.plus(button.text)
            }
        }
    }

}