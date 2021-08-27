package com.cdpete.storagedatatesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import com.cdpete.storagedatatesting.data.DataStatic
import com.cdpete.storagedatatesting.data.SingletonStatic
import com.cdpete.storagedatatesting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var savedStateHandleData: String? = null
    private var declaredData: String? = null

    companion object {
        private const val KEY_NAME = "key_name"
    }

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding) {
            btSave.setOnClickListener { onSaveButtonClicked() }
            btShow.setOnClickListener { onShowButtonClicked() }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        this.savedStateHandleData = savedInstanceState.getString(KEY_NAME)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_NAME, savedStateHandleData)
    }

    private fun onSaveButtonClicked() {
        var dummyData = "CDMOCHI"
        DataStatic.name = dummyData
        SingletonStatic.name = dummyData
        savedStateHandleData = dummyData
        declaredData = dummyData
    }

    private fun onShowButtonClicked() {
        val message = """
            declaredData: $declaredData
            savedStateHandleData: $savedStateHandleData
            singletonStatic: ${SingletonStatic.name}
            staticField: ${DataStatic.name}
        """.trimIndent()
        val alertDialog =
            AlertDialog.Builder(this)
                .setTitle("Result")
                .setMessage(message)
                .setCancelable(true)
                .create()
        alertDialog.show()
    }
}