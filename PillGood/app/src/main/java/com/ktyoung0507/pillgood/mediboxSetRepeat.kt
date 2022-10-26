package com.ktyoung0507.pillgood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ktyoung0507.pillgood.databinding.ActivityMediboxSetRepeatBinding

class mediboxSetRepeat : AppCompatActivity() {
    val binding by lazy { ActivityMediboxSetRepeatBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // ***** intent 경로 설정 ***** //
        // === 1. 나만의 약통 세부 설정 페이지 === //
        val intentBack = Intent(this, mediboxSetDetails::class.java)
        // === 2. 나만의 약통 세부 설정 페이지(값 수정 시) === // → ~값을 받아 넘기는 기능 구현해야함~ ←
        val intentApply = Intent(this, mediboxSetDetails::class.java)


        // ***** 버튼 및 텍스트 클릭 시 링크 설정 ***** //
        // === 1. 상단 바 뒤로가기 버튼 클릭 시 나만의 약통 세부 설정 페이지로 이동 === //
        binding.layoutHeaderBackBtn.setOnClickListener { startActivity(intentBack) }
        // === 2. 설정 저장 텍스트 클릭 시 나만의 약통 세부 설정 페이지로 이동 === // → ~값을 받아 넘기는 기능 구현해야함~ ←
        binding.layoutApplyText.setOnClickListener { startActivity(intentApply) }
    }
}