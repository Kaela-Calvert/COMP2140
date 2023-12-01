import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App {

	Session newSession = null;
	MimeMessage mimeMessage = null;
	private String emailReceipients_1;
	private static final String ALPHANUMERIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static int length = 10; // specify the length of the generated string
	private static String randomString;

	App(String recipient) throws AddressException, MessagingException, IOException

	{
		emailReceipients_1 = recipient;
		this.setupServerProperties();
		this.draftEmail();
		this.sendEmail();
	}


	private void sendEmail() throws MessagingException {
		String fromUser = "uwionwheels@gmail.com"; // Enter sender email id
		String fromUserPassword = "kytf haop baku mpei"; // Enter sender gmail password , this will be authenticated by
		String emailHost = "smtp.gmail.com";
		Transport transport = newSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserPassword);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
		System.out.println("Email successfully sent!!!");
	}

	private MimeMessage draftEmail() throws AddressException, MessagingException, IOException {

		randomString = generateRandomAlphanumericString(length);
		String emailSubject = "Veification";
		String emailBody = "Your UWI ON WHEELS verification code is: " + randomString;
		mimeMessage = new MimeMessage(newSession);
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients_1));

		mimeMessage.setSubject(emailSubject);

		MimeBodyPart bodyPart = new MimeBodyPart();

		bodyPart.setContent(emailBody, "text/html;charset=UTF-8");
		MimeMultipart multiPart = new MimeMultipart();
		multiPart.addBodyPart(bodyPart);

		mimeMessage.setContent(multiPart);
		return mimeMessage;
	}

	private void setupServerProperties() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		newSession = Session.getDefaultInstance(properties, null);
	}

	private static String generateRandomAlphanumericString(int length) {
		StringBuilder sb = new StringBuilder(length);
		SecureRandom random = new SecureRandom();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(ALPHANUMERIC_CHARS.length());
			char randomChar = ALPHANUMERIC_CHARS.charAt(randomIndex);
			sb.append(randomChar);
		}

		return sb.toString();
	}

    public static String getRandomString() {
        return randomString;

    }


}


