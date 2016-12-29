package com.zeus.beaconproject.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Zeus on 28-Dec-16.
 */

public class PaginatedItemResult implements Serializable {
    public String nextPage;
    public ArrayList<WalmartItem> items;
}
