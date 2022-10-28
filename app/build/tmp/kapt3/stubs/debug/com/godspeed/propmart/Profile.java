package com.godspeed.propmart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u000e\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 J\b\u0010!\u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\"\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u00a8\u0006\""}, d2 = {"Lcom/godspeed/propmart/Profile;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/godspeed/propmart/databinding/ActivityProfileBinding;", "cal", "Ljava/util/Calendar;", "kotlin.jvm.PlatformType", "getCal", "()Ljava/util/Calendar;", "setCal", "(Ljava/util/Calendar;)V", "datepick", "Landroid/widget/Button;", "getDatepick", "()Landroid/widget/Button;", "setDatepick", "(Landroid/widget/Button;)V", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "num", "", "getNum", "()Ljava/lang/String;", "setNum", "(Ljava/lang/String;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "openDatepicker", "view", "Landroid/view/View;", "submitProf", "app_debug"})
public final class Profile extends androidx.appcompat.app.AppCompatActivity {
    private com.godspeed.propmart.databinding.ActivityProfileBinding binding;
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String num = "";
    public android.widget.Button datepick;
    private java.util.Calendar cal;
    
    public Profile() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNum() {
        return null;
    }
    
    public final void setNum(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.Button getDatepick() {
        return null;
    }
    
    public final void setDatepick(@org.jetbrains.annotations.NotNull()
    android.widget.Button p0) {
    }
    
    public final java.util.Calendar getCal() {
        return null;
    }
    
    public final void setCal(java.util.Calendar p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void submitProf() {
    }
    
    public final void openDatepicker(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
}