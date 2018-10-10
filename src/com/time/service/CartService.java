package com.time.service;

import com.time.pojo.Cart;

public interface CartService {

    /**
     *  将购物车添加至redis
     * @param userName
     * @param cart
     */
    void addCartToRedis(String userName, Cart cart);

    /**
     * 取出购物车
     * @param userName
     * @return
     */
    Cart findCartFromRedis(String userName);

    /**
     * 取出特定的商品
     */
    Cart selectCartFromRedisByBookIds(String[] bookIds, String userName);
}
