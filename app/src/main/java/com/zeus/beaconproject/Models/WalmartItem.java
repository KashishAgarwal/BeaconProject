package com.zeus.beaconproject.Models;


import java.io.Serializable;

/**
 * Created by Zeus on 29-Dec-16.
 */

public class WalmartItem implements Serializable {

    public long itemId;
    public String name;
    public String  category;
    public String thumbnailImage;
    public String shortDescription;
    public String longDescription;
    public double msrp;
    public double salePrice;
}
