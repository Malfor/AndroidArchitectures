package com.rodolfo.androidarchitectures.mvc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rodolfo.androidarchitectures.databinding.ActivityMvcBinding

class MVCActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvcBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "MVC Activity"
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MVCActivity::class.java)
    }
}