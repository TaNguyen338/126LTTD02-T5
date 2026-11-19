package com.example.studentmvvm.network

import com.example.studentmvvm.model.Student

data class StudentDto(
    val id: Int,
    val name: String,
    val email: String
)

fun StudentDto.toEntity() = Student(
    fullName = name,
    studentCode = "CLOUD%04d".format(id),
    email = email,
    gpa = 3.0
)