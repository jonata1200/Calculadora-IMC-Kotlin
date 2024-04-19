package com.example.calculadoraimckotlin


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadoraimckotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btCalcularIMC.setOnClickListener{

            val peso = binding.EditTextPeso.text.toString().replace(",", ".")
            val altura = binding.EditTextAltura.text.toString().replace(",", ".")
            val resultado = binding.textResultado

            if (peso.isEmpty()){

                binding.EditTextPeso.error = "Informe seu Peso!"

            }else if (altura.isEmpty()){

                binding.EditTextAltura.error = "Informe sua Altura!"

            }else{

                calcularIMC(peso, altura, resultado)

            }
        }
    }


    private fun calcularIMC(peso: String, altura: String, resultado: TextView){

        val imc = peso.toDouble() / (altura.toDouble() * altura.toDouble())
        val decimal = String.format("%.2f",imc)


        val mensagem = when {

            imc <= 18.5 -> "Desnutrido"
            imc <= 24.9 -> "Peso Normal"
            imc <= 29.9 -> "Sobrepeso"
            imc <= 34.9 -> "Obesidade (Grau 1)"
            imc <= 39.9 -> "Obesidade (Grau 2)"
            else -> "Obesidade Mórbida (Grau 3)"

        }
        resultado.setText("O seu IMC é de: $decimal \n $mensagem")

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.ic_limpar ->{

                binding.EditTextPeso.setText("")
                binding.EditTextAltura.setText("")
                binding.textResultado.setText("")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}