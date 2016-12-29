package com.zeus.beaconproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zeus.beaconproject.Models.WalmartItem;

import java.util.ArrayList;

/**
 * Created by Zeus on 28-Dec-16.
 */

public class ProductAdapter extends BaseAdapter {

    private ArrayList<WalmartItem> mProductList;
    private LayoutInflater mInflater;
    private boolean mShowQuantity;
    Context context;

    public ProductAdapter(ArrayList<WalmartItem> list, LayoutInflater inflater, boolean showQuantity,Context context) {
        mProductList = list;
        mInflater = inflater;
        mShowQuantity = showQuantity;
        this.context=context;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewItem item;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.each_list_item, null);
            item = new ViewItem();

            item.productImageView = (ImageView) convertView
                    .findViewById(R.id.ImageViewItem);
            item.productTitle = (TextView) convertView
                    .findViewById(R.id.TextViewItem);
            item.productQuantity = (TextView) convertView
                    .findViewById(R.id.textViewQuantity);
            item.productWalmartPrice=(TextView) convertView
                    .findViewById(R.id.textViewWalmartPrice);

            convertView.setTag(item);
        } else {
            item = (ViewItem) convertView.getTag();
        }

        WalmartItem curProduct = mProductList.get(position);

        Picasso.with(context).load(curProduct.thumbnailImage).into(item.productImageView);
        item.productTitle.setText(curProduct.name);
        item.productWalmartPrice.setText("Rs: "+curProduct.salePrice);
        // Show the quantity in the cart or not
        if (mShowQuantity) {
//            item.productQuantity.setText("Quantity: "
//                    + ShoppingCartHelper.getProductQuantity(curProduct));
        } else {
            // Hid the view
            item.productQuantity.setVisibility(View.GONE);
        }

        return convertView;
    }

    private class ViewItem {
        ImageView productImageView;
        TextView productTitle;
        TextView productQuantity;
        TextView productWalmartPrice;
    }

}
