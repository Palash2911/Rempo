package com.godspeed.propmart;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00120\nj\b\u0012\u0004\u0012\u00020\u0012`\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00120\nj\b\u0012\u0004\u0012\u00020\u0012`\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/godspeed/propmart/PropertyPageActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/godspeed/propmart/Adapters/DocumentAdapter;", "binding", "Lcom/godspeed/propmart/databinding/ActivityPropertyPageBinding;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "documentList", "Ljava/util/ArrayList;", "Lcom/godspeed/propmart/Models/DocumentModel;", "Lkotlin/collections/ArrayList;", "firebase", "firestore", "getFirestore", "()Lcom/google/firebase/firestore/FirebaseFirestore;", "idList", "", "plotList", "selectedId", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class PropertyPageActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.godspeed.propmart.databinding.ActivityPropertyPageBinding binding;
    private com.google.firebase.firestore.FirebaseFirestore firebase;
    private java.util.ArrayList<com.godspeed.propmart.Models.DocumentModel> documentList;
    private com.godspeed.propmart.Adapters.DocumentAdapter adapter;
    private java.util.ArrayList<java.lang.String> plotList;
    private java.util.ArrayList<java.lang.String> idList;
    private java.lang.String selectedId;
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    
    public PropertyPageActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.firestore.FirebaseFirestore getFirestore() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
}