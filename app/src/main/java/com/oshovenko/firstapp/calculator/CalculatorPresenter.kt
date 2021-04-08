package com.oshovenko.firstapp.calculator

import android.view.View
import android.widget.Button
import com.oshovenko.firstapp.calculator.count.CountEquation
import com.oshovenko.firstapp.databinding.ActivityCalculatorBinding

class CalculatorPresenter(private var binding: ActivityCalculatorBinding) {

    fun onClick(view: View){

        when (view) {
            binding.buttonAc-> {
                binding.equation.text = ""
                binding.answer.text = "=0"
            }

            binding.buttonBack -> {
                val equation: String = binding.equation.text.toString()
                if (equation.isNotEmpty()){
                    binding.equation.text = binding.equation.text.toString().subSequence(0, binding.equation.text.length-1)
                }
            }

            binding.buttonEqual -> {
                val equation: String = binding.equation.text.toString()
                if (equation.isNotEmpty()){
                    try {
                        val countEquation = CountEquation(binding.equation.text.toString())
                        binding.answer.text = countEquation.count()
                    } catch (e: Exception){
                        binding.answer.text = e.toString().substring(20, e.toString().length)
                        println(e)
                    }
                }
            }
            else -> {
                val equation: String = binding.equation.text.toString()
                val button: Button = view as Button
                binding.equation.text = equation.plus(button.text)
            }
        }
    }
}