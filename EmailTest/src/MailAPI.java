import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Authenticator;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.EmailException;


public class MailAPI {
	
	public static void main(String[] args) throws EmailException, AddressException, MessagingException, IOException {
		String [] tos={"siby.augustine@maventic.com",/*"roshan.jha@maventic.com","shylesh.mv@maventic.com"*/};
		sendmail(tos);
	}

	private static void sendmail(String [] tos) throws EmailException, AddressException, MessagingException, IOException {
		Properties properties = getProperties();
		Session session = getSession(properties);
		MimeMessage message = new MimeMessage(session);
		Address toAddress=new InternetAddress("ajayan.e@maventic.com");
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		
		message.setFrom(toAddress);
		int tosLength=tos.length;
		Address [] toAdds=new Address[tosLength];
		
		message.addRecipients(Message.RecipientType.TO, "shylesh.mv@maventic.com");
		message.setReplyTo(new InternetAddress[]{
				new InternetAddress("siby.augustine@maventic.com")
				});
		
		message.setSubject("Mail through Java Mail API!");
		String html = "Hi, Huhaa You have received this mail from Java mail API!.";

		MimeMultipart multipart=new MimeMultipart();
		String filename = "D:/1457442479247_001.png";
	
		messageBodyPart.setText(html);
		messageBodyPart.attachFile(filename); 
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart);
		
		Transport.send(message);
		System.out.println("Sent message successfully....");
	}

	private static Properties getProperties() {
		Properties properties=new Properties();  
		properties.setProperty("mail.transport.protocol", "smtp");     
		properties.setProperty("mail.host", "smtp.gmail.com");  
		properties.put("mail.smtp.auth", "true");  
		properties.put("mail.smtp.port", "465");  
		properties.put("mail.debug", "true");  
		properties.put("mail.smtp.socketFactory.port", "465");  
		properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
		properties.put("mail.smtp.socketFactory.fallback", "false"); 
		return properties;
	}

	private static Session getSession(Properties properties) {
		return Session.getDefaultInstance(properties,  
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(UserCredentials.getUname(),UserCredentials.getPass());  
			}  
		});
	}

}
