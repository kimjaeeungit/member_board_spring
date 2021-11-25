package com.kimjaeeun.util;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kimjaeeun.dao.MemberDao;
import com.kimjaeeun.vo.Member;

@WebServlet("/mail")
public class MailSender extends HttpServlet{
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {   
   MemberDao dao= new MemberDao();
   MailAccount account = new MailAccount(); 
   String user = "dmjfhe12@gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
   String password = "Wodms+wodud12@";   // 패스워드
   String receiver = req.getParameter("email");
   String title = "도그몰 임시비밀번호 발급";
   String codeori=req.getParameter("code_check");
   String code = String.valueOf(codeori);//임시비번 받아오기
   String content = "임시비밀번호:" + code; 
   
   
    // SMTP 서버 정보를 설정한다.
    Properties prop = new Properties();
    prop.put("mail.smtp.host", "smtp.gmail.com"); 
    prop.put("mail.smtp.port", 465); 
    prop.put("mail.smtp.auth", "true"); 
    prop.put("mail.smtp.ssl.enable", "true"); 
    prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    prop.put("mail.smtp.ssl.protocols","TLSv1.2");
    
    Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);
        }
    });

    try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));

        //수신자메일주소
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver)); 

        // Subject
        message.setSubject(title); //메일 제목을 입력

        // Text
        message.setText(content);    //메일 내용을 입력

        // send the message
        Transport.send(message); ////전송
        System.out.println("message sent successfully...");
    } catch (AddressException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (MessagingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
    Member member=dao.findById(receiver);
    Member member2 = new Member(member.getId(),code,receiver,member.getName());
	dao.modify(member2);
    resp.sendRedirect("index.html");
   }
 
   
}