package pl.testingcourse.cart;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import pl.testingcourse.order.Order;
import pl.testingcourse.order.OrderStatus;

class CartServiceTest {

  @Test
  void processCartShouldSendToPrepare() {
    // given
    Order order = new Order();
    Cart cart = new Cart();
    cart.addOrderToCart(order);

    CartHandler cartHandler = mock(CartHandler.class);
    CartService cartService= new CartService(cartHandler);

    given(cartHandler.canHandleCart(cart)).willReturn(true);

    //when
    Cart resultCar = cartService.processCart(cart);

    //then
    verify(cartHandler).sendToPrepare(cart);
    verify(cartHandler, times(1)).sendToPrepare(cart);
    verify(cartHandler, atLeastOnce()).sendToPrepare(cart);
    then(cartHandler).should().sendToPrepare(cart);

    InOrder inOrder = Mockito.inOrder(cartHandler);
    inOrder.verify(cartHandler).canHandleCart(cart);
    inOrder.verify(cartHandler).sendToPrepare(cart);

    assertThat(resultCar.getOrders(), hasSize(1));
    assertThat(resultCar.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));

  }


  @Test
  void processCartShouldNotSendToPrepare() {
    // given
    Order order = new Order();
    Cart cart = new Cart();
    cart.addOrderToCart(order);

    CartHandler cartHandler = mock(CartHandler.class);
    CartService cartService= new CartService(cartHandler);

    given(cartHandler.canHandleCart(cart)).willReturn(false);

    //when
    Cart resultCar = cartService.processCart(cart);

    //then
    verify(cartHandler, never()).sendToPrepare(cart);
  then(cartHandler).should(never()).sendToPrepare(cart);


    assertThat(resultCar.getOrders(), hasSize(1));
    assertThat(resultCar.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));

  }
}
