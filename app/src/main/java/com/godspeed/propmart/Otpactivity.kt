package com.godspeed.propmart

import `in`.aabhasjindal.otptextview.OtpTextView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.godspeed.propmart.databinding.ActivityOtpactivityBinding
import com.godspeed.propmart.databinding.ActivityProfileBinding
import com.google.common.base.Verify.verify
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FcmBroadcastProcessor.reset
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import org.w3c.dom.Text
import java.util.concurrent.TimeUnit

class Otpactivity : AppCompatActivity() {

    private val TAG = "Login"
    private val auth = FirebaseAuth.getInstance()
    private var storedVerificationId: String = ""
    private val db = Firebase.firestore
    private lateinit var messaging: FirebaseMessaging
    var num: String = ""
    private lateinit var binding: ActivityOtpactivityBinding

    override fun onStart() {
        super.onStart()
        messaging = FirebaseMessaging.getInstance();
        if (auth.currentUser != null) {
            db.collection("Users").document(auth.currentUser!!.uid).get()
                .addOnCompleteListener { task2 ->
                    if (task2.result?.exists() == true) {
                        val intent = Intent(this, Bottomtab::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        auth.signOut()
                    }
                }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpactivity)
        binding = ActivityOtpactivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        num = intent.extras?.get("Number").toString()
        val verify = findViewById<Button>(R.id.verifyotp)
        val editnum = findViewById<TextView>(R.id.editphoneno)
        val resendotp = findViewById<TextView>(R.id.resendotp)
        binding.numbersent.text = "Please Enter the OTP we just sent on +91 " + num.substring(0, 1) + "*******" + num.substring(8);
        editnum.setOnClickListener{
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        resendotp.setOnClickListener{
            Toast.makeText(this, "OTP RESENT SUCCESSFULLY!", Toast.LENGTH_SHORT).show()
            sendOtp()
        }
        sendOtp()

        verify.setOnClickListener {
            Log.d("Number", binding.otpView.otp.toString())
            verifyOtp()
        }
    }

    private fun verifyOtp(){
        if (binding.otpView.otp.toString().isEmpty() || binding.otpView.otp.toString().length < 6){
            binding.otpView.showError()
            Toast.makeText(this, "Please enter valid 6 digit OTP!", Toast.LENGTH_SHORT).show()
            return
        }
        val otpNumber = binding.otpView.otp.toString()
        Log.d("OTPnum", otpNumber)
        val credential = PhoneAuthProvider.getCredential(storedVerificationId, otpNumber)
        signInWithPhoneAuthCredential(credential)
    }

    private fun sendOtp(){
        if (num.isEmpty() || num.length < 10){
            Toast.makeText(this, "Please enter valid phone number!", Toast.LENGTH_SHORT).show()
            return
        }
        val phoneNumber = "+91 $num"

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d(TAG, "onVerificationCompleted: $credential")
            Firebase.messaging.subscribeToTopic("all").addOnSuccessListener {
                Log.e("", "Added to Bookmark list");
            }
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.w(TAG, "onVerificationFailed", e)
            if (e is FirebaseAuthInvalidCredentialsException) {
                Toast.makeText(
                    applicationContext,
                    "Invalid Request! Contact Developer!",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Toast.makeText(
                    applicationContext,
                    "SMS Quota Reached! Contact Developer!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            reset()
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {

            Log.d(TAG, "onCodeSent:$verificationId")
            storedVerificationId = verificationId
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    db.collection("Users").document(auth.currentUser!!.uid).get()
                        .addOnCompleteListener{task2->
                            if(task2.result?.exists() == true ){
                                binding.otpView.showSuccess()
                                binding.otpView.setOTP(credential.smsCode.toString())
                                val intent = Intent(this, Bottomtab::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                binding.otpView.showSuccess()
                                val intent = Intent(this, Profile::class.java)
                                intent.putExtra("Number", num)
                                startActivity(intent)
                                finish()
                            }
                        }.addOnCanceledListener {
                            Toast.makeText(this, "User not found !", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        binding.otpView.showError()
                        Toast.makeText(this, "Invalid code, Try Again!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                    }
                }
            }.addOnCanceledListener {
                Toast.makeText(this, "Some Error Occurred !", Toast.LENGTH_SHORT).show()
            }
    }

    private fun reset (){
        auth.signOut()
    }
}