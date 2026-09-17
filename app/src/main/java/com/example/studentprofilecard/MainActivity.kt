package com.example.studentprofilecard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentprofilecard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Khởi tạo ViewBinding
    private lateinit var binding: ActivityMainBinding

    // Tạo dữ liệu sinh viên ban đầu
    private var currentStudent = Student(
        id = "2415053122338",
        name = "Nguyen Quoc Tan",
        className = "126LTTD02-T5",
        email = "anv@ute.udn.vn",
        gpa = 3.8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Nạp layout XML thông qua ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Gán dữ liệu ban đầu lên giao diện
        bindStudentData(currentStudent)

        // Xử lý sự kiện khi người dùng bấm nút Cập Nhật
        binding.btnUpdateGpa.setOnClickListener {
            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()

            // Kiểm tra tính hợp lệ của dữ liệu (Validate)
            if (newGpa == null || newGpa !in 0.0..4.0) {
                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm GPA không hợp lệ!")
                return@setOnClickListener
            }

            // Cập nhật sinh viên bằng hàm copy()
            currentStudent = currentStudent.copy(gpa = newGpa)
            bindStudentData(currentStudent) // Vẽ lại dữ liệu mới lên Views

            toast("Cập nhật điểm thành công!")
        }
    }

    // Hàm gán dữ liệu lên XML Views
    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            tvStudentId.text = "MSSV: ${student.id} Lớp: ${student.className}"
            tvGpaBadge.text = "${student.gpa} GPA (${student.gpa.toAcademicRanking()})"
            edtNewGpa.setText(student.gpa.toString())
        }
    }
}