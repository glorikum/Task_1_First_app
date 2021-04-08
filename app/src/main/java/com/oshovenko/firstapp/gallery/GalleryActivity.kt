package com.oshovenko.firstapp.gallery

import android.icu.number.NumberFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.oshovenko.firstapp.R
import com.oshovenko.firstapp.databinding.ActivityGalleryBinding
import com.squareup.picasso.Picasso

class GalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClick(view: View){
        Picasso.get()
            .load(R.drawable.pic)
            .fit()
            .into(binding.imageView);
    }
}