package com.jaring.jom.util.authentication

import com.google.api.client.json.JsonFactory
import com.google.api.client.http.HttpTransport
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import scala.collection.mutable.ListBuffer
import java.util.Arrays
import java.security.SecureRandom
import com.jaring.jom.util.impl.IOAuthImpl
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import java.io.InputStream
import com.jaring.jom.util.common.PropertyLoaderUtil
import java.io.InputStreamReader
import com.google.api.client.http.GenericUrl
import com.jaring.jom.util.gson.CustomGson
import java.io.BufferedInputStream

object GoogleAuthentication{
	def getUserInfo(json:String) : GoogleUserInfoEntity = {
		val googleObject:GoogleUserInfoEntity = CustomGson.fromGson(json, classOf[GoogleUserInfoEntity])
		return googleObject;
	}
}

class GoogleAuthentication(val callBackURI:String)
	extends IOAuthImpl{
  
	private val clientSecretFile = "client_secrets.json"
	private val user = "user"
	private val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
	private val jsonFactory = JacksonFactory.getDefaultInstance();
	private val userInfoURL = "https://www.googleapis.com/oauth2/v1/userinfo";
	private val scopes:java.util.List[String] = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile",
		      "https://www.googleapis.com/auth/userinfo.email");
	
	private val clientSecrets = loadClientSecrets();
	
	private val googleFlow = new GoogleAuthorizationCodeFlow.Builder(
	        httpTransport, jsonFactory, clientSecrets, scopes)
	    //.setCredentialDataStore(DATASTORE_FACTORY)
	    .build(); 

	def isConnectable():Boolean = {
		if(googleFlow == null || httpTransport == null)
			return false;
		return true;
	}
	
	override def generateStateToken():String = {
		val sr1 = new SecureRandom();
		return EnumAuthentication.GOOGLE_TOKEN .toString() + sr1.nextInt();
	}

	override def getGeneratedOAuthURL(stateToken:String):Option[String] = {
		if (isConnectable() == false || stateToken==null || stateToken.isEmpty())
			return None;

		val url = googleFlow.newAuthorizationUrl();
		
		return Option.apply(url.setRedirectUri(callBackURI).setState(stateToken).build());
	}

	def loadClientSecrets():GoogleClientSecrets = {
	  var clientSecrets:GoogleClientSecrets = null;
	  try{
		  val inputStream:InputStream = PropertyLoaderUtil.fileLoader(clientSecretFile)
		  clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(inputStream));
	  }catch{
	    case error:Exception => throw new IllegalAccessException("Unable to detect "+clientSecretFile);
		  			error.printStackTrace()
	  }
	
	  clientSecrets;
	}
	
	override def getUserInfoJson(authCode:String):Option[String] = {
		return getUserInfoJson(authCode, user);
	}
	
	override def getUserInfoJson(authCode:String, userId:String) : Option[String] = {
		
		if(isConnectable() == false || authCode == null || authCode.isEmpty())
			return None;
		
		val response = googleFlow.newTokenRequest(authCode).setRedirectUri(callBackURI).execute();
		val credential = googleFlow.createAndStoreCredential(response, null);
		val requestFactory = httpTransport.createRequestFactory(credential);
		val url = new GenericUrl(userInfoURL);
		val request = requestFactory.buildGetRequest(url);
		request.getHeaders().setContentType("application/json");
		val jsonIdentity = request.execute().parseAsString();

		Option.apply(jsonIdentity);
	}
	
	override def convertJsonToObj(json:String):Option[GoogleUserInfoEntity] = {
		if(json == null || json.isEmpty())
			return None
		val entity:GoogleUserInfoEntity = new GoogleUserInfoEntity()
		val googleInfoEntity =  CustomGson.fromGson(json, entity.getClass())
		return Option.apply(googleInfoEntity);
	}
	

}