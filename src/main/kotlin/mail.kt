
import java.util.*
import javax.mail.*
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object Mail {
    fun sendMail(to: String, msg: String) {

        val from = "smalllamartin@gmail.com"
        val username = "smalllamartin@gmail.com"
        val password = "retryelse01"

        val host = "smtp.gmail.com"

        val props = Properties()
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        props["mail.smtp.host"] = host
        props["mail.smtp.port"] = "587"

        val session = Session.getInstance(props,
                object : javax.mail.Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(username, password)
                    }
                })

        try {
            val message = MimeMessage(session)

            message.setFrom(InternetAddress(from))
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to))
            message.subject = "42 Match - Code"
            message.setText(msg)
            Transport.send(message)

            println("Sent message successfully....")

        } catch (e: MessagingException) { println(e.message) }

    }
}