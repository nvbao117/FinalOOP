package model;

public class Product {
	private int id;
	private String name;
	private double price;
	private String status;
	private String image;

	public Product(int id, String name, double price, String status, String image) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.status = status;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", status=" + status + ", image=" + image
				+ "]";
	}
	
	
}