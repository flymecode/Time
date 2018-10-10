package com.time.pojo;

import java.io.Serializable;

public class Item implements Serializable {

    private Integer itemId;
    private Integer orderId;
    private Integer bookNumber = 1;
    private Book book;


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((book.getBookId() == null) ? 0 : book.getBookId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) //比较地址

            return true;

        if (obj == null)

            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Item other = (Item) obj;
        // TODO 删除
        System.out.println(other.book.getBookId());
        if(book.getBookId() != other.book.getBookId()){
            return false;
        }

        return true;
    }

    public Integer getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Integer bookNumber) {
        this.bookNumber = bookNumber;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


}
