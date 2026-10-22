package com.example.b18

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.b18.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val empty = getString(R.string.not_available)

        binding.tvName.text = intent.getStringExtra(EXTRA_NAME) ?: empty
        binding.tvEmail.text = intent.getStringExtra(EXTRA_EMAIL) ?: empty
        binding.tvPhone.text = intent.getStringExtra(EXTRA_PHONE) ?: empty
        binding.tvStudentId.text = intent.getStringExtra(EXTRA_STUDENT_ID) ?: empty
        binding.tvClass.text = intent.getStringExtra(EXTRA_CLASS) ?: empty

        binding.btnGoRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_NAME = "EXTRA_NAME"
        const val EXTRA_EMAIL = "EXTRA_EMAIL"
        const val EXTRA_PHONE = "EXTRA_PHONE"
        const val EXTRA_STUDENT_ID = "EXTRA_STUDENT_ID"
        const val EXTRA_CLASS = "EXTRA_CLASS"
    }
}