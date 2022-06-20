package sportshop.pojo.Cart;

import java.util.Collection;
import java.util.LinkedHashMap;import java.util.Map;

public class Cart {
    private Integer totalPrice = 0;
    private final Map<Integer, CartItem> cartItems = new LinkedHashMap<>();

    public void addCartItem(CartItem cartItem){
        CartItem item = cartItems.getOrDefault(cartItem.getGoods_id(), cartItem);
        if (item!=cartItem){
            item.addGoods_quantity(cartItem.getGoods_quantity());
        }
        cartItems.put(cartItem.getGoods_id(),item);
        calculateTotalPrice();
    }

    public void updateCartItemQuantity(Integer goods_id, Integer goods_quantity){
        CartItem cartItem = cartItems.get(goods_id);
        if (cartItem!=null){
            cartItem.setGoods_quantity(goods_quantity);
            calculateTotalPrice();
        }
    }

    public void removeCartItem(Integer goods_id){
        CartItem removeItem = cartItems.remove(goods_id);
        calculateTotalPrice();
    }

    public void clear(){
        cartItems.clear();
    }

    public boolean isEmpty(){
        return cartItems.isEmpty();
    }

    public void calculateTotalPrice(){
         totalPrice = cartItems.values().stream().map((item) -> {
            return item.getGoods_price() * item.getGoods_quantity();
        }).reduce(0, Integer::sum);
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Collection<CartItem> getCartItems() {
        return cartItems.values();
    }
}
