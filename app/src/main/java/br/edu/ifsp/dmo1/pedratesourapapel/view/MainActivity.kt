package br.edu.ifsp.dmo1.pedratesourapapel.view

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.pedratesourapapel.R
import br.edu.ifsp.dmo1.pedratesourapapel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isBot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configToolBar()
        configSpinner()
        configListener()
    }

    private fun configListener() {
        binding.buttonStart.setOnClickListener { startGame() }

        binding.buttonPlayBot.setOnClickListener {
            binding.edittextPlayer2.isEnabled = false
            binding.edittextPlayer2.setText("Bot")
            isBot = true
        }
    }

    private fun configSpinner() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.tipos_jogos)
        )
        binding.spinnerButtles.adapter = adapter
    }

    private fun configToolBar() {
        supportActionBar?.hide()
    }

    private fun startGame() {

        val battles: Int = when (binding.spinnerButtles.selectedItemPosition) {
            0 -> 1
            1 -> 3
            else -> 5
        }

        val mIntent = Intent(this, WarActivity::class.java)
        mIntent.putExtra(Constants.KEY_PLAYER_1, binding.edittextPlayer1.text.toString())
        mIntent.putExtra(Constants.KEY_PLAYER_2, binding.edittextPlayer2.text.toString())
        mIntent.putExtra(Constants.KEY_ROUNDS, battles)
        startActivity(mIntent)
    }
}
