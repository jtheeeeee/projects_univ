package my.model;

public class Product {
	private int productId;
	private String productName;
	private int price;
	private String color;
	private String productImage;
	private int accessCount;
	private int productType;

public Product(){}
	
	public Product(int productId, String productName, int price, String color, String productImage) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.color = color;
		this.productImage = productImage;
		
	}
	
	public Product(int productId, String productName, int price, String color, String productImage, int productType) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.color = color;
		this.productImage = productImage;
		this.productType =  productType;
		
	}
	
	public Product (String productName, int price, String color, String productImage) {
		super();
		this.productName = productName;
		this.price = price;
		this.color = color;
		this.productImage = productImage;
		
	} //method overrunning	
	public Product(String productName, int price, String color, String productImage, int productType) {
		super();
		this.productName = productName;
		this.price = price;
		this.color = color;
		this.productImage = productImage;
		this.productType=productType;
		
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public int getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}
	
	
	

}
