package model;
import java.util.Date;

public class Invoice {
    private int id;
    private int customerId;
    private int employeeId;
    private Date paymentTime;
    private double totalAmount;
    private String paymentMethod;
    private double receivedAmount;

    // Constructor
    public Invoice(int id, int customerId, int employeeId, Date paymentTime, double totalAmount, String paymentMethod, double receivedAmount) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.paymentTime = paymentTime;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.receivedAmount = receivedAmount;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public Date getPaymentTime() { return paymentTime; }
    public void setPaymentTime(Date paymentTime) { this.paymentTime = paymentTime; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public double getReceivedAmount() { return receivedAmount; }
    public void setReceivedAmount(double receivedAmount) { this.receivedAmount = receivedAmount; }

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", customerId=" + customerId + ", employeeId=" + employeeId + ", paymentTime="
				+ paymentTime + ", totalAmount=" + totalAmount + ", paymentMethod=" + paymentMethod
				+ ", receivedAmount=" + receivedAmount + "]";
	}
    
}
