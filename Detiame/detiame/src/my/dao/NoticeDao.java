package my.dao;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

import my.model.Notice;

import my.util.JdbcUtil;

public class NoticeDao {
 

 public void insert(Connection conn, Notice notice) 
   throws SQLException {
  PreparedStatement pstmt=null; 
  try {
   pstmt = conn.prepareStatement
   ("insert into notice (noticeTitle,userId,noticeDate,noticeContent)"
     + " values(?,?,?,?)");
   pstmt.setString(1, notice.getNoticeTitle());
   pstmt.setString(2, notice.getUserId());
   pstmt.setTimestamp(3, new Timestamp(notice.getNoticeDate().getTime()));
   pstmt.setString(4, notice.getNoticeContent());

   
   pstmt.executeUpdate(); 
  } catch (SQLException e){
   e.printStackTrace();
  } finally {
   //JdbcUtil.close(conn); 여러 번 들어갈 수 있으므로 한 번 하고 닫지 않도록 하기.
   JdbcUtil.close(pstmt);
  }
 }

 public Notice selectById(Connection conn, String userId) 
   throws SQLException {
  PreparedStatement pstmt=null; 
  ResultSet rs = null;
  Notice notice = null; 
  try {
   pstmt = conn.prepareStatement
   ("select * from notice where userId = ?");
   pstmt.setString(1, userId );
   rs = pstmt.executeQuery();
   if (rs.next()){
    notice = new Notice(); 
    notice.setNoticeId(rs.getInt(1));
    notice.setNoticeTitle(rs.getString(2));
    notice.setUserId(rs.getString(3));
    notice.setNoticeDate(rs.getTimestamp(4));
    notice.setNoticeContent(rs.getString(5));

    
   }
  } catch (SQLException e){
   e.printStackTrace();
  } finally {
   //JdbcUtil.close(conn);
   JdbcUtil.close(pstmt);
   JdbcUtil.close(rs);
  }
  return notice;
 }

 public void update(Connection conn, Notice notice) 
   throws SQLException {
  PreparedStatement pstmt=null; 
  try {
   pstmt = conn.prepareStatement
   ("update notice set noticeTitle=?, noticeContent=? where noticeId=?");
   pstmt.setString(1, notice.getNoticeTitle());
   pstmt.setString(2, notice.getNoticeContent());
   pstmt.setInt(3, notice.getNoticeId());

   pstmt.executeUpdate(); 
  } catch (SQLException e){
   e.printStackTrace();
  } finally {
   //JdbcUtil.close(conn);
   JdbcUtil.close(pstmt);
  }
 }
 
 public void updateAccessCount(Connection conn, int noticeId) 
   throws SQLException {
  PreparedStatement pstmt=null; 
  try {
   pstmt = conn.prepareStatement
   ("update notice set noticeCount=noticeCount +1 where noticeId=?");
   pstmt.setInt(1, noticeId);
   pstmt.executeUpdate(); 
  } catch (SQLException e){
   e.printStackTrace();
  } finally {
   //JdbcUtil.close(conn);
   JdbcUtil.close(pstmt);
  }
 }
 
 public void deleteById(Connection conn, int noticeId) 
   throws SQLException {
  PreparedStatement pstmt=null;   
  try {
   pstmt = conn.prepareStatement
   ("delete from notice where noticeId = ?");
   pstmt.setInt(1, noticeId);
   pstmt.executeUpdate();   
  } catch (SQLException e){
   e.printStackTrace();
  } finally {
   //JdbcUtil.close(conn);
   JdbcUtil.close(pstmt);
  }
 }
 
 public int selectCount(Connection conn) throws SQLException {
  Statement stmt = null; 
  ResultSet rs = null; 
  try {
   stmt = conn.createStatement();
   rs = stmt.executeQuery("select count(*) from notice");
   rs.next();
   return rs.getInt(1);
  } finally {
   //JdbcUtil.close(conn);
   JdbcUtil.close(rs);
  }
 }

 public List<Notice> selectList(Connection conn) 
   throws SQLException {
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  List<Notice> noticeList = null;
  try {
   pstmt = conn.prepareStatement
   ("select * from notice ");
   rs  = pstmt.executeQuery(); 
   noticeList = new ArrayList<Notice>();
   while (rs.next()){
    Notice notice = new Notice();
    notice.setNoticeId(rs.getInt(1));
    notice.setNoticeTitle(rs.getString(2));
    notice.setUserId(rs.getString(2));
    notice.setNoticeDate(rs.getTimestamp(4));
    notice.setNoticeCount(rs.getInt(5));
    notice.setNoticeContent(rs.getString(6));
    noticeList.add(notice);
   }
  } finally {
   //JdbcUtil.close(conn);
   JdbcUtil.close(rs);
   JdbcUtil.close(pstmt);
  }
  return noticeList;
 }
 
 
 public List<Notice> selectListByNoticeId(Connection conn, int noticeId) 
   throws SQLException {
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  List<Notice> noticeList = null;
  try {
   pstmt = conn.prepareStatement
   ("select * from notice where noticeId = ?");
   pstmt.setInt(1, noticeId );
   rs  = pstmt.executeQuery(); 
   noticeList = new ArrayList<Notice>();
   while (rs.next()){
    Notice notice = new Notice();
    notice.setNoticeId(rs.getInt(1));
    notice.setNoticeTitle(rs.getString(2));
    notice.setUserId(rs.getString(2));
    notice.setNoticeDate(rs.getTimestamp(4));
    notice.setNoticeCount(rs.getInt(5));
    notice.setNoticeContent(rs.getString(6));
    noticeList.add(notice);
   }
  } finally {
   //JdbcUtil.close(conn);
   JdbcUtil.close(rs);
   JdbcUtil.close(pstmt);
  }
  return noticeList;
 }
}
 
