package com.example.consortium.presentation.newDelivery

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.consortium.R
import com.example.consortium.databinding.ActivityHomeBinding
import com.example.consortium.databinding.ActivityNewDeliveryBinding
import com.example.consortium.presentation.delivery.DeliveriesActivity
import com.example.consortium.presentation.home.HomeActivity
import com.example.consortium.presentation.home.HomeViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.DecimalFormat
import kotlin.math.round
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class NewDeliveryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNewDeliveryBinding
    private  val viewModel: NewDeliveriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sldElement1.addOnChangeListener { slider, value, fromUser ->
            binding.txvNumberElement1.text = String.format("%.2f", value)
        }
        binding.sldElement2.addOnChangeListener { slider, value, fromUser ->
            binding.txvNumberElement2.text = String.format("%.2f", value)
        }
        binding.sldElement3.addOnChangeListener { slider, value, fromUser ->
            binding.txvNumberElement3.text = String.format("%.2f", value)
        }
        binding.sldElement4.addOnChangeListener { slider, value, fromUser ->
            binding.txvNumberElement4.text = String.format("%.2f", value)
        }
        binding.sldElement5.addOnChangeListener { slider, value, fromUser ->
            binding.txvNumberElement5.text = String.format("%.2f", value)
        }

        binding.btnSave.setOnClickListener{
            viewModel.save(binding.sldElement1.value,
                binding.sldElement2.value,
                binding.sldElement3.value,
                binding.sldElement4.value,
                binding.sldElement5.value)
        }

        viewModel.newDeliveriesUiState.onEach {
            when(it){
                NewDeliveriesUiState.Empty -> Unit
                is NewDeliveriesUiState.Success -> {
                    with(binding){
                        sldElement1.valueTo = it.trader.froynyx
                        sldElement1.value = it.trader.froynyx
                        sldElement2.valueTo = it.trader.kreotrium
                        sldElement2.value = it.trader.kreotrium
                        sldElement3.valueTo = it.trader.vethynx
                        sldElement3.value = it.trader.vethynx
                        sldElement4.valueTo = it.trader.yerfrium
                        sldElement4.value = it.trader.yerfrium
                        sldElement5.valueTo = it.trader.zuscum
                        sldElement5.value = it.trader.zuscum
                    }
                }
                NewDeliveriesUiState.Completed -> {
                    Toast.makeText(this,"Delivery saved",Toast.LENGTH_SHORT).show()
                    startActivity(DeliveriesActivity.newIntent(this))
                }
            }
        }.launchIn(lifecycleScope)

    }
    companion object {
        fun newIntent(context : Context) : Intent {
            return Intent(context, NewDeliveryActivity::class.java)
        }
    }
}