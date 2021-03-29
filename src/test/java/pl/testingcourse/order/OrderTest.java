package pl.testingcourse.order;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.testingcourse.Meal;
import pl.testingcourse.extensions.BeforeAfterExtension;
import pl.testingcourse.order.Order;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(BeforeAfterExtension.class)
class OrderTest {



    private Order order;

    @BeforeEach
    void setup(){
        System.out.println("Inside @BeforeEach method");
        order=new Order();
    }


    @AfterEach
    void cleanUp(){
        System.out.println("Inside @AfterEach method");
        order.cancel();
    }

    @Test
    void testAssertArrayEquals(){

        //given
        int[] ints={1,2,3};
        int[] ints1={1,2,3};

        //then
        assertArrayEquals(ints, ints1);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder(){
        //given

        //then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        MatcherAssert.assertThat(order.getMeals(), emptyCollectionOf(Meal.class));

    }

    @Test
    void addingVoidToOrderShouldIncreaseOrderSize(){
        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Sandwich");

        //when
        order.addMealToOrder(meal1);


        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal1));
        assertThat(order.getMeals(), hasItem(meal1));
        assertThat(order.getMeals(), not(contains(meal2)));

        assertThat(order.getMeals().get(0).getPrice(), equalTo(15));
    }


}