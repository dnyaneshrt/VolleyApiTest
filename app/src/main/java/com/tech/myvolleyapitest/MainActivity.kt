package com.tech.myvolleyapitest

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadImage()
        btn_get_image.setOnClickListener {
            loadImage()
        }
    }

    private fun loadImage() {

        progressBar.visibility = View.VISIBLE
// Instantiate the RequestQueue.
       // val queue = Volley.newRequestQueue(this)

        val url = "https://meme-api.herokuapp.com/gimme/wholesomememes"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                var url = response.getString("url")

                Glide.with(this).load(url).listener(
                    object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            Toast.makeText(
                                this@MainActivity,
                                "failed to load image",
                                Toast.LENGTH_SHORT
                            ).show()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE

                            return false
                        }

                    }
                ).into(imageView)

            },
            Response.ErrorListener {
            })

// Add the request to the RequestQueue.
     //   queue.add(jsonObjectRequest)
        //using singleton
        MySinglton.getInstance(this).addToRequestQueue(jsonObjectRequest)


    }
}