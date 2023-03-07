package com.example.consortium.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.consortium.R
import com.example.consortium.databinding.ActivityHomeBinding
import com.example.consortium.presentation.Delivery.DeliveriesActivity
import com.example.consortium.presentation.NewDelivery.NewDeliveryActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOpen.setOnClickListener {
            startActivity(DeliveriesActivity.newIntent(this))
        }
    }
}