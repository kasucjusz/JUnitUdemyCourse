package pl.testingcourse.cart;

public interface CartHandler {

  boolean canHandleCart(Cart cart);

  void sendToPrepare(Cart cart);

}
