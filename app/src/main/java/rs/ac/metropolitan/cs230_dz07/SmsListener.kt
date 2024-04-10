package rs.ac.metropolitan.cs230_dz07

interface SmsListener {
    fun messageReceived(message: String)
}