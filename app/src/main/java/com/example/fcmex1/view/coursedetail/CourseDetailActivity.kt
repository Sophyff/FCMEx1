package com.example.fcmex1.view.coursedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fcmex1.databinding.ActivityCourseDetailBinding

class CourseDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityCourseDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCourseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val courseId=intent?.extras?.getString("courseId")?:"NA"
        val desc=intent?.extras?.getString("desp")?:"NA"
        val fees=intent?.extras?.getString("fees")?:"0"

        binding.tvCourseDesp.text=desc
        binding.tvCourseFees.text=fees
        binding.tvCourseId.text=courseId
    }
}