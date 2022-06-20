package com.tyfff.entity;

/**
 * 订单明细类
 * 
 * @author lujun
 * 
 */
public class Orderitem {
	//订单明细编号
	private Integer orderitem_id;
	//所属订单
	private Order order;
	//商品
	private Goods goods;
	//购买数量
	private Integer goods_num;

	public Orderitem() {
	}

	public Orderitem(Integer orderitem_id, Order order, Goods goods, Integer goods_num) {
		this.orderitem_id = orderitem_id;
		this.order = order;
		this.goods = goods;
		this.goods_num = goods_num;
	}

	public Integer getOrderitem_id() {
		return orderitem_id;
	}
	public void setOrderitem_id(Integer orderitem_id) {
		this.orderitem_id = orderitem_id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Integer getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
	}
	
}
