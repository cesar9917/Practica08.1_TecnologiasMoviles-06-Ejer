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
    private lateinit var btnActualizar: Button
    private lateinit var btnEliminar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editNombre = findViewById(R.id.editNombre)
        editCurso = findViewById(R.id.editCurso)
        editCarrera = findViewById(R.id.editCarrera)

        btnGuardar = findViewById(R.id.btnGuardar)
        btnMostrar = findViewById(R.id.btnMostrar)
        btnActualizar = findViewById(R.id.btnActualizar)
        btnEliminar = findViewById(R.id.btnEliminar)

        btnGuardar.setOnClickListener() {
            guardarEstudiante()
        }

        btnMostrar.setOnClickListener {
            val intent = Intent(this, MostrarEstudiante::class.java)
            startActivity(intent)
        }

        btnActualizar.setOnClickListener {
            actualizarEstudiante()
        }

        btnEliminar.setOnClickListener {
            eliminarEstudiante()
        }

    }

    private fun guardarEstudiante() {
        val db = FirebaseDatabase.getInstance().reference

        val id = db.child("estudiantes").push().key

        val estudiante = Estudiante (
            id,
            nombre = editNombre.text.toString(),
            curso = editCurso.text.toString(),
            carrera = editCarrera.text.toString()
        )

        if (id != null) {
            db.child("estudiantes")
                .child(id)
                .setValue(estudiante)
                .addOnSuccessListener {
                    Toast.makeText(this, "Se guardo en Firebase", Toast.LENGTH_SHORT).show()

                    editNombre.text.clear()
                    editCurso.text.clear()
                    editCarrera.text.clear()
                }
        }
    }

    private fun eliminarEstudiante() {
        val db = FirebaseDatabase.getInstance().reference
        val nombreBuscado = editNombre.text.toString()

        db.child("estudiantes")
            .get()
            .addOnSuccessListener { snapshot ->
                for (dato in snapshot.children) {
                    val estudiante =
                        dato.getValue(Estudiante::class.java)

                    if (estudiante?.nombre == nombreBuscado) {
                        dato.ref.removeValue()
                        Toast.makeText(this,"Eliminado",Toast.LENGTH_SHORT).show()
                        break
                    }
                }
            }
    }

    private fun actualizarEstudiante() {
        val db = FirebaseDatabase.getInstance().reference
        val nombreBuscado = editNombre.text.toString()

        db.child("estudiantes")
            .get()
            .addOnSuccessListener { snapshot ->

                for (dato in snapshot.children) {
                    val estudiante =
                        dato.getValue(Estudiante::class.java)
                    if (estudiante?.nombre == nombreBuscado) {
                        val datos = mapOf<String, Any>(
                            "curso" to editCurso.text.toString(),
                            "carrera" to editCarrera.text.toString()
                        )
                        dato.ref.updateChildren(datos)
                        Toast.makeText(this,"Actualizado",Toast.LENGTH_SHORT).show()
                        break
                    }
                }
            }
    }

}




