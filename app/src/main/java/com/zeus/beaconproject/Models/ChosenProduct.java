package com.zeus.beaconproject.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
 * Created by Zeus on 29-Dec-16.
 */
@Table(name="ChosenProduct")
public class ChosenProduct extends Model implements Serializable {
    public ChosenProduct(){
        super();
    }
    public ChosenProduct(WalmartItem wi,int qt){
        super();
        itemId=wi.itemId;
        name=wi.name;
        category=wi.category;
        thumbnailImage=wi.thumbnailImage;
        shortDescription=wi.shortDescription;
        longDescription=wi.longDescription;
        msrp=wi.msrp;
        salePrice=wi.salePrice;
        quantity=qt;
    }
    @Column(name="itemId")
    public long itemId;
    @Column(name="name")
    public String name;
    @Column(name="category")
    public String  category;
    @Column(name="thumbnailImage")
    public String thumbnailImage;
    @Column(name="shortDescription")
    public String shortDescription;
    @Column(name="longDescription")
    public String longDescription;
    @Column(name="msrp")
    public double msrp;
    @Column(name="salePrice")
    public double salePrice;
    @Column(name="quantity")
    public int quantity;

    public WalmartItem getWalmartItem(){
        WalmartItem ww= new WalmartItem(itemId,name,category,thumbnailImage,shortDescription,longDescription,msrp,salePrice);
        return ww;
    }

}
