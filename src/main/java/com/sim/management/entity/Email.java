package com.sim.management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Email {
 private  String[] toEmail;
  private String subject;
  private String message;
  private String emailFrom;
  private String emailContent;
  private String contentType;

}
