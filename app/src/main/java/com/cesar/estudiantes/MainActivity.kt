package com.cesar.estudiantes

import android.content.Intent
import android.widget.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var editNombre: EditText
    private lateinit var editCurso: EditText
    private lateinit var editCarrera: EditText

    private lateinit var btnGuardar: Button
    private lateinit var btnMostrar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editNombre = findViewById(R.id.editNombre)
        editCurso = findViewById(R.id.editCurso)
        editCarrera = findViewById(R.id.editCarrera)

        btnGuardar = findViewById(R.id.btnGuardar)
        btnMostrar = findViewById(R.id.btnMostrar)

        btnGuardar.setOnClickListener() {
            guardarEstudiante()
        }

        btnMostrar.setOnClickListener {
            val intent = Intent(this, MostrarEstudiante::class.java)
            startActivity(intent)
        }


    }

    private fun guardarEstudiante() {
        val db = FirebaseDatabase.getInstance().reference

        val estudiante = Estudiante (
            nombre = editNombre.text.toString(),
            curso = editCurso.text.toString(),
            carrera = editCarrera.text.toString()
        )

        db.child("estudiantes")
            .push()
            .setValue(estudiante)
            .addOnSuccessListener {
                Toast.makeText(this, "Se guardo en Firebase", Toast.LENGTH_SHORT).show()

                editNombre.text.clear()
                editCurso.text.clear()
                editCarrera.text.clear()
            }
    }
}




