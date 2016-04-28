package test

import com.bean.SampleGsonBean
import com.walcron.util.gson.CustomGson
import org.junit.Assert
import org.scalatest.FlatSpec
import java.util.Date
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
}