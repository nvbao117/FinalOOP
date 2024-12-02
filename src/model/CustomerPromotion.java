package model;

public class CustomerPromotion {
	private int customerId;
	private int promotionId;

	public CustomerPromotion(int customerId, int promotionId) {
		this.customerId = customerId;
		this.promotionId = promotionId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getPromotionId() {
		return promotionId;
	}

	@Override
	public String toString() {
		return "CustomerPromotion [customerId=" + customerId + ", promotionId=" + promotionId + "]";
	}
	
	
}
