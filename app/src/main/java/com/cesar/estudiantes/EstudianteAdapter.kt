package com.cesar.estudiantes

import androidx.recyclerview.widget.RecyclerView
import android.view.*
import android.widget.*


class EstudianteAdapter(private val lista: ArrayList<Estudiante>): RecyclerView.Adapter<EstudianteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.textNombre)
        val curso: TextView = itemView.findViewById(R.id.textCurso)
        val carrera: TextView = itemView.findViewById(R.id.textCarrera)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_estudiante, parent, false)

        return ViewHolder(vista)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estudiante = lista[position]

        holder.nombre.text = estudiante.nombre
        holder.curso.text = estudiante.curso
        holder.carrera.text = estudiante.carrera
    }
}