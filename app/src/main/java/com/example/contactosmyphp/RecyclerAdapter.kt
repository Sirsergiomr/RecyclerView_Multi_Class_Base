package com.example.contactosmyphp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import models.Contactos
import kotlinx.android.synthetic.main.contactos_row.view.*
import java.lang.IllegalArgumentException

class RecyclerAdapter(
    val context:Context,
    val listaContactos:List<Contactos>,
    private val itemClickListener: OnContactoClickListener
):RecyclerView.Adapter<BaseViewHolder<*>>() {
    interface  OnContactoClickListener{
        fun onImageClick(imagen: String)
        fun onItemClick(nombre: String, telefono: String, correo: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        // inflamos vista
        return ContactosViewHolder(LayoutInflater.from(context).inflate(R.layout.contactos_row,parent,false))

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        //carga datos en lista
        if (holder is ContactosViewHolder)
            holder.bind(listaContactos[position],position)
        else
            throw IllegalArgumentException("Error viewHolder erroneo")
    }

    override fun getItemCount(): Int =  listaContactos.size          //número de items



    inner class ContactosViewHolder(itemView:View):BaseViewHolder<Contactos>(itemView)// nos aseguramos de que cuando la clase padre muera, muera esta también
    {
        override fun bind(item: Contactos, position: Int) {
            Glide.with(context).load(item.image).into(itemView.imageView)

            itemView.textViewNombrePl.text="Nombre: "+item.nombre
            itemView.textViewApellidoPl.text = "Telefono: "+item.telefono
            itemView.textViewDorsal.text="Correo: "+item.correo

            itemView.setOnClickListener {
                itemClickListener.onItemClick(item.nombre,item.telefono,item.correo)
            }
            itemView.imageView.setOnClickListener {
                itemClickListener.onImageClick(item.image)
            }
        }
    }

}