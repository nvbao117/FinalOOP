package model;
import java.util.Date ; 
public class Promotion {
	private int id;
	private Date effectiveTime;
	private String condition;
	private double discount;

	// Constructor
	public Promotion(int id, Date effectiveTime, String condition, double discount) {
		this.id = id;
		this.effectiveTime = effectiveTime;
		this.condition = condition;
		this.discount = discount;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", effectiveTime=" + effectiveTime + ", condition=" + condition + ", discount="
				+ discount + "]";
	}
	
}
