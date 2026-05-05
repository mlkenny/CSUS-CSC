//Name: Michael Kenny
//Title: CSC Assignment #4 (CSC20 - Email and Document Super/Sub Classes)
//Date: 2/28/2021
//Professor: Faroughi

import java.util.*;

class Driver {
   
   public static void main(String [] args) {
      Email e1 = new Email("Hello everyone, we will have a meeting tomorrow at 10", "Gita Faroughui", "Alex", "Meeting", "");
      e1.send();
      
      System.out.println(e1);
      System.out.println("\n\n");
      
      boolean b = e1.contains("tomorrow");
      if(b) {
        System.out.println("The word tomorrow was found in the email."); 
      
      }else {
         System.out.println("The word tomorrow was not found in the email.");
      
      }
      
      System.out.println("\nThe content of this email is: " + e1.getText());
      System.out.println();
      
      e1.modifyContent("bye");
      
      e1.setRecipient("Jose@yahoo.com, Mary@gmail.com");
      System.out.println();
      
      Email forward = e1.forward("Alex", "Gita", "Maria");
      
      System.out.println("forwarded message:\n" + forward);
      System.out.println();
      
      System.out.println("The number of the letters in the email is: " + e1.getContentLength());
   
   }

}

class Document {
   
   String content = "";
   
   public Document() {
   
   }
   
   public Document(String text) {
      content = text;
   
   }
   
   public String getContent() {
      return content;
   
   }
   
   public void setContent(String newContent) {
      content = newContent;
   
   }
   
   public String toString() {
      return "Document has content of: " + content;
   
   }
   
   public int getContentLength() {
      return content.length();
   
   }
   
   public boolean contains(String keyword) {
      String temp_word = "";
      Scanner sc = new Scanner(content);
      while(sc.hasNext()) {
         temp_word = sc.next();
         if(temp_word.equals(keyword)) {
            return true;
      
         }
      
      }
      return false;
   
   }
   
   public boolean equals(Document other) {
      return this.content.equalsIgnoreCase(other.content);
   
   }

}

class Email extends Document {
   String text = "";
   String sender = "";
   String recipient = "";
   Date date;
   String subject = "";
   String cc = "";
   boolean isSent = false;
   
   public Email(String text, String sender, String recipient, String subject, String cc) {
      date = new Date();
      this.text = text;
      super.setContent(text);
      this.sender = sender;
      this.recipient = recipient;
      this.subject = subject;
      this.cc = cc;
   
   }
   
   public void send() {
      isSent = true;
   
   }
   
   public boolean getSent() {
      return isSent;
   
   }
   
   public String getText() {
      return text;
   
   }
   
   public String getSender() {
      return sender;
   
   }
   
   public String getRecipient() {
      return recipient;
   
   }
   
   public String getSubject() {
      return subject;
   
   }
   
   public String getCC() {
      return cc;
   
   }
   
   public Date date() {
      return date;
   
   }
   
   public void setSender(String s) {
      if(isSent) {
         System.out.println("This email has already been sent.");
      
      }else {
         sender = s;
      
      }
   
   }
   
   public void setRecipient(String r) {
      if(isSent) {
         System.out.println("This email has already been sent.");
      
      }else {
         recipient = r;
      
      }
   
   }
   
   public void setCC(String c) {
      if(isSent) {
         System.out.println("This email has already been sent.");
      
      }else {
         cc = c;
      
      }
   
   }
   
   public String toString() {
      return super.toString() + " The Sender is: " + sender + " The recipient is: " + recipient + " The subject is: " + subject + " The date is: " + date;
   
   }
   
   public void modifyContent(String s) {
      if(isSent) {
         System.out.println("This email has already been sent.");
      
      }else {
         super.setContent(s);
      
      }
   
   }
   
   public Email forward(String rec, String sender, String cc) {
      Email f = new Email(this.getText(), sender, recipient, this.subject, cc);
      f.date = new Date();
      f.isSent = true;
      return f;
   
   }
   
}