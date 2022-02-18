package com.rodolfo.androidarchitectures.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rodolfo.androidarchitectures.databinding.ActivityMvpBinding

class MVPActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "MVP Activity"
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVPActivity::class.java)
    }
}