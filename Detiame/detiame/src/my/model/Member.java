package my.model;

import java.util.Date;

public class Member {
	private int memberId;
	private String userId;
	private String password;
	private String userName;
	private String userAddress;
	private String phone;
	private String email;
	private Date birthdate;
	
	public Member() {}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Member(String userId, String password, String userName, String userAddress, String phone, String email,
			Date birthdate) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.userAddress = userAddress;
		this.phone = phone;
		this.email = email;
		this.birthdate = birthdate;
	}

	public Member(String userId, String password, String email, String phone, String userAddress) {
		this.userId =  userId;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.userAddress = userAddress;

	}
	
	public Member(int memberId, String password, String email, String phone, String userAddress) {
		this.memberId = memberId;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.userAddress = userAddress;

	}

	
	
}
