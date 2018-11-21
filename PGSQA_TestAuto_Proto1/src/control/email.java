package control;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class email {
	public static void send_email() {
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
	    props.setProperty("mail.smtp.host", "smtp.gmail.com");
	    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	    props.setProperty("mail.smtp.socketFactory.fallback", "false");
	    props.setProperty("mail.smtp.port", "465");
	    props.setProperty("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.debug", "true");
	    props.put("mail.store.protocol", "pop3");
	    props.put("mail.transport.protocol", "smtp");
	    final String username = "kykriske22@gmail.com";
	    final String password = ""; //
	    //https://myaccount.google.com/lesssecureapps
	    try{
	        Session session = Session.getDefaultInstance(props, 
	                             new Authenticator(){
	                                protected PasswordAuthentication getPasswordAuthentication() {
	                                   return new PasswordAuthentication(username, password);
	                                }});
	       // -- Create a new message --
	        Message msg = new MimeMessage(session);

	      // -- Set the FROM and TO fields --
	        msg.setFrom(new InternetAddress("kykriske22@gmail.com"));
	        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("kykriske22@gmail.com",false));
	        msg.setSubject("[AVAS]Test Report");
	        msg.setText("Test 1: NG\n"
	        		+ "Test 2: OK\n"
	        		+ "Test 3: OK\n\n"
	        		+ "Total: 2 OK 1 NG");
	        msg.setSentDate(new Date());
	        Transport.send(msg);
	        System.out.println("Message sent.");
	    }catch (MessagingException e){ System.out.println("e-mail exception:" + e);}
	}
}
