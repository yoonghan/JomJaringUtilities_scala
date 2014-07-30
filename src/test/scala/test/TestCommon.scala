package test

import org.junit.Test

import org.junit.Assert
import com.jaring.jom.util.common.CommonUtility
import com.jaring.jom.util.common.PropertyLoaderUtil
import com.bean.CustomEmailBean
import org.scalatest.FlatSpec
import com.jaring.jom.util.authentication.GoogleAuthentication
import com.jaring.jom.util.authentication.FacebookAuthentication
import com.jaring.jom.util.authentication.EnumAuthentication
//
//import org.junit.runner.RunWith
//import org.scalatest.junit.JUnitRunner
//
//@RunWith(classOf[JUnitRunner])
class TestCommon extends FlatSpec{
	"Common Utilities" should "have default proper dates" in{
		Assert.assertEquals("dd/MM/yyyy hh:mm:ss", CommonUtility.getCommonBeanValue.getDateFormat());
	}
	
	"Email Bean" should "have properties read" in{
		val propMapper = new CustomEmailBean();
		
		try{
			PropertyLoaderUtil.loadProperty("email.properties", propMapper);
		} catch {
			case e:Exception => e.printStackTrace();Assert.fail();
		}
		
		Assert.assertTrue(propMapper.getFromUser().isEmpty() == false);
		Assert.assertTrue(propMapper.getToUser().isEmpty() == false);
	}
	
	"Authentication" should "be able to be created" in{
	  val CALLBACKURL="http://www.google.co.uk"
	    
	  val googleAuth = new GoogleAuthentication(CALLBACKURL)
	  val facebookAuth = new FacebookAuthentication(CALLBACKURL)
	}
}