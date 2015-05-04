package test

import org.scalatest.FlatSpec
import com.jaring.jom.util.img.ThumbnailCreator
import java.util.Properties
import java.io.{File,FileReader}
import org.junit.Assert
import com.jaring.jom.util.common.PropertyLoaderUtil

class TestImage extends FlatSpec{
  "Image" should "have been created" in{
    val prop = (PropertyLoaderUtil.propertyLoader("image.properties")).get
    val locFolder = prop.getProperty("image.locFolder")
    val oldImg = prop.getProperty("image.toConvert")
    val newImgName = "rename.JPG"
	ThumbnailCreator.resize(locFolder+oldImg, locFolder+newImgName, 100, "jpg");
    val newImg = new File(locFolder+newImgName)
    Assert.assertTrue(newImg.exists())
    newImg.delete()
  }

}