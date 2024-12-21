package com.farhan.sharedpreferencedemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.farhan.sharedpreferencedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Declare constant / the key where our data will be saved
    private final val SAVED= "saved"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.readDataButton.isEnabled = false //Disable the read button

            binding.saveDataButton.setOnClickListener {
                //Jika data diisi, saya akan saved data tersebut
                if(binding.saveDataEditText.text.toString() != ""){
                    savePreference(SAVED, binding.saveDataEditText.text.toString())
                }
                binding.readDataButton.isEnabled = true //Enable the read button
        }
            binding.readDataButton.setOnClickListener {
                val savedData = loadPreference()
                binding.readDataEditText.setText(savedData)
            }
        }
    private fun savePreference(key:String, value:String){
        //Retrieve the shared preference with mode private, only accessible in this app
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        //Enter the edit mode of shared preference
        val editor = sharedPreferences.edit()
        //Write the value "value" in key "key"
        //Nama: Farhan
        //Hobi: Melancong
        //Alamat: Bandung
        editor.putString(key, value)
        //save the data
        editor.commit()
    }
    private fun loadPreference():String{
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        //Look for data saved with "SAVED" file name, if not found return ""
        val savedData = sharedPreferences.getString(SAVED, "")
        return savedData!!
    }
}
