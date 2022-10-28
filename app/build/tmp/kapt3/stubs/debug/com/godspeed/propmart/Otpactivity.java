package com.godspeed.propmart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0016H\u0014J\b\u0010\u001a\u001a\u00020\u0016H\u0002J\b\u0010\u001b\u001a\u00020\u0016H\u0002J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/godspeed/propmart/Otpactivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "binding", "Lcom/godspeed/propmart/databinding/ActivityOtpactivityBinding;", "callbacks", "Lcom/google/firebase/auth/PhoneAuthProvider$OnVerificationStateChangedCallbacks;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "messaging", "Lcom/google/firebase/messaging/FirebaseMessaging;", "num", "getNum", "()Ljava/lang/String;", "setNum", "(Ljava/lang/String;)V", "storedVerificationId", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "reset", "sendOtp", "signInWithPhoneAuthCredential", "credential", "Lcom/google/firebase/auth/PhoneAuthCredential;", "verifyOtp", "app_debug"})
public final class Otpactivity extends androidx.appcompat.app.AppCompatActivity {
    private final java.lang.String TAG = "Login";
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    private java.lang.String storedVerificationId = "";
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    private com.google.firebase.messaging.FirebaseMessaging messaging;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String num = "";
    private com.godspeed.propmart.databinding.ActivityOtpactivityBinding binding;
    private final com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks = null;
    
    public Otpactivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNum() {
        return null;
    }
    
    public final void setNum(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @java.lang.Override()
    protected void onStart() {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void verifyOtp() {
    }
    
    private final void sendOtp() {
    }
    
    private final void signInWithPhoneAuthCredential(com.google.firebase.auth.PhoneAuthCredential credential) {
    }
    
    private final void reset() {
    }
}