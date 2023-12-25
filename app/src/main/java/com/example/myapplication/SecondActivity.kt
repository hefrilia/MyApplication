package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var textViewName: TextView
    private lateinit var textViewSelectedUser: TextView

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                binding.selectedUser.text =  it.data?.getStringExtra(ThirdScreen.THIRD_INTENT_KEY)
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iconBack.setOnClickListener {
            finish()
        }

        textViewName = findViewById(R.id.username)
        textViewSelectedUser = findViewById(R.id.selected_user)

        val userName = intent.getStringExtra("EXTRA_USERNAME")
        val selectedUser = intent.getStringExtra("EXTRA_SELECTED_USER")

        textViewName.text = "$userName"
        textViewSelectedUser.text = "$selectedUser"

        binding.buttonChoose.setOnClickListener {
            val intentDetail = Intent(this, ThirdScreen::class.java)
            getResult.launch(intentDetail)
        }

    }

//    fun onChooseButtonClicked(view: android.view.View){
//        val intent = Intent(this, ThirdScreen::class.java)
//        startActivity(intent)
//    }

}