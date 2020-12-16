package com.example.contactosmyphp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import models.Contactos
import kotlinx.android.synthetic.main.activity_main3.*

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainInazuma : AppCompatActivity() ,RecyclerAdapter2.OnContactoClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        conexion()
    }

    fun conexion() {
        val listaContactos: ArrayList<Contactos> = ArrayList<Contactos>()
        recyclerView2.layoutManager = LinearLayoutManager(this)
        recyclerView2.addItemDecoration(
            (DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            ))
        )
        val BASE_URL = "http://iesayala.ddns.net/SergioMR/Inazuma.php"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET,
            BASE_URL,
            Response.Listener { response ->
                val jsonArray = JSONArray(response)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = JSONObject(jsonArray.getString(i))

                    val nombre = jsonObject.get("Nombre")
                    val telefono = jsonObject.get("Apellido")
                    val correo = jsonObject.get("Dorsal")
                    val image = jsonObject.get("Imagen")
                    listaContactos.add(
                        Contactos(
                            nombre = nombre.toString(),
                            telefono = telefono.toString(),
                            correo = correo.toString(),
                            image = image.toString()
                        )
                    )
                }
                recyclerView2.adapter = RecyclerAdapter2(this, listaContactos, this)
            }, Response.ErrorListener { Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show() })
        queue.add(stringRequest)
    }

    override fun onImageClick(imagen: String) {
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("imageURL", imagen)
        startActivity(intent)
    }

    @Override
    override fun onItemClick(nombre: String, telefono: String, correo: String) {
        Toast.makeText(this, nombre + " " + telefono + " " + correo, Toast.LENGTH_SHORT).show()
    }
}