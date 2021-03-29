package pl.testingcourse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.io.ObjectInputFilter.Config;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import okio.ByteString;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.testingcourse.extensions.IAExceptionIgnoreExtension;
import pl.testingcourse.order.Order;

class MealTest {

  private static Stream<Arguments> createMealsWithNameAndPrice() {
    return Stream.of(Arguments.of("Hamburger", 10), Arguments.of("Cheeseburger", 12));
  }

  private static Stream<String> createCakeNames() {
    List<String> cakeNames = Arrays.asList("Cheesecake", "Fruitcake", "Cupcake");
    return cakeNames.stream();
  }



  @Test
  void bufferArrayTest() {


    ByteString byteString =
        ByteString.decodeBase64(
            "QSsqwo/G17hLRJxhcxkKwuSXKR/pRBerqte22ZWkDtPXWXEPSh+1xoRRdPonsZmUms8LZXfBOfY4iSNFEBxReQ==");
    ByteBuffer byteBuffer = byteString.asByteBuffer();

    byte[] ivBytes = new byte[64];
    byteBuffer.get(ivBytes);

    byte[] data = new byte[byteBuffer.remaining()];
    byteBuffer.get(data);

    System.out.println(byteBuffer);
  }

  @Test
  void shouldReturnDiscountedPrice() {

    // given
    Meal meal = new Meal(35);

    // when
    int discountedPrice = meal.getDiscountedPrice(7);

    // then
    assertEquals(28, discountedPrice);
    assertThat(discountedPrice, equalTo(28));
  }

  @Test
  void referencesToTheSameObjectShouldBeEqual() {
    // given
    Meal meal = new Meal(10);
    Meal meal1 = meal;

    // then
    assertSame(meal, meal1);
    assertThat(meal, sameInstance(meal1));
  }

  @Test
  void referencesToDifferentObjectShouldNotBeEqual() {
    // given
    Meal meal = new Meal(10);
    Meal meal1 = new Meal(10);

    // then
    assertNotSame(meal, meal1);
    assertThat(meal, not(sameInstance(meal1)));
  }

  @Test
  void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame() {
    // given
    Meal meal = new Meal(10, "Pizza");
    Meal meal1 = new Meal(10, "Pizza");

    // theb
    assertEquals(meal, meal1, "checking if two meal are equal");
  }

  @Test
  void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice() {
    // given
    Meal meal = new Meal(8, "Soup");

    // when
    // then
    assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(10));
  }

  @ParameterizedTest
  @ValueSource(ints = {5, 10, 15, 18})
  void mealPricesShouldBeLowerThan20(int price) {

    assertThat(price, lessThan(20));
  }

  @ParameterizedTest
  @MethodSource("createMealsWithNameAndPrice")
  void burgersShouldHaveCorrectNameAndPrice(String name, int price) {
    assertThat(name, containsString("burger"));
    assertThat(price, greaterThanOrEqualTo(10));
  }

  @ParameterizedTest
  @MethodSource("createCakeNames")
  void cakeNamesShouldEndWithCake(String name) {
    assertThat(name, notNullValue());
    assertThat(name, endsWith("cake"));
  }

  @ExtendWith(IAExceptionIgnoreExtension.class)
  @ParameterizedTest
  @ValueSource(ints = {2, 3, 5, 8})
  void mealPricesShouldBeLowerThan10(int price) {

    if (price > 5) {
      throw new IllegalArgumentException();
    }
    assertThat(price, lessThan(20));
  }

  @TestFactory
  Collection<DynamicTest> dynamicTestCollection() {
    return Arrays.asList(
        dynamicTest("Dynamic test 1", () -> assertThat(5, lessThan(6))),
        dynamicTest("Dynamic test 2 ", () -> assertEquals(4, 2 * 2)));
  }

  private int calculatePrice(int price, int quantity) {

    return price * quantity;
  }

  @TestFactory
  Collection<DynamicTest> calculateMealPrices() {
    Order order = new Order();
    order.addMealToOrder(new Meal(10, "Hamburger", 2));
    order.addMealToOrder(new Meal(12, "Pizza", 5));
    order.addMealToOrder(new Meal(3, "Beer", 12));

    Collection<DynamicTest> dynamicTests = new ArrayList<>();

    int counter = 1;
    for (Meal m : order.getMeals()) {
      int price = m.getPrice();
      int quantity = m.getQuantity();

      Executable executable =
          () -> {
            assertThat(calculatePrice(price, quantity), lessThan(66));
          };
      counter++;
      String name = "test name: " + counter;
      DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
      dynamicTests.add(dynamicTest);
    }

    return dynamicTests;
  }
}
