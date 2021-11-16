package my.model;

import java.util.Date;

public class Notice {
 private int noticeId;
 private String noticeTitle;
 private String userId;
 private Date noticeDate;
 private int noticeCount;
 private String noticeContent;


public Notice(){}
 
public Notice (String userId, String noticeTitle, String noticeContent) {
  super();
  this.userId = userId;
  this.noticeTitle = noticeTitle;
  this.noticeContent = noticeContent;

  
}
public Notice (String userId, String noticeTitle, Date noticeDate, String noticeContent) {
 super();
 this.userId = userId;
 this.noticeTitle = noticeTitle;
 this.noticeDate = noticeDate;
 this.noticeContent = noticeContent;

 
}

public int getNoticeId() {
 return noticeId;
}

public void setNoticeId(int noticeId) {
 this.noticeId = noticeId;
}

public String getNoticeTitle() {
 return noticeTitle;
}

public void setNoticeTitle(String noticeTitle) {
 this.noticeTitle = noticeTitle;
}

public String getUserId() {
 return userId;
}

public void setUserId(String userId) {
 this.userId = userId;
}

public Date getNoticeDate() {
 return noticeDate;
}

public void setNoticeDate(Date noticeDate) {
 this.noticeDate = noticeDate;
}

public int getNoticeCount() {
 return noticeCount;
}

public void setNoticeCount(int noticeCount) {
 this.noticeCount = noticeCount;
}

public String getNoticeContent() {
 return noticeContent;
}

public void setNoticeContent(String noticeContent) {
 this.noticeContent = noticeContent;
}

 

 


}

 
