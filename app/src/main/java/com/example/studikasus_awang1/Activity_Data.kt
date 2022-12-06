package com.example.studikasus_awang1

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.studikasus_awang1.databinding.ActivityDataBinding
import com.google.firebase.firestore.FirebaseFirestore

class Activity_Data : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityDataBinding
        lateinit var db: FirebaseFirestore
        binding = ActivityDataBinding.inflate(LayoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        db.collection("Kredits")
            .get()
            .addOnSuccessListener {
                binding.edtNominal.setText(it.documents.last().data?.get("nominal").tostring())
                binding.edtTenor.setText(it.documents.last().data?.get("Tenor").tostring())
                binding.edtAngsuran.setText(it.documents.last().data?.get("angsuran").tostring())
            }
        binding.btnDelete.setOnClickListener{
            delete()
            val intent = intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun delete(){
        db.collection("kredits").document("AihtONs7x9Qq8CK5rITF")
            .delete()
            .addSuccesListener{ Log.d(TAG, "DocumentSnapshot succesfully delete!")}
            .addFailureListener{ e -> Log.w(TAG, "error Delete Document", e)}

    }
}