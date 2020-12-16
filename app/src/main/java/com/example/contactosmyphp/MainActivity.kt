package com.example.contactosmyphp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import models.Contactos
import kotlinx.android.synthetic.main.activity_main.*

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() ,RecyclerAdapter.OnContactoClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        conexion()
    }

    fun conexion(){
        val listaContactos : ArrayList<Contactos>  = ArrayList<Contactos>()
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.addItemDecoration((DividerItemDecoration(this,DividerItemDecoration.VERTICAL)))
        val BASE_URL = "http://iesayala.ddns.net/SergioMR/contactos.php"
        val queue = Volley.newRequestQueue(this)
        val stringRequest= StringRequest(
            Request.Method.GET,
            BASE_URL,
            Response.Listener { response ->
                val jsonArray = JSONArray(response)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = JSONObject(jsonArray.getString(i))

                    val nombre = jsonObject.get("nombre")
                    val telefono = jsonObject.get("telefono")
                    val correo = jsonObject.get("correo")
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
                recyclerView.adapter=RecyclerAdapter(this,listaContactos,this)
            }, Response.ErrorListener { Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show() })
        queue.add(stringRequest)
    }

    override fun onImageClick(imagen: String) {
        val intent=Intent(this,MainActivity2::class.java)
        intent.putExtra("imageURL",imagen)
        startActivity(intent)
    }

    @Override
    override fun onItemClick(nombre: String, telefono: String, correo:String) {
        Toast.makeText(this, nombre+" "+telefono+" "+correo, Toast.LENGTH_SHORT).show()
    }
}