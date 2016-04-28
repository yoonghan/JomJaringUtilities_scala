package com.walcron.util.authentication

class GoogleUserInfoEntity(
  var id:String = null,
  var email:String = null,
  var verified_email:Boolean = false,
  var name:String = null,
  var link:String = null,
  var family_name:String = null,
  var given_name:String = null,
  var picture:String = null){

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

	def isVerified_email() ={
		verified_email;
	}

	def setVerified_email(verified_email:Boolean) {
		this.verified_email = verified_email;
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

	def getFamily_name() = {
		family_name;
	}

	def setFamily_name(family_name:String) {
	  if(family_name.length() > 300)
		this.family_name = family_name.substring(0, 300)
	  else
		this.family_name = family_name;
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

	def getGiven_name() ={
		given_name;
	}

	def setGiven_name(given_name:String) = {
	  if(given_name.length() > 300)
		this.given_name = given_name.substring(0, 300);
	  else
		this.given_name = given_name;
	}

	def getPicture() = {
	  picture;
	}

	def setPicture(picture:String) {
	  if(picture.length() > 1000)
	    this.picture = picture.substring(0, 1000)
	  else
		this.picture = picture;
	}
}