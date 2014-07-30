package com.jaring.jom.util.authentication

class FacebookUserInfoEntity(
  var id:String = null,
  var email:String = null,
  var name:String = null,
  var link:String = null,
  var gender:String = null){

	def getId():String = {
	  return id;
	}

	def setId(id:String) {
	  if(id.length() > 1000)
	  	this.id = id.substring(0, 1000)
	  else
		this.id = id;
	}

	def getEmail():String = {
		email;
	}

	def setEmail(email: String) {
	  if(email.length() > 500)
		this.email=email.substring(0, 500)
	  else
		this.email = email;
	}

	def getName() = {
		name
	}

	def setName(name:String) {
	  if(name.length > 1000)
		this.name = name.substring(0, 1000)
	  else
		this.name = name;
	}

	def getLink() ={
		link
	}

	def setLink(link:String) {
	  if(link.length() > 1000)
		this.link = link.substring(0, 1000)
	  else
		this.link = link;
	}

	def getGender() = {
	  gender;
	}

	def setGender(gender:String) {
	  if(gender.length() > 200)
	    this.gender = gender.substring(0, 200)
	  else
		this.gender = gender;
	}
}