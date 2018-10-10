package com.time.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车
 */
public class Cart implements Serializable {

    private List<Item> items = new ArrayList<>();
    private Integer bookNumber = 0;
    private Integer totalPrice = 0;
    private Integer bookPrice = 0;
    private Integer fee = 0;



    /**
     * 添加商品到购物车
     * @param item
     */
    public void addItem(Item item) {
        //判断是否有相同的商品
        if (items.contains(item)) {
            // 追加数量
            for(Item buyItem : items){
                if (buyItem.equals(item)) {
                    buyItem.setBookNumber(item.getBookNumber() + buyItem.getBookNumber());
                }
            }
        } else {
            items.add(item);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * 获取书籍数量
     * @return
     */
    public Integer getBookNumber(){

        Integer result = 0;
        for (Item item : items) {
           result += item.getBookNumber();
        }
        return result;
    }

    public Float getBookPrice() {
        Float result = 0f;
        for (Item item : items) {
            result += item.getBookNumber() * item.getBook().getPrice();
        }
        return result;
    }

    public Float getFee() {
        Float result = 0f;
        if(getBookPrice() < 79f){
            return 5f;
        }
        return result;
    }

    /**
     * 总价（书和运费）
     * @return
     */
    public Float getTotalPrice() {
        return getBookPrice() + getFee();
    }


    public void setBookNumber(Integer bookNumber) {
        this.bookNumber = bookNumber;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setBookPrice(Integer bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setFee(Integer fee) {
        fee = fee;
    }
}
