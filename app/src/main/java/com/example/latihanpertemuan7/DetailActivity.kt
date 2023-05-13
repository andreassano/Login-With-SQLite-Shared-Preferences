package com.example.latihanpertemuan7

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.latihanpertemuan7.databinding.ActivityDetailBinding
import com.example.latihanpertemuan7.databinding.ActivityRegisterBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var sharePrefs : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharePrefs = getSharedPreferences("DataUser", Context.MODE_PRIVATE)

        val email = sharePrefs.getString("user_email","")
        val password = sharePrefs.getString("user_password","")

        binding.txtEmail.text = "Email : ${email.toString()}"
        binding.txtPassword.text = "Password : ${password.toString()}"

        binding.btnLogout.setOnClickListener {
            with(sharePrefs.edit()){
                clear()
                apply()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}