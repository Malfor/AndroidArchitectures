package com.rodolfo.androidarchitectures

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rodolfo.androidarchitectures.databinding.ActivityArchitecturesBinding
import com.rodolfo.androidarchitectures.mvc.view.MVCActivity
import com.rodolfo.androidarchitectures.mvp.view.MVPActivity
import com.rodolfo.androidarchitectures.mvvm.MVVMActivity

class ArchitecturesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArchitecturesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArchitecturesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            buttonMvc.setOnClickListener { startActivity(MVCActivity.getIntent(this@ArchitecturesActivity)) }
            buttonMvp.setOnClickListener { startActivity(MVPActivity.getIntent(this@ArchitecturesActivity)) }
            buttonMvvm.setOnClickListener { startActivity(MVVMActivity.getIntent(this@ArchitecturesActivity)) }
        }
    }
}