package my.model;

public class Cart {
 private int cartId;
 private String userId;
 private int productId;
 private String productName;
 private int amount;
 private int price;
 


public Cart() {}


public Cart(int cartId, int price, int amount) {
	super();
	this.cartId = cartId;
	this.price=price;
	this.amount = amount;
}


public Cart(String userId, int productId, String productName, int amount, int price ){
 super();
 this.userId = userId;
 this.productId = productId;
 this.productName = productName;
 this.amount = amount;
 this.price = price; 
}


public String getProductName() {
	return productName;
}


public void setProductName(String productName) {
	this.productName = productName;
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


/**
 * @return the price
 */
public int getPrice() {
 return price;
}


/**
 * @param price the price to set
 */
public void setPrice(int price) {
 this.price = price;
}


/**
 * @return the cartId
 */
public int getCartId() {
 return cartId;
}


/**
 * @param cartId the cartId to set
 */
public void setCartId(int cartId) {
 this.cartId = cartId;
}

 


}