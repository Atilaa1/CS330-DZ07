package rs.ac.metropolitan.cs230_dz07

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast

class SMSReceiver : BroadcastReceiver() {
    private var listener: SmsListener? = null
    fun setListener(listener: SmsListener) {
        this.listener = listener
    }

    override fun onReceive(context: Context, intent: Intent) {
        val extras = intent.extras ?: return

        val pdus = extras.get("pdus") as Array<*>
        for (pdu in pdus) {
            val message = SmsMessage.createFromPdu(pdu as ByteArray)
            val senderNumber = message.originatingAddress
            val messageText = message.messageBody
            listener?.messageReceived(messageText)

            Log.i("SmsReceiver", "SMS poruka primljena od $senderNumber: $messageText")
            // Ovde možete dodati dodatnu logiku za obradu primljene SMS poruke
        }
    }
}
