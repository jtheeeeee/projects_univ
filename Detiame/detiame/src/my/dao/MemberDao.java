package my.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import my.model.Member;
import my.util.JdbcUtil;

public class MemberDao {
	
	public void insert(Connection conn, Member member) 
			throws SQLException {
		PreparedStatement pstmt=null; 
		try {
			pstmt = conn.prepareStatement
			("insert into member (userId,password,userName,userAddress,phone,email,birthdate)"
					+ " values(?,?,?,?,?,?,?)");
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getUserAddress());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getEmail());
			pstmt.setTimestamp(7, new Timestamp(member.getBirthdate().getTime()));
			pstmt.executeUpdate(); 
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn); //jsp에서 conn열고 dao에서 닫음 그래서 헷갈리는 문제! 열고 닫기 jsp에서 해주는 걸로 문제 해결하기
			JdbcUtil.close(pstmt);
		}
	}
	
	public String selectPasswordById(Connection conn, String userId) //userId를 줬을 때 그 사람의 PASSWORD 읽기가 목적??
			throws SQLException {
		PreparedStatement pstmt=null; 
		ResultSet rs = null;
		String password = null; // password 리턴해주기 위해서
		try {
			pstmt = conn.prepareStatement
			("select password from member where userId = ?");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()){
				password = rs.getString(1);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return password;
	}
	public int selectMemberIdById(Connection conn, String userId) //userId를 줬을 때 그 사람의 PASSWORD 읽기가 목적??
			throws SQLException {
		PreparedStatement pstmt=null; 
		ResultSet rs = null;
		Member member = new Member();
		int memberId = 0; // password 리턴해주기 위해서
		try {
			pstmt = conn.prepareStatement
			("select memberId from member where userId = ?");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()){
				
				memberId = rs.getInt(1);
				
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return memberId;
	}
	
	public int selectByMemberId(Connection conn, String userId) 
			throws SQLException {
		PreparedStatement pstmt=null; 
		ResultSet rs = null;
		Member member = null; 
		int memberId = 0;
		try {
			pstmt = conn.prepareStatement
			("select memberId from member where userId=?");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()){
				member = new Member();
				member.setMemberId(rs.getInt(1));
				memberId = member.getMemberId(); 
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return memberId;
	}

public Member selectByMemberId(Connection conn, int memberId) 
		throws SQLException {
	PreparedStatement pstmt=null; 
	ResultSet rs = null;
	Member member = null; 
	try {
		pstmt = conn.prepareStatement
		("select * from member where memberId = ?");
		pstmt.setInt(1, memberId);
		rs = pstmt.executeQuery();
		if (rs.next()){
			member = new Member();
			member.setMemberId(rs.getInt(1));
			member.setUserId(rs.getString(2));
			member.setPassword(rs.getString(3));
			member.setUserName(rs.getString(4));
			member.setUserAddress(rs.getString(5));
			member.setPhone(rs.getString(6));
			member.setEmail(rs.getString(7));
			member.setBirthdate(rs.getTimestamp(8));
		}
	} catch (SQLException e){
		e.printStackTrace();
	} finally {
		//JdbcUtil.close(conn);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(rs);
	}
	return member;
}
public Member selectById(Connection conn, int memberId) 
		throws SQLException {
	PreparedStatement pstmt=null; 
	ResultSet rs = null;
	Member member = null; 
	try {
		pstmt = conn.prepareStatement
		("select * from member where memberId = ?");
		pstmt.setInt(1, memberId);
		rs = pstmt.executeQuery();
		if (rs.next()){
			member = new Member();
			member.setMemberId(rs.getInt(1));
			member.setUserId(rs.getString(2));
			member.setPassword(rs.getString(3));
			member.setUserName(rs.getString(4));
			member.setUserAddress(rs.getString(5));
			member.setPhone(rs.getString(6));
			member.setEmail(rs.getString(7));
			member.setBirthdate(rs.getTimestamp(8));
		}
	} catch (SQLException e){
		e.printStackTrace();
	} finally {
		//JdbcUtil.close(conn);
		JdbcUtil.close(pstmt);
		JdbcUtil.close(rs);
	}
	return member;
}


	public void update(Connection conn, Member member) 
			throws SQLException {
		PreparedStatement pstmt=null; 
		try {
			pstmt = conn.prepareStatement
			("update member set password=?, userAddress=?, phone=?, email=? where memberId=?");
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getUserAddress());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEmail());
			pstmt.setInt(5, member.getMemberId());
			pstmt.executeUpdate(); 
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}

	public void deleteById(Connection conn, int memberId) 
			throws SQLException {
		PreparedStatement pstmt=null; 		
		try {
			pstmt = conn.prepareStatement
			("delete from member where memberId = ?");
			pstmt.setInt(1, memberId);
			pstmt.executeUpdate();			
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	


	public List<Member> selectList(Connection conn) 
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> memberList = null;
		try {
			pstmt = conn.prepareStatement
					("select * from member");
			rs  = pstmt.executeQuery(); 
			memberList = new ArrayList<Member>();
			while (rs.next()){
				Member member = new Member();
				member.setMemberId(rs.getInt(1));
				member.setUserId(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setUserName(rs.getString(4));
				member.setUserAddress(rs.getString(5));
				member.setPhone(rs.getString(6));
				member.setEmail(rs.getString(7));
				member.setBirthdate(rs.getTimestamp(8));
				memberList.add(member);
			}
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return memberList;
	}



}

















