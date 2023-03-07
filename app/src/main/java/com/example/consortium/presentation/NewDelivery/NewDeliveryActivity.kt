package com.example.consortium.presentation.NewDelivery

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.consortium.R

class NewDeliveryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_delivery)
    }
    companion object {
        fun newIntent(context : Context) : Intent {
            return Intent(context, NewDeliveryActivity::class.java)
        }
    }
}