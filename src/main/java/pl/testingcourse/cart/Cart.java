package pl.testingcourse.cart;

import pl.testingcourse.Meal;
import pl.testingcourse.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Order> orders = new ArrayList<>();

    void addOrderToCart(Order order) {
        this.orders.add(order);

    }

    void clearCart() {
        this.orders.clear();
    }

    public List<Order> getOrders() {

        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void simulateLargeOrder(){

        for(int i = 0; i<1000; i++){
            Meal meal = new Meal(i%10, "Hamburger no "+i);
            Order order = new Order();
            order.addMealToOrder(meal);
            addOrderToCart(order);
        }
        System.out.println("Cart size: "+orders.size());
        clearCart();
    }


}
