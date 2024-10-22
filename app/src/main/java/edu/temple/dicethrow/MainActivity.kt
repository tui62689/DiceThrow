package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*findViewById<Button>(R.id.rollDiceButton).setOnClickListener{
            (supportFragmentManager.findFragmentById(R.id.dieContainer) as DieFragment).throwDie()
        }*/

        val currentFragment = supportFragmentManager.findFragmentById(R.id.dieContainer)

        if (currentFragment !is DieFragment) {
            val fragment = DieFragment.newInstance(10)
            supportFragmentManager.beginTransaction().add(R.id.dieContainer, fragment).commit()
        }
    }
}