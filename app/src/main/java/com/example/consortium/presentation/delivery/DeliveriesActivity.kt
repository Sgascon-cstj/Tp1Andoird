package com.example.consortium.presentation.delivery

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.consortium.R
import com.example.consortium.databinding.ActivityDeliveriesBinding
import com.example.consortium.presentation.delivery.adapters.DeliveryRecyclerViewAdapters
import com.example.consortium.presentation.newDelivery.NewDeliveryActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class DeliveriesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDeliveriesBinding
    private  val viewModel : DeliveriesViewModel by viewModels()

    private val deliveryRecyclerViewAdapter = DeliveryRecyclerViewAdapters(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeliveriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rcvItems.layoutManager = GridLayoutManager(this,1)
        binding.rcvItems.adapter = deliveryRecyclerViewAdapter

        viewModel.deliveriesUiState.onEach {
            when(it){
                DeliveriesUiState.Empty -> Unit
                is DeliveriesUiState.Success -> {
                    deliveryRecyclerViewAdapter.deliveries = it.deliveries.asReversed()
                    deliveryRecyclerViewAdapter.notifyDataSetChanged()


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