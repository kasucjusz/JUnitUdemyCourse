package pl.testingcourse.order;

import pl.testingcourse.Meal;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Meal> meals=new ArrayList<>();

    private OrderStatus orderStatus;

    public void addMealToOrder(Meal meal){
        this.meals.add(meal);
    }

    public void changeOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void removeMealFRomOrder(Meal meal){
        this.meals.remove(meal);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void cancel(){
        this.meals.clear();
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
