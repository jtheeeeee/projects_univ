package my.model;

import java.util.Date;

public class Purchase {
	private int purchaseId;
	private String userId;
	private int productId;
	private int amount;
	private int totalPrice;
	private int method;
	private Date purchaseDate;
	private String orderAddress;
	private String phone;
	private String email;
	private String orderName;
	private String productName;
	
	public Purchase() {}
	

	public Purchase(String userId, int productId, int amount, int totalPrice, int method, Date purchaseDate,
			String orderAddress, String phone, String email, String orderName) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.method = method;
		this.purchaseDate = purchaseDate;
		this.orderAddress = orderAddress;
		this.phone = phone;
		this.email = email;
		this.orderName = orderName;
	}



	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
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

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	

}
