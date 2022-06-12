package com.godspeed.propmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import com.godspeed.propmart.databinding.ActivityAadharverifiedBinding
import com.godspeed.propmart.databinding.ActivityWebViewBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.net.URLEncoder

class WebView : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater);
        setContentView(binding.root)

        val pdf = intent.getStringExtra("downloadUrl");
        val url = URLEncoder.encode(pdf,"UTF-8")
        Log.d("DownloadIrl",url.toString());
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.setSupportZoom(true)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://docs.google.com/gview?embedded=true&url=$url");

    }


}