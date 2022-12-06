package com.godspeed.propmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.godspeed.propmart.databinding.ActivityChooseBinding

class ChooseActivity : AppCompatActivity() {

    lateinit var binding:ActivityChooseBinding;

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityChooseBinding.inflate(layoutInflater);
        super.onCreate(savedInstanceState)
        setContentView(binding.root);

        binding.plot.setOnClickListener {
            val intent:Intent = Intent(this , sellProp::class.java);
            startActivity(intent);

        }

        binding.layout.setOnClickListener {
            val intent:Intent = Intent(this , Visitweb::class.java);
            startActivity(intent);
        }
    }
}