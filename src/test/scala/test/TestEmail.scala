package test

import com.walcron.util.email.EmailUtility
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
		emailUtil.sendEmail(Option("walcoorperation@gmail.com"), "Test 2", "Test message")
	}

  "Email" should "be able to send as Html" in{
		val emailUtil = new EmailUtility
		emailUtil.sendEmailAsHTML(Option.empty, Option("walcoorperation@gmail.com"), "Test", 
"""<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reminder</title>

    <style type="text/css">
		#outlook a{
			padding:0;
		}
		body{
			width:100% !important;
		}
		.ReadMsgBody{
			width:100%;
		}
		.ExternalClass{
			width:100%;
		}
		body{
			-webkit-text-size-adjust:none;
		}
		body{
			margin:0;
			padding:0;
		}
		img{
			border:0;
			height:auto;
			line-height:100%;
			outline:none;
			text-decoration:none;
		}
		table td{
			border-collapse:collapse;
		}
		#bodyTable{
			height:100% !important;
			margin:0;
			padding:0;
			width:100% !important;
		}

	@tab Page
	@section heading 2
	@tip Set the styling for all second-level headings in your emails.
	@style heading 2
	*/
		h2{
			/*@editable*/color:#404040;
			display:block;
			/*@editable*/font-family:Helvetica;
			/*@editable*/font-size:20px;
			/*@editable*/font-style:normal;
			/*@editable*/font-weight:bold;
			/*@editable*/line-height:100%;
			/*@editable*/letter-spacing:normal;
			margin-top:0;
			margin-right:0;
			margin-bottom:10px;
			margin-left:0;
			/*@editable*/text-align:left;
		}
	/*
	@tab Page
	@section heading 3
	@tip Set the styling for all third-level headings in your emails.
	@style heading 3
	*/
		h3{
			/*@editable*/color:#606060;
			display:block;
			/*@editable*/font-family:Helvetica;
			/*@editable*/font-size:16px;
			/*@editable*/font-style:normal;
			/*@editable*/font-weight:bold;
			/*@editable*/line-height:100%;
			/*@editable*/letter-spacing:normal;
			margin-top:0;
			margin-right:0;
			margin-bottom:10px;
			margin-left:0;
			/*@editable*/text-align:left;
		}
	/*
	@tab Page
	@section heading 4
	@tip Set the styling for all fourth-level headings in your emails. These should be the smallest of your headings.
	@style heading 4
	*/
		h4{
			/*@editable*/color:#808080;
			display:block;
			/*@editable*/font-family:Helvetica;
			/*@editable*/font-size:12px;
			/*@editable*/font-style:normal;
			/*@editable*/font-weight:bold;
			/*@editable*/line-height:100%;
			/*@editable*/letter-spacing:normal;
			margin-top:0;
			margin-right:0;
			margin-bottom:10px;
			margin-left:0;
			/*@editable*/text-align:left;
		}
	/*
	@tab Header
	@section preheader style
	@tip Set the background color and bottom border for your email's preheader area.
	@theme page
	*/
		#templatePreheader{
			/*@editable*/background-color:#000000;
			/*@editable*/border-bottom:0;
		}
	/*
	@tab Header
	@section preheader text
	@tip Set the styling for your email's preheader text. Choose a size and color that is easy to read.
	*/
		.preheaderContent{
			/*@editable*/color:#FFFFFF;
			/*@editable*/font-family:Helvetica;
			/*@editable*/font-size:10px;
			/*@editable*/line-height:125%;
			/*@editable*/text-align:left;
		}
	/*
	@tab Header
	@section preheader link
	@tip Set the styling for your email's preheader links. Choose a color that helps them stand out from your text.
	*/
		.preheaderContent a:link,.preheaderContent a:visited,.preheaderContent a .yshortcuts {
			/*@editable*/color:#E60101;
			/*@editable*/font-weight:normal;
			/*@editable*/text-decoration:underline;
		}
	/*
	@tab Header
	@section header style
	@tip Set the background color and border for your email's header area.
	@theme header
	*/
		#templateHeader{
			/*@editable*/background-color:#F4F4F4;
			/*@editable*/border-top:0;
			/*@editable*/border-bottom:0;
		}
	/*
	@tab Header
	@section header text
	@tip Set the styling for your email's header text. Choose a size and color that is easy to read.
	*/
		.headerContent{
			/*@editable*/color:#505050;
			/*@editable*/font-family:Helvetica;
			/*@editable*/font-size:20px;
			/*@editable*/font-weight:bold;
			/*@editable*/line-height:100%;
			/*@editable*/padding-top:0;
			/*@editable*/padding-right:0;
			/*@editable*/padding-bottom:0;
			/*@editable*/padding-left:0;
			/*@editable*/text-align:left;
			/*@editable*/vertical-align:middle;
		}
	/*
	@tab Header
	@section header link
	@tip Set the styling for your email's header links. Choose a color that helps them stand out from your text.
	*/
		.headerContent a:link,.headerContent a:visited,.headerContent a .yshortcuts {
			/*@editable*/color:#EB4102;
			/*@editable*/font-weight:normal;
			/*@editable*/text-decoration:underline;
		}
		#headerImage{
			height:auto;
			max-width:600px !important;
		}
	/*
	@tab Body
	@section body style
	@tip Set the background color for your email's body area.
	*/
		.templateBody{
			/*@editable*/background-color:#F4F4F4;
		}
	/*
	@tab Body
	@section calendar month style
	@tip Set the styling for your calendar's month area.
	*/
		.calendarTitleBar{

		}
	/*
	@tab Body
	@section calendar month style
	@tip Set the styling for your calendar's month area.
	*/
		.calendarTitleBarContent,.calendarTitleBarContent a:link,.calendarTitleBarContent a:visited,.calendarTitleBarContent a .yshortcuts{
			color:#FFFFFF;font-family:Helvetica;font-size:45px;font-style:normal;font-weight:bold;line-height:100%;letter-spacing:-1px;text-align:center;text-decoration:none;background-color:#D00000;border-bottom:1px solid #B00000;
		}
	/*
	@tab Body
	@section calendar day style
	@tip Set the styling for your calendar's day area.
	*/
		#calendarContentBlock{
			/*@tab Body
@section calendar day style
@tip Set the styling for your calendar's day area.*/-moz-border-radius:0 0 10px 10px;
			-webkit-border-radius:0 0 10px 10px;
			/*@editable*/background-color:#FFFFFF;
			/*@editable*/border:1px solid #E8E8E8;
			border-radius:0 0 10px 10px;
		}
	/*
	@tab Body
	@section calendar day style
	@tip Set the styling for your calendar's day area.
	*/
		.calendarContent,.calendarContent a:link,.calendarContent a:visited,.calendarContent a .yshortcuts{
			/*@editable*/color:#303030;
			/*@editable*/font-family:Helvetica;
			/*@editable*/font-size:175px;
			/*@editable*/font-style:normal;
			/*@editable*/font-weight:bold;
			/*@editable*/line-height:100%;
			/*@editable*/letter-spacing:-5px;
			text-align:center;
			text-decoration:none;
		}
		.calendarTitleBar h1,.calendarTitleBar h2,.calendarTitleBar h3,.calendarTitleBar h4,.calendarContent h1,.calendarContent h2,.calendarContent h3,.calendarContent h4{
			line-height:100% !important;
			margin:0 !important;
		}
	/*
	@tab Body
	@section body title bar style
	@tip Set the styling for your email's body title bar area.
	*/
		.contentTitleBar{
			/*@editable*/background-color:#909090;
			/*@editable*/border-bottom:1px solid #707070;
		}
	/*
	@tab Body
	@section body title bar style
	@tip Set the styling for your email's body title bar area.
	*/
		.contentTitleBarContent{
			/*@editable*/color:#FFFFFF;
			/*@editable*/font-family:Helvetica;
			/*@editable*/font-size:20px;
			/*@editable*/font-style:normal;
			/*@editable*/font-weight:bold;
			/*@editable*/line-height:100%;
			/*@editable*/letter-spacing:normal;
			/*@editable*/text-align:left;
		}
	/*
	@tab Body
	@section body text container style
	@tip Set the background color and border for your email's body content block.
	*/
		#bodyContentBlock{
			/*@tab Body
@section body text container style
@tip Set the background color and border for your email's body content block.*/-moz-border-radius:0 0 10px 10px;
			-webkit-border-radius:0 0 10px 10px;
			/*@editable*/background-color:#FFFFFF;
			/*@editable*/border:1px solid #E8E8E8;
			border-radius:0 0 10px 10px;
		}
	/*
	@tab Body
	@section body text
	@tip Set the styling for your email's main content text. Choose a size and color that is easy to read.
	@theme main
	*/
		.bodyContent{
			/*@editable*/color:#505050;
			/*@editable*/font-family:Helvetica;
			/*@editable*/font-size:13px;
			/*@editable*/line-height:150%;
			/*@editable*/text-align:left;
		}
	/*
	@tab Body
	@section body link
	@tip Set the styling for your email's main content links. Choose a color that helps them stand out from your text.
	*/
		.bodyContent a:link,.bodyContent a:visited,.bodyContent a .yshortcuts {
			/*@editable*/color:#D00000;
			/*@editable*/font-weight:normal;
			/*@editable*/text-decoration:underline;
		}
		.contentTitleBar h1,.contentTitleBar h2,.contentTitleBar h3,.contentTitleBar h4{
			line-height:100% !important;
			margin:0 !important;
		}
		.bodyContent img{
			display:inline;
			height:auto;
		}
	/*
	@tab Footer
	@section footer style
	@tip Set the top border for your email's footer area.
	@theme footer
	*/
		#templateFooter{
			/*@editable*/border-top:0;
		}
	/*
	@tab Footer
	@section footer style
	@tip Set the top border for your email's footer area.
	@theme footer
	*/
		body,#bodyTable{
			/*@editable*/background-color:#F4F4F4;
		}
	/*
	@tab Footer
	@section footer text
	@tip Set the styling for your email's footer text. Choose a size and color that is easy to read.
	@theme footer
	*/
		.footerContent{
			/*@editable*/color:#808080;
			/*@editable*/font-family:Helvetica;
			/*@editable*/font-size:10px;
			/*@editable*/line-height:150%;
			/*@editable*/text-align:left;
		}
	/*
	@tab Footer
	@section footer link
	@tip Set the styling for your email's footer links. Choose a color that helps them stand out from your text.
	*/
		.footerContent a:link,.footerContent a:visited,.footerContent a .yshortcuts {
			/*@editable*/color:#606060;
			/*@editable*/font-weight:normal;
			/*@editable*/text-decoration:underline;
		}
		.footerContent img{
			display:inline;
		}
		#monkeyRewards img{
			max-width:190px !important;
		}
</style></head>
    <body leftmargin="0" marginwidth="0" topmargin="0" marginheight="0" offset="0">
    	<center>
        	<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" id="bodyTable">
            	<tr>
                	<td align="center" valign="top">
                        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="templateContainer">
                        	<tr>
                            	<td align="center" valign="top">
                                	<table border="0" cellpadding="40" cellspacing="0" width="100%">
                                    	<tr>
                                        	<td align="center" valign="top" style="background-color:#F4F4F4;">
                                            	<table border="0" cellpadding="30" cellspacing="0" width="500" id="calendarContentBlock">
                                                	<tr>
                                                        <td align="center" valign="top" style="color:#FFFFFF;font-family:Helvetica;font-size:45px;font-style:normal;font-weight:bold;line-height:100%;letter-spacing:-1px;text-align:center;text-decoration:none;background-color:#D00000;border-bottom:1px solid #B00000;">Apr</td>
                                                    </tr>
                                                	<tr>
                                                        <td align="center" valign="top" class="calendarContent" mc:edit="calendar_content">20</td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    	<tr>
                                        	<td align="center" valign="top" style="background-color:#F4F4F4;" style="padding-top:0;">
                                            	<table border="0" cellpadding="20" cellspacing="0" width="500" id="bodyContentBlock">
                                                	<tr>
                                                        <td valign="top" class="contentTitleBar contentTitleBarContent" style="padding-top:10px; padding-bottom:10px;" mc:edit="content_title">Reminder</td>
                                                    </tr>
                                                	<tr>
                                                        <td align="center" valign="top">
                                                            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                                                <tr>
                                                                    <td valign="top" class="bodyContent" style="padding-bottom:20px;" mc:edit="body_content"><h1 style="display:block;margin-top:0;margin-right:0;margin-bottom:10px;margin-left:0;">Remove</h1>
You have an upcoming event with the following details:<br>
<br>
<strong>Event From:</strong> 00:01 AM <br>
<strong>Event Till:</strong> Whole day <br>
<strong>More info:</strong> Test
<br>
<br>
Please be punctual for you upcoming event.<br>
<br>
As always, we from JOM Jaring does not incur any charges to our users for using this scheduling service.<br>
<br>
<br>
<br>
&nbsp;</td>
                                                                </tr>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        	<tr>
                            	<td align="center" valign="top">
                                	<table border="0" cellpadding="40" cellspacing="0" width="100%" id="templateFooter">
                                    	<tr>
                                        	<td align="center" valign="top" style="padding-bottom:40px;">
                                            	<table border="0" cellpadding="0" cellspacing="0" width="600">
                                                    <tr>
                                                    	<td colspan="3" valign="top" class="footerContent" style="padding-bottom:20px;" mc:edit="footer_social"></td>
                                                    </tr>
                                                	<tr>
                                                        <td valign="top" class="footerContent" mc:edit="footer_content"><em>Copyright &copy; 2015 JOM Jaring, All rights reserved.</em><br>
&nbsp;</td>
                                                        <td width="20">
                                                        	<br>
                                                        </td>
                                                        <td valign="top" width="200" class="footerContent"><a href="http://login.jomjaring.com">Login</a></td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </center>
    </body>
</html>
""")
	}

//  "Email" should "be able to send attachment" in{
//    val emailUtil = new EmailUtility
//    val attachment = emailUtil.createMultipart("C:/temp/excel_54a4a4341d86e330ad740088.xlsx")
//    emailUtil.sendEmail(Option.empty, Option.empty, "Test Subject", "From Han", Some(attachment))
//  }
}
