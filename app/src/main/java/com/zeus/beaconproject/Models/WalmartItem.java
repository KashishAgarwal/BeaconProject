package com.zeus.beaconproject.Models;


import java.io.Serializable;

/**
 * Created by Zeus on 29-Dec-16.
 */

public class WalmartItem implements Serializable {

    public long itemId;

    public WalmartItem(long itemId, String name, String category, String thumbnailImage, String shortDescription, String longDescription, double msrp, double salePrice) {
        this.itemId = itemId;
        this.name = name;
        this.category = category;
        this.thumbnailImage = thumbnailImage;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.msrp = msrp;
        this.salePrice = salePrice;
    }

    public String name;
    public String category;
    public String thumbnailImage;
    public String shortDescription;
    public String longDescription;
    public double msrp;
    public double salePrice;


}
