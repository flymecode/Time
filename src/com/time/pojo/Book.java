package com.time.pojo;

import java.io.Serializable;

/**
 * 书籍
 */
public class Book implements Serializable {
    /**
     * 书籍Id
     */
    private Integer bookId;
    /**
     * 书籍名称
     */
    private String title;
    /**
     * 书籍作者
     */
    private String author;
    /**
     * 书籍价格
     */
    private Integer price;
    /**
     * 分类
     */
    private String categoryId;
    /**
     * 图片
     */
    private String image;
    /**
     * isbn
     */
    private String isbn;

    /**
     * 出版日期
     */
    private String pubdate;
    /**
     * 出版社
     */
    private String publisher;
    /**
     * 页数
     */
    private String pages;
    /**
     * 简介
     */
    private String summary;

    /**
     * 权重
     */
    private Integer weight;
    /**
     * 库存
     */
    private Integer number;
    /**
     * 上架时间
     */
    private Integer putAwayTime;

    public Integer getPutAwayTime() {
        return putAwayTime;
    }

    public void setPutAwayTime(Integer putAwayTime) {
        this.putAwayTime = putAwayTime;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
