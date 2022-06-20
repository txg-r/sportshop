package pers.tyfff.sportshop.entity;

import java.sql.Date;

public class Goods {
    private Integer goods_id;
    private String goods_name;
    private String goods_miaoshu;
    //商品地址
    private String goods_pic;
    private Integer market_price;
    private Integer mall_price;
    private Integer catelog_id;
    private Integer store_num;
    private String goods_address;
    private Date enter_date;

    public Goods(Integer goods_id, String goods_name, String goods_miaoshu, String goods_pic, Integer market_price, Integer mall_price, Integer catelog_id, Integer store_num, String goods_address, Date enter_date) {
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.goods_miaoshu = goods_miaoshu;
        this.goods_pic = goods_pic;
        this.market_price = market_price;
        this.mall_price = mall_price;
        this.catelog_id = catelog_id;
        this.store_num = store_num;
        this.goods_address = goods_address;
        this.enter_date = enter_date;
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

    public String getGoods_miaoshu() {
        return goods_miaoshu;
    }

    public void setGoods_miaoshu(String goods_miaoshu) {
        this.goods_miaoshu = goods_miaoshu;
    }

    public String getGoods_pic() {
        return goods_pic;
    }

    public void setGoods_pic(String goods_pic) {
        this.goods_pic = goods_pic;
    }

    public Integer getMarket_price() {
        return market_price;
    }

    public void setMarket_price(Integer market_price) {
        this.market_price = market_price;
    }

    public Integer getMall_price() {
        return mall_price;
    }

    public void setMall_price(Integer mall_price) {
        this.mall_price = mall_price;
    }

    public Integer getCatelog_id() {
        return catelog_id;
    }

    public void setCatelog_id(Integer catelog_id) {
        this.catelog_id = catelog_id;
    }

    public Integer getStore_num() {
        return store_num;
    }

    public void setStore_num(Integer store_num) {
        this.store_num = store_num;
    }

    public String getGoods_address() {
        return goods_address;
    }

    public void setGoods_address(String goods_address) {
        this.goods_address = goods_address;
    }

    public Date getEnter_date() {
        return enter_date;
    }

    public void setEnter_date(Date enter_date) {
        this.enter_date = enter_date;
    }
}
