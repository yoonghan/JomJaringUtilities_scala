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
}