package classes;

public class Order {
    private Integer orderId;
    private Double orderValue;
    private Store store;

    public Order(Integer orderId, Double orderValue, Store store){
        this.orderId = orderId;
        this.orderValue = orderValue;
        this.store = store;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Double orderValue) {
        this.orderValue = orderValue;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "Valor do pedido= R$ " + orderValue +
                ", Loja=" + store.getStoreName() +
                "}\n";
    }
}
