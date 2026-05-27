package com.cesar.estudiantes

import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import android.os.Bundle
import androidx.recyclerview.widget.*
import com.google.firebase.database.*


class MostrarEstudiante : AppCompatActivity() {

    private lateinit var lista: ArrayList<Estudiante>
    private lateinit var recycler: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mostrar_estudiantes)

        recycler = findViewById(R.id.recyclerEstudiantes)

        recycler.layoutManager = LinearLayoutManager(this)
        lista = ArrayList()

        leerEstudiantes()
    }

    private fun leerEstudiantes() {
        val db = FirebaseDatabase.getInstance().reference

        db.child("estudiantes")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot : DataSnapshot) {
                    lista.clear()

                    for (dato in snapshot.children) {
                        val estudiante = dato.getValue(Estudiante::class.java)

                        if (estudiante != null) {
                            lista.add(estudiante)
                        }
                    }
                    recycler.adapter = EstudianteAdapter(lista)
                }
                override fun onCancelled(error: DatabaseError) {

                }
            })
    }
}