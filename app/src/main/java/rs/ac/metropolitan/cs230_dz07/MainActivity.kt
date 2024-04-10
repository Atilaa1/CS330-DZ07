package rs.ac.metropolitan.cs230_dz07

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity  : AppCompatActivity(),SmsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val imageButton1 = findViewById<ImageButton>(R.id.imageButton1)
        val imageButton2 = findViewById<ImageButton>(R.id.imageButton2)

        imageButton1.setOnClickListener {
            val message = editText.text.toString()
            val phoneNumber = "1234567890"

            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)

            Toast.makeText(this, "SMS poslat", Toast.LENGTH_SHORT).show()
        }

        imageButton2.setOnClickListener {
            val message = editText.text.toString()
            val email = "atila.kujundzic.5000@metropolitan.ac.rs"
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                putExtra(Intent.EXTRA_SUBJECT, "Naslov e-maila")
                putExtra(Intent.EXTRA_TEXT, message)
            }

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }

            Toast.makeText(this, "E-mail poslat", Toast.LENGTH_SHORT).show()
        }
    }

    override fun messageReceived(message: String) {
        val editText = findViewById<EditText>(R.id.editText)
        editText.setText(message)
    }
}