package lt.sporttech.ubalancit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import lt.sporttech.ubalancit.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        removeToolbar()
        setContentView(R.layout.activity_main)
    }

    private fun removeToolbar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
    }
}