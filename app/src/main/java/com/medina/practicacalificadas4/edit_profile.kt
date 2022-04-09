package com.medina.practicacalificadas4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_main.*

class edit_profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        val extras = this.intent.extras
        if (extras != null) {
            if (extras.get(PARAMETER_EXTRA_NAME) != null) {
                edt_nombre.setText(extras.getString(PARAMETER_EXTRA_NAME))
            }

            if (extras.get(PARAMETER_EXTRA_EMAIL) != null) {
                edt_email.setText(extras.getString(PARAMETER_EXTRA_EMAIL))
            }

            if (extras.get(PARAMETER_EXTRA_ADDRESS) != null) {
                edt_oficina.setText(extras.getString(PARAMETER_EXTRA_ADDRESS))
            }

            if (extras.get(PARAMETER_EXTRA_PHONE) != null) {
                edt_telefono.setText(extras.getString(PARAMETER_EXTRA_PHONE))
            }
        }
    }

    fun sendClose(view: android.view.View) {
        val intent = Intent()
        intent.putExtra("nom", edt_nombre.getText().toString())
        intent.putExtra("ema", edt_email.getText().toString())
        intent.putExtra("addre", edt_oficina.getText().toString())
        intent.putExtra("phone", edt_telefono.getText().toString())
        setResult(RESULT_OK, intent)
        finish()
    }

}