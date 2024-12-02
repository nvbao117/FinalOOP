package model;

public class InvoiceDetail {
	private int invoiceId;
	private int productId;
	private int quantity;
	private double totalAmount;

	// Constructor
	public InvoiceDetail(int invoiceId, int productId, int quantity, double totalAmount) {
		this.invoiceId = invoiceId;
		this.productId = productId;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
	}

	// Getters and Setters
	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "InvoiceDetail [invoiceId=" + invoiceId + ", productId=" + productId + ", quantity=" + quantity
				+ ", totalAmount=" + totalAmount + "]";
	}
	
}
