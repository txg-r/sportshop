package sportshop.pojo.Cart;

import java.util.Objects;

public class CartItem {
    private Integer goods_id;
    private String goods_name;
    private Integer goods_price;
    private Integer goods_quantity;



    public CartItem(Integer goods_id, String goods_name, Integer goods_price, Integer goods_quantity) {
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.goods_price = goods_price;
        this.goods_quantity = goods_quantity;
    }

    public CartItem() {
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Integer getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(Integer goods_price) {
        this.goods_price = goods_price;
    }

    public Integer getGoods_quantity() {
        return goods_quantity;
    }

    public void setGoods_quantity(Integer goods_quantity) {
        this.goods_quantity = goods_quantity;
    }

    public void addGoods_quantity(Integer addition){
        goods_quantity+=addition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(goods_id, cartItem.goods_id) && Objects.equals(goods_name, cartItem.goods_name) && Objects.equals(goods_price, cartItem.goods_price) && Objects.equals(goods_quantity, cartItem.goods_quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goods_id, goods_name, goods_price, goods_quantity);
    }
}
