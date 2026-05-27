package com.cesar.estudiantes

import android.widget.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    private lateinit var editNombre: EditText
    private lateinit var editCurso: EditText
    private lateinit var editCarrera: EditText

    private lateinit var btnGuardar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editNombre = findViewById(R.id.editNombre)
        editCurso = findViewById(R.id.editCurso)
        editCarrera = findViewById(R.id.editCarrera)

        btnGuardar = findViewById(R.id.btnGuardar)



    }

}




