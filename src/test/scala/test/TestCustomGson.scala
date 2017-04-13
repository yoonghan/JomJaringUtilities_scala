package test

import com.walcron.bean.SampleGsonBean
import com.walcron.util.bean.FacebookTokenBean
import com.walcron.util.gson.CustomGson
import org.junit.Assert
import org.scalatest.FlatSpec
import java.util.Calendar

class TestCustomGson extends FlatSpec{
	"Modified Gson" should "have correct interchangeable dates" in{
	  
	    val calendar = Calendar.getInstance();
		calendar.set(2014, 12, 11);
		calendar.setTimeInMillis(0);
	  
		val sampleGson:SampleGsonBean = new SampleGsonBean(calendar.getTime());
		val jsonValue:String = CustomGson.toGson(sampleGson);
		
		val converted:SampleGsonBean = CustomGson.fromGson(jsonValue, classOf[SampleGsonBean])
		
		Assert.assertEquals(converted.getSampleDate().getTime(), sampleGson.getSampleDate().getTime());
	}

	"Facebook Gson" should "can be parsecorrectly" in{

		val jsonString= """{"access_token":"ATOKEN","token_type":"bearer","expires_in":5181978}"""
		val authJsonIdentity:FacebookTokenBean = CustomGson.fromGson(jsonString, classOf[FacebookTokenBean])
		Assert.assertEquals("ATOKEN", authJsonIdentity.access_token);
	}
}