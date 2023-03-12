package com.example.consortium.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope

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
                    binding.txvFr.text = it.trader.froynyx.toString()
                    binding.txvK.text = it.trader.kreotrium.toString()
                    binding.txvVe.text = it.trader.vethynx.toString()
                    binding.txvYe.text = it.trader.yerfrium.toString()
                    binding.txvZ.text = it.trader.zuscum.toString()
                    binding.txtName.setText(it.trader.name)
                }
            }
        }.launchIn(lifecycleScope)

        binding.btnOpen.setOnClickListener {
            if (binding.txtName.text.isNotEmpty()){
                viewModel.save()
                startActivity(DeliveriesActivity.newIntent(this))
            }else{
                Toast.makeText(this,"Username is empty",Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnChargement.setOnClickListener{
            if (binding.txtName.text.isNotEmpty())
            {
                viewModel.recharge()
            }else{
                Toast.makeText(this,"Username is empty",Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnTeleverser.setOnClickListener{
            if (binding.txtName.text.isNotEmpty())
            {
                viewModel.upload()
            }else{
                Toast.makeText(this,"Username is empty",Toast.LENGTH_SHORT).show()
            }
        }
    }
}