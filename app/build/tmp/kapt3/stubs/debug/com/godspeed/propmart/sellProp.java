package com.godspeed.propmart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J0\u0010%\u001a\u00020\"2\f\u0010&\u001a\b\u0012\u0002\b\u0003\u0018\u00010\'2\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020,H\u0016J\u0016\u0010-\u001a\u00020\"2\f\u0010&\u001a\b\u0012\u0002\b\u0003\u0018\u00010\'H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R-\u0010\u001a\u001a\u001e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001bj\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d`\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 \u00a8\u0006."}, d2 = {"Lcom/godspeed/propmart/sellProp;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/widget/AdapterView$OnItemSelectedListener;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "binding", "Lcom/godspeed/propmart/databinding/ActivitySellPropBinding;", "getBinding", "()Lcom/godspeed/propmart/databinding/ActivitySellPropBinding;", "setBinding", "(Lcom/godspeed/propmart/databinding/ActivitySellPropBinding;)V", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "i", "", "getI", "()I", "setI", "(I)V", "naPlot", "", "getNaPlot", "()Z", "setNaPlot", "(Z)V", "newPlot", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "getNewPlot", "()Ljava/util/HashMap;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onItemSelected", "p0", "Landroid/widget/AdapterView;", "p1", "Landroid/view/View;", "p2", "p3", "", "onNothingSelected", "app_debug"})
public final class sellProp extends androidx.appcompat.app.AppCompatActivity implements android.widget.AdapterView.OnItemSelectedListener {
    public com.godspeed.propmart.databinding.ActivitySellPropBinding binding;
    private boolean naPlot = false;
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.HashMap<java.lang.String, java.lang.Object> newPlot = null;
    private int i = 0;
    
    public sellProp() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.godspeed.propmart.databinding.ActivitySellPropBinding getBinding() {
        return null;
    }
    
    public final void setBinding(@org.jetbrains.annotations.NotNull()
    com.godspeed.propmart.databinding.ActivitySellPropBinding p0) {
    }
    
    public final boolean getNaPlot() {
        return false;
    }
    
    public final void setNaPlot(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<java.lang.String, java.lang.Object> getNewPlot() {
        return null;
    }
    
    public final int getI() {
        return 0;
    }
    
    public final void setI(int p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onItemSelected(@org.jetbrains.annotations.Nullable()
    android.widget.AdapterView<?> p0, @org.jetbrains.annotations.Nullable()
    android.view.View p1, int p2, long p3) {
    }
    
    @java.lang.Override()
    public void onNothingSelected(@org.jetbrains.annotations.Nullable()
    android.widget.AdapterView<?> p0) {
    }
}