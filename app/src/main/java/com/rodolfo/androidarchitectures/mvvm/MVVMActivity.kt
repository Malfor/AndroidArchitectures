package com.rodolfo.androidarchitectures.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rodolfo.androidarchitectures.R
import com.rodolfo.androidarchitectures.databinding.ActivityMvvmBinding

class MVVMActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvvmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "MVVM Activity"
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVVMActivity::class.java)
    }
}