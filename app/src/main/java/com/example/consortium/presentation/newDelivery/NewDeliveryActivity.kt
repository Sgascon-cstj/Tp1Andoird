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
                        if (it.trader.froynyx <= 0){
                            sldElement1.isEnabled = false
                            txvNumberElement1.text = "0"
                        }else{
                            sldElement1.valueTo = it.trader.froynyx
                            sldElement1.value = it.trader.froynyx
                        }
                        if (it.trader.kreotrium <= 0){
                            sldElement2.isEnabled= false
                            txvNumberElement2.text = "0"

                        }else{
                            sldElement2.valueTo = it.trader.kreotrium
                            sldElement2.value = it.trader.kreotrium
                        }
                        if (it.trader.vethynx <= 0){
                            sldElement3.isEnabled= false
                            txvNumberElement3.text = "0"

                        }else{
                            sldElement3.valueTo = it.trader.vethynx
                            sldElement3.value = it.trader.vethynx
                        }
                        if (it.trader.yerfrium <= 0){
                            sldElement4.isEnabled= false
                            txvNumberElement4.text = "0"

                        }else{
                            sldElement4.valueTo = it.trader.yerfrium
                            sldElement4.value = it.trader.yerfrium
                        }
                        if (it.trader.zuscum <= 0){
                            sldElement5.isEnabled= false
                            txvNumberElement5.text = "0"

                        }else{
                            sldElement5.valueTo = it.trader.zuscum
                            sldElement5.value = it.trader.zuscum
                        }


                    }
                }
                NewDeliveriesUiState.Completed -> {
                    Toast.makeText(this,getString(R.string.deliverySaved),Toast.LENGTH_SHORT).show()
                    with(binding){
                        sldElement1.isEnabled = false
                        sldElement2.isEnabled = false
                        sldElement3.isEnabled = false
                        sldElement4.isEnabled = false
                        sldElement5.isEnabled = false

                    }
                    startActivity(DeliveriesActivity.newIntent(this))
                }
                NewDeliveriesUiState.Error -> {
                    Toast.makeText(this, getString(R.string.errorElementGreaterThanOne), Toast.LENGTH_SHORT).show()
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