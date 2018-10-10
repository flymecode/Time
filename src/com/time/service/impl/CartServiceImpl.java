package com.time.service.impl;

import com.time.pojo.Book;
import com.time.pojo.Cart;
import com.time.pojo.Item;
import com.time.service.CartService;
import com.time.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CartServiceImpl implements CartService {

    @Override
    public void addCartToRedis(String userName, Cart cart) {
        Jedis jedis = JedisUtils.getJedis();
        List<Item> items = cart.getItems();
        if (items.size() > 0) {
            Map<String, String> hash = new HashMap<>();
            for (Item item : items) {
                if (jedis.hexists("cart:" + userName, String.valueOf(item.getBook().getBookId()))) {
                    jedis.hincrBy("cart:" + userName, String.valueOf(item.getBook().getBookId()), item.getBookNumber());
                }else{
                    hash.put(String.valueOf(item.getBook().getBookId()), String.valueOf(item.getBookNumber()));
                }
            }
            if (hash.size() > 0) {
                jedis.hmset("cart:"+userName, hash);
            }
        }
        jedis.close();
    }

    @Override
    public Cart findCartFromRedis(String userName) {
        Jedis jedis = JedisUtils.getJedis();
        Cart cart = new Cart();
        //获取所有商品, redis中保存的是skuId 为key , amount 为value的Map集合
        Map<String, String> hgetAll = jedis.hgetAll("cart:"+userName);
        Set<Entry<String, String>> entrySet = hgetAll.entrySet();
        // TODO 有问题，在item中有book
        for (Map.Entry<String, String> entry : entrySet) {
            //entry.getKey(): bookId
            Book book = new Book();
            book.setBookId(Integer.parseInt(entry.getKey()));
            Item item = new Item();
            item.setBook(book);
            //entry.getValue(): bookNumber
            item.setBookNumber(Integer.parseInt(entry.getValue()));
            //添加到购物车中
            cart.addItem(item);
        }

        return cart;
    }

    //从购物车中取出指定商品
    public Cart selectCartFromRedisByBookIds(String[] bookIds,String userName){
        Cart cart = new Cart();
        //获取所有商品, redis中保存的是skuId 为key , amount 为value的Map集合
        Jedis jedis = JedisUtils.getJedis();
        Map<String, String> hgetAll = jedis.hgetAll("cart:"+userName);
        if (null != hgetAll && hgetAll.size() > 0) {
            Set<Map.Entry<String, String>> entrySet = hgetAll.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                for (String skuId : bookIds) {
                    if (skuId.equals(entry.getKey())) {
                        //entry.getKey(): skuId
                        Book book = new Book();
                        book.setBookId(Integer.parseInt(entry.getKey()));
                        Item item = new Item();
                        item.setBook(book);
                        //entry.getValue(): amount
                        item.setBookNumber(Integer.parseInt(entry.getValue()));
                        //添加到购物车中
                        cart.addItem(item);
                    }
                }
            }
        }

        return cart;
    }
}
