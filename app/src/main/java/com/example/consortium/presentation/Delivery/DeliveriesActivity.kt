package com.example.consortium.presentation.Delivery

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.consortium.R
import com.example.consortium.databinding.ActivityDeliveriesBinding
import com.example.consortium.databinding.ActivityHomeBinding
import com.example.consortium.presentation.NewDelivery.NewDeliveryActivity


class DeliveriesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDeliveriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeliveriesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.floatingNewDelivery.setOnClickListener{
            startActivity(NewDeliveryActivity.newIntent(this))
        }
    }
    companion object {
        fun newIntent(context : Context) : Intent {
            return Intent(context, DeliveriesActivity::class.java)
        }
    }
}