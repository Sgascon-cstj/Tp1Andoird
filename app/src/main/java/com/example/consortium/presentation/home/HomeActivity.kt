package com.example.consortium.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.consortium.R
import com.example.consortium.core.Constants.dataStore

import com.example.consortium.databinding.ActivityHomeBinding
import com.example.consortium.presentation.delivery.DeliveriesActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private  val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.homeUiState.onEach {
            when(it){
                HomeUiState.Empty -> Unit
                is HomeUiState.Success ->{
                    binding.txvFr.text = String.format("%.2f", it.trader.froynyx)
                    binding.txvK.text = String.format("%.2f", it.trader.kreotrium)
                    binding.txvVe.text = String.format("%.2f", it.trader.vethynx)
                    binding.txvYe.text = String.format("%.2f", it.trader.yerfrium)
                    binding.txvZ.text = String.format("%.2f", it.trader.zuscum)
                    binding.txtName.setText(it.trader.name)
                }
            }
        }.launchIn(lifecycleScope)

        binding.btnOpen.setOnClickListener {
            if (binding.txtName.text.isNotEmpty()){
                viewModel.save()
                startActivity(DeliveriesActivity.newIntent(this))
            }else{
                Toast.makeText(this,getString(R.string.emptyUserName),Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnChargement.setOnClickListener{
            if (binding.txtName.text.isNotEmpty())
            {
                viewModel.recharge()
                Toast.makeText(this, getString(R.string.rechargeComplete), Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,getString(R.string.emptyUserName),Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnTeleverser.setOnClickListener{
            if (binding.txtName.text.isNotEmpty())
            {
                viewModel.upload()
                Toast.makeText(this, getString(R.string.uploadComplete), Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,getString(R.string.emptyUserName),Toast.LENGTH_SHORT).show()
            }
        }
    }
}