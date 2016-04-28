package com.walcron.util.img

import java.awt.image.BufferedImage
import org.imgscalr.Scalr
import java.io.File
import javax.imageio.ImageIO

object ThumbnailCreator {
	def resize(fromPath:String, toPath:String, width:Int, imgType:String) {
	  val buffImage = ImageIO.read(new File(fromPath))
	  ImageIO.write(Scalr.resize(buffImage,width), "jpg", new File(toPath))
	}
}
