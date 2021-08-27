package com.cdpete.storagedatatesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.cdpete.storagedatatesting.data.DataStatic
import com.cdpete.storagedatatesting.data.SingletonStatic
import com.cdpete.storagedatatesting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var savedStateHandleData: String? = null
    private var declaredData: String? = null

    companion object {
        private const val STATE_KEY = "state_key"
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

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d("saveInstanceState", STATE_KEY)
        outState.putString(STATE_KEY, savedStateHandleData)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedStateHandleData = savedInstanceState.getString(STATE_KEY)
    }

    private fun onSaveButtonClicked() {
        var dummyData = "CDMOCHI"
        savedStateHandleData = dummyData
        declaredData = dummyData
        DataStatic.name = dummyData
        SingletonStatic.name = dummyData
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