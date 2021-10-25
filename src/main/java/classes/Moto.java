package classes;

import java.util.ArrayList;
import java.util.List;

public class Moto {
    private String motoName;
    private Double comission;
    private List<Integer> storesAttended;
    private List<Order> orders;

    public Moto(String motoName, Double comission, List<Integer> storesAttended){
        this.motoName = motoName;
        this.comission = comission;
        this.storesAttended = storesAttended;
        this.orders = new ArrayList<>();
    }

    public String getMotoName() {
        return motoName;
    }

    public void setMotoName(String motoName) {
        this.motoName = motoName;
    }

    public Double getComission() {
        return comission;
    }

    public void setComission(Double comission) {
        this.comission = comission;
    }

    public List<Integer> getStoresAttended() {
        return storesAttended;
    }

    public void setStoresAttended(List<Integer> storesAttended) {
        this.storesAttended = storesAttended;
    }

    public void addOrder(Order orderToBeAdded){
        this.orders.add(orderToBeAdded);
    }

    public List<Order> getOrders() {
        return orders;
    }

    private Double calculateProfit(){
        Double profit = 0.0;
        for(int i = 0; i < orders.size(); i++){
            profit += orders.get(i).getOrderValue() * orders.get(i).getStore().getDeliveryFeePercentage();
            profit += this.comission;
        }
        return profit;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "nome='" + motoName + "\n" +
                ", valor a receber= R$ " + this.calculateProfit() + "\n" +
                ", pedidos= " + orders + "\n" +
                '}';
    }
}
