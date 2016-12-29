package com.zeus.beaconproject.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Zeus on 28-Dec-16.
 */

@Table(name="WalmartItem")
public class WalmartItem extends Model implements Serializable {

    public WalmartItem(){
        super();
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
}
