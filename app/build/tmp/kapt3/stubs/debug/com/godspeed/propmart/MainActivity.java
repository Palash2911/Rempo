package com.godspeed.propmart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0013H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/godspeed/propmart/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "messaging", "Lcom/google/firebase/messaging/FirebaseMessaging;", "phone", "Landroid/widget/EditText;", "getPhone", "()Landroid/widget/EditText;", "setPhone", "(Landroid/widget/EditText;)V", "storedVerificationId", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private final java.lang.String TAG = "Login";
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    private java.lang.String storedVerificationId = "";
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    private com.google.firebase.messaging.FirebaseMessaging messaging;
    public android.widget.EditText phone;
    
    public MainActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.EditText getPhone() {
        return null;
    }
    
    public final void setPhone(@org.jetbrains.annotations.NotNull()
    android.widget.EditText p0) {
    }
    
    @java.lang.Override()
    protected void onStart() {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
}