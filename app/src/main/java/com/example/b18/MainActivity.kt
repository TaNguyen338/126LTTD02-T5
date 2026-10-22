package com.example.b18

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.b18.databinding.ActivityMainBinding
import com.example.b18.utils.onTextChange
import com.example.b18.utils.trimmedText

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Kiểm tra thời gian thực khi gõ phím
        binding.edtName.onTextChange { validateName() }
        binding.edtEmail.onTextChange { validateEmail() }
        binding.edtPhone.onTextChange { validatePhone() }
        binding.edtStudentId.onTextChange { validateStudentId() }
        binding.edtClass.onTextChange { validateClass() }

        // Đăng ký: kiểm tra toàn Form, hợp lệ thì sang màn hình Chi tiết
        binding.btnRegister.setOnClickListener {
            val allValid = listOf(
                validateName(),
                validateEmail(),
                validatePhone(),
                validateStudentId(),
                validateClass()
            ).all { it }

            if (allValid) {
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_NAME, binding.edtName.trimmedText())
                    putExtra(DetailActivity.EXTRA_EMAIL, binding.edtEmail.trimmedText())
                    putExtra(DetailActivity.EXTRA_PHONE, binding.edtPhone.trimmedText())
                    putExtra(DetailActivity.EXTRA_STUDENT_ID, binding.edtStudentId.trimmedText())
                    putExtra(DetailActivity.EXTRA_CLASS, binding.edtClass.trimmedText())
                }
                startActivity(intent)
            }
        }
    }

    private fun validateName(): Boolean {
        val name = binding.edtName.trimmedText()
        val valid = name.length >= 2 && Regex("^[\\p{L} ]+$").matches(name)
        binding.tilName.error = if (valid) null else getString(R.string.err_name)
        return valid
    }

    private fun validateEmail(): Boolean {
        val valid = Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.trimmedText()).matches()
        binding.tilEmail.error = if (valid) null else getString(R.string.err_email)
        return valid
    }

    private fun validatePhone(): Boolean {
        val valid = Regex("^0\\d{9}$").matches(binding.edtPhone.trimmedText())
        binding.tilPhone.error = if (valid) null else getString(R.string.err_phone)
        return valid
    }

    private fun validateStudentId(): Boolean {
        val valid = Regex("^\\d{10,13}$").matches(binding.edtStudentId.trimmedText())
        binding.tilStudentId.error = if (valid) null else getString(R.string.err_student_id)
        return valid
    }

    private fun validateClass(): Boolean {
        val valid = Regex("^[A-Za-z0-9]{4,15}$").matches(binding.edtClass.trimmedText())
        binding.tilClass.error = if (valid) null else getString(R.string.err_class)
        return valid
    }
}