package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var editTextName : EditText
    private lateinit var editTextPalindrome : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editTextName = findViewById(R.id.name_edit_text)
        editTextPalindrome = findViewById(R.id.palindrome_edit_text)
    }

    fun onCheckButtonClicked(view: View){
        val name = editTextName.text.toString()
        val palindrome = editTextPalindrome.text.toString()

        val isPalindrome = isPalindrome(palindrome)

        val resultMessage =
            if (isPalindrome)"is palindrome"
        else "not palindrome"

        showDialog("Result", resultMessage)
    }

    fun onNextButtonClicked(view: View){
        val intent = Intent(this, SecondActivity::class.java)

        val name = editTextName.text.toString()
        val selectedUserName = "Selected User Name"

        intent.putExtra("EXTRA_USERNAME", name)
        intent.putExtra("EXTRA_SELECTED_USER", selectedUserName)

        startActivity(intent)
    }

    fun isPalindrome(palindrome: String): Boolean{
        val cleanSentence = palindrome.replace("[^a-zA-Z0-9]".toRegex(), "").toLowerCase()
        return cleanSentence == cleanSentence.reversed()
    }

    fun showDialog(title: String, message: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("YES") {_, _ ->}
            .show()
    }
}