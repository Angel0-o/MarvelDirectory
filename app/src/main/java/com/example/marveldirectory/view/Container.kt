package com.example.marveldirectory.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marveldirectory.databinding.ActivityContainerBinding

class Container : AppCompatActivity() {
    private lateinit var binding: ActivityContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}