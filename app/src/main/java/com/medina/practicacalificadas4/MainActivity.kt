package com.medina.practicacalificadas4

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.medina.practicacalificadas4.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_main.*

const val ACTIVITY_B_REQUEST = 992
const val PARAMETER_EXTRA_NAME = "nom"
const val PARAMETER_EXTRA_EMAIL = "ema"
const val PARAMETER_EXTRA_ADDRESS = "addre"
const val PARAMETER_EXTRA_PHONE = "phone"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


    fun sendWsp(view: android.view.View){

        val phone = binding.textView12.text.toString()
        val url = "https://api.whatsapp.com/send?phone=+51 $phone"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)

    }

    fun send_text(view: android.view.View){
        val phone = binding.textView12.text.toString()

        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone)))
    }

    fun calling(view: android.view.View){
        val phone = binding.textView12.text.toString()
        val intent = Intent()
        intent.action = Intent.ACTION_DIAL
        intent.data = Uri.parse ("tel: +51 $phone")
        startActivity(intent)
    }

    fun sendExplicit(view: android.view.View) {
        val nom = binding.textView9.text.toString()
        val ema = binding.textView10.text.toString()
        val addre = binding.textView11.text.toString()
        val cel = binding.textView12.text.toString()
        validateInputFields(nom, ema, addre, cel)
        goDetailActivity(nom, ema, addre, cel)
    }



    private fun goDetailActivity(nom: String, ema: String, addre: String, cel: String) {
        val intent = Intent(this, edit_profile::class.java)
        intent.putExtra(PARAMETER_EXTRA_NAME, nom)
        intent.putExtra(PARAMETER_EXTRA_EMAIL, ema)
        intent.putExtra(PARAMETER_EXTRA_ADDRESS, addre)
        intent.putExtra(PARAMETER_EXTRA_PHONE, cel)
        startActivityForResult(intent, ACTIVITY_B_REQUEST)
    }

    private fun validateInputFields(nom: String, ema: String, addre: String, cel: String) {
        if (nom.isBlank() && ema.isBlank() && addre.isBlank() && cel.isBlank()) {
            Toast.makeText(this, "Empty fields", Toast.LENGTH_SHORT).show()
            return
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(ContentValues.TAG, "requestCode:$requestCode")
        Log.d(ContentValues.TAG, "resultCode:$resultCode")
        Log.d(ContentValues.TAG, "data:" + android.R.attr.data)

        when (requestCode) {
            ACTIVITY_B_REQUEST -> {
                if (resultCode === RESULT_OK) {
                    val extras = data?.extras

                    if (extras != null) {
                        if (extras.get(PARAMETER_EXTRA_NAME) != null) {
                            binding.textView9.text = extras.getString(PARAMETER_EXTRA_NAME)
                        }

                        if (extras.get(PARAMETER_EXTRA_EMAIL) != null) {
                            binding.textView10.text = extras.getString(PARAMETER_EXTRA_EMAIL)
                        }

                        if (extras.get(PARAMETER_EXTRA_ADDRESS) != null) {
                            binding.textView11.text = extras.getString(PARAMETER_EXTRA_ADDRESS)
                        }

                        if (extras.get(PARAMETER_EXTRA_PHONE) != null) {
                            binding.textView12.text = extras.getString(PARAMETER_EXTRA_PHONE)
                        }
                    }
                }

            }
        }

    }

}

