package classes;

public class Store {
    private Integer storeName;
    private Double deliveryFeePercentage;

    public Store(Integer storeName, Double deliveryFeePercentage){
        this.storeName = storeName;
        this.deliveryFeePercentage = deliveryFeePercentage;
    }

    public Integer getStoreName() {
        return storeName;
    }

    public void setStoreName(Integer storeName) {
        this.storeName = storeName;
    }

    public Double getDeliveryFeePercentage() {
        return deliveryFeePercentage;
    }

    public void setDeliveryFeePercentage(Double deliveryFeePercentage) {
        this.deliveryFeePercentage = deliveryFeePercentage;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeName='" + storeName + '\'' +
                ", deliveryFeePercentage=" + deliveryFeePercentage +
                '}';
    }
}
