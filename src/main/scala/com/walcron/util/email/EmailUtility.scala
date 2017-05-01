package com.walcron.util.email

import javax.mail.Authenticator
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.internet.MimeMessage
import javax.mail.internet.InternetAddress
import com.walcron.util.common.PropertyLoaderUtil
import com.walcron.util.bean.EmailPropBean
import java.util.Properties
import java.util.Date
import javax.mail.Message
import javax.mail.Transport
import javax.mail.internet.MimeBodyPart
import javax.activation.FileDataSource
import javax.activation.DataHandler
import javax.mail.internet.MimeMultipart

class EmailUtility {

	val emailPropBean = new EmailPropBean();
	val propertyFileName = "email.properties"
	val propertyMap = Array("to.user","from.user","subject","mail.user","mail.password")

	val property:Properties = PropertyLoaderUtil.propertyLoader(propertyFileName).get

	PropertyLoaderUtil.map(property, propertyMap, false,
	    {(i,value)=>i match{
		    case 0 => emailPropBean.setToUser(value)
		    case 1 => emailPropBean.setFromUser(value)
		    case 2 => emailPropBean.setSubject(value)
		    case 3 => emailPropBean.setUserName(value)
		    case 4 => emailPropBean.setPassword(value)
	    }})

	emailPropBean.setProperty(property)

	def setCustomEmailProperty(emailPropBean:EmailPropBean):Unit = {
		emailPropBean.setProperty(property)
	}

	def sendEmail(message:String){
	  val subjectWithDate = String.format("%s [%s]", emailPropBean.getSubject(),
	      emailPropBean.getDateFormat().format(new Date()));
	  sendEmail(subjectWithDate,message);
	}

	def sendEmail(subject:String, message:String){
	  sendEmail(Option.empty,subject,message);
	}

	def createMultipart(attachmentSource:String, fileName:Option[String])={
	  val multipart = new MimeMultipart()
	  val messageBodyPart = new MimeBodyPart()

	  val source = new FileDataSource(attachmentSource)
	  messageBodyPart.setDataHandler(new DataHandler(source))
    if(fileName.isDefined) {
      messageBodyPart.setFileName(fileName.get)
    }
    else {
      messageBodyPart.setFileName(attachmentSource)
    }

	  multipart.addBodyPart(messageBodyPart);

	  multipart
	}

	def sendEmail(bccAdds:Option[String], subject:String, message:String){
	  sendEmail(Option.empty, bccAdds, subject, message, Option.empty, false)
	}

	def sendEmail(toAdds:Option[String], bccAdds:Option[String], subject:String, message:String, multipart:Option[MimeMultipart]){
	  sendEmail(Option.empty, bccAdds, subject, message, multipart, false)
	}

	def sendEmailAsHTML(toAdds:Option[String], bccAdds:Option[String], subject:String, message:String){
	  sendEmail(Option.empty, bccAdds, subject, message, Option.empty, true)
	}

	def sendEmail(toAdds:Option[String], bccAdds:Option[String], subject:String, message:String, multipart:Option[MimeMultipart], isHTML:Boolean){
		if(emailPropBean.getProperty == null){
			println("No authentication admitted")
		}
		try{
			val auth:Authenticator = new Authenticator() {
				override def getPasswordAuthentication():PasswordAuthentication =
					return new PasswordAuthentication(
					    emailPropBean.getUserName(), emailPropBean.getPassword())
			};

			val session:Session = Session.getInstance(emailPropBean.getProperty(),auth);

			//Create a default MimeMessage object.
			val mimeMessage:MimeMessage = new MimeMessage(session);

			// Set From: header field of the header.
			mimeMessage.setFrom(new InternetAddress(emailPropBean.getFromUser()));

			// Set To: header field of the header.
			mimeMessage.addRecipient(Message.RecipientType.TO,
	                              new InternetAddress(
	                                  if(toAdds.isDefined) toAdds.get
	                                  else emailPropBean.getToUser()
	                                  ));

			// Set bcc: header field to recipient
			if(bccAdds.isDefined){
			  val bccVal = bccAdds.get
			  if(bccVal.indexOf(",") > -1)
			    mimeMessage.addRecipients(Message.RecipientType.BCC, bccVal);
			  else
			    mimeMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(bccVal));
			}

			//Set Subject: header field
			mimeMessage.setSubject(subject);

			// Now set the actual message
			if(isHTML){
			  val messageBodyPart = new MimeBodyPart()
			  val defMultipart = new MimeMultipart()
			  messageBodyPart.setContent(message, """text/html; charset=utf-8""");
			  defMultipart.addBodyPart(messageBodyPart)
			  mimeMessage.setContent(defMultipart)


			}else if(multipart.isDefined){
			  val messageBodyPart = new MimeBodyPart()
			  val defMultipart = multipart.get
			  messageBodyPart.setText(message)
			  defMultipart.addBodyPart(messageBodyPart)
			  mimeMessage.setContent(defMultipart)
			}else{
			  mimeMessage.setText(message);
			}

			//Send message
			Transport.send(mimeMessage);

		}catch{
		  case e:Exception => e.printStackTrace();
		  //can't do much just give it as error.
		}
	}

}
