package test

import com.jaring.jom.util.email.EmailUtility
import org.junit.Test
import org.junit.Assert._
import org.scalatest.junit.AssertionsForJUnit
import org.scalatest.FlatSpec

class TestEmail extends FlatSpec{
  "Email" should "have been send" in{
		val emailUtil = new EmailUtility
		emailUtil.sendEmail("Hello")
	}
  
  "Email" should "be able to send multiple" in{
		val emailUtil = new EmailUtility
		emailUtil.sendEmail(Option("jomjaring@gmail.com,yoong.han@timwe.com"), "Test", "Test message")
	}
  
//  "Email" should "be able to send attachment" in{
//    val emailUtil = new EmailUtility
//    val attachment = emailUtil.createMultipart("C:/temp/excel_54a4a4341d86e330ad740088.xlsx")
//    emailUtil.sendEmail(Option.empty, Option.empty, "Test Subject", "From Han", Some(attachment))
//  }
}