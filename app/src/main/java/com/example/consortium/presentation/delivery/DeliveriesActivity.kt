package com.example.consortium.presentation.delivery

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.consortium.R
import com.example.consortium.databinding.ActivityDeliveriesBinding
import com.example.consortium.presentation.newDelivery.NewDeliveryActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class DeliveriesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDeliveriesBinding
    private  val viewModel : DeliveriesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeliveriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.deliveriesUiState.onEach {
            when(it){
                DeliveriesUiState.Empty -> Unit
                is DeliveriesUiState.Success -> {
                    binding.txvBonRetour.text = getString(R.string.welcomBack,it.name)

                }
            }
        }.launchIn(lifecycleScope)

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