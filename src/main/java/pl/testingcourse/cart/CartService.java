package pl.testingcourse.cart;

import pl.testingcourse.order.OrderStatus;

public class CartService {

  private final CartHandler cartHandler;

  public CartService(CartHandler cartHandler) {
    this.cartHandler = cartHandler;
  }

  Cart processCart(Cart cart) {

    if (cartHandler.canHandleCart(cart)) {
      cartHandler.sendToPrepare(cart);
      cart.getOrders().forEach(order -> order.changeOrderStatus(OrderStatus.PREPARING));
      return cart;
    } else {
      cart.getOrders().forEach(order -> order.changeOrderStatus(OrderStatus.REJECTED));
      return cart;
    }
  }
}
