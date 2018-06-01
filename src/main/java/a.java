import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

public class a {
    public static void main(String[] args) {
        Email email = EmailBuilder.startingBlank()
                .from("Michel Baker", "tontonbachir@gmqil.com")
                .to("mom", "tontonbachir@gmqil.com")
                .to("dad", "smalllamartin@gmqil.com")
                .withSubject("My Bakery is finally open!")
                .withPlainText("Mom, Dad. We did the opening ceremony of our bakery!!!")
                .buildEmail();

        MailerBuilder
                .withSMTPServer("smtp.gmail.com", 465, "tontonbachir", "Retryelse01")
                .withTransportStrategy(TransportStrategy.SMTPS)
                .buildMailer()
                .sendMail(email);
    }
}
