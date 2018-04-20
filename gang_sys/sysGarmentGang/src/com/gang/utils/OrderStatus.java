package com.gang.utils;

/**
 * 订单状态
 * @author Fengdx
 *
 * @date Feb 3, 2016
 */
public class OrderStatus {
	/**
	 * 等待买家付款（待付款）
	 */
	public static int WAITING_PAY = 1;

	/**
	 * 买家已付款，等待卖家发货（待发货） 2
	 */
	public static int WAITING_DELIVERY = 2;

	/**
	 * 卖家已发货，等待买家确认（已发货）
	 */
	public static int HAS_DELIVERIED = 3;

	/**
	 * 买家确认收货，已完成
	 */
	public static int FINISH = 4;
	
	/**
	 * 待评价-去掉，不要
	 */
	public static int WAITING_EVALUATE = 5;

	/**
	 * 已关闭（完成后7天无异议自动关闭）
	 */
	public static int CLOSE = 6;

	/**
	 * 已取消（用户主动取消）
	 */
	public static int CANCEL = 7;

	/**
	 * 已超时（24小时未付款）
	 */
	public static int TIMEOUT = 8;

	/**
	 * 已申请退货退款
	 */
	public static int APPLY_REFUND = 9;

	/**
	 * 拒绝退款
	 */
	public static int REFUSE_REFUND = 10;

	/**
	 * 同意退款
	 */
	public static int AGREE_REFUND = 11;

	/**
	 * 完成退货退款
	 */
	public static int FINISH_REFUND = 12;

	/**
	 * 待消费
	 */
	public static int WAITING_CONSUME = 13;

	/**
	 * 已消费
	 */
	public static int HAS_CONSUMED = 14;

	/**
	 * 退款中
	 */
	public static int REFUNDING = 15;

	/**
	 * 退款成功
	 */
	public static int REFUND_SUCC = 16;

	/**
	 * 申请退款
	 */
	public static int APPLY_REFUNDING = 17;

	/**
	 * 未消费
	 */
	public static int NOT_CONSUME = 18;
	
	/**
	 * 微信订单下单成功
	 */
	public static int WXPAY_ORDER_SUCC = 0;
	
	/**
	 * 微信支付成功
	 */
	public static int WXPAY_SUCC = 1;
	
	/**
	 * 微信支付失败
	 */
	public static int WXPAY_FALSE = 2;
}
