package com.zeus.beaconproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.squareup.picasso.Picasso;
import com.zeus.beaconproject.MainActivity;
import com.zeus.beaconproject.Models.ChosenProduct;
import com.zeus.beaconproject.Models.WalmartItem;
import com.zeus.beaconproject.R;

import java.util.List;

/**
 * Created by Zeus on 29-Dec-16.
 */

public class ProductDetailFragment extends Fragment implements View.OnClickListener{
    WalmartItem curItem;
    EditText editTextQuantity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.product_detail_fragment, container, false);
        Bundle recBundle=this.getArguments();
        if(recBundle!=null){
            curItem= (WalmartItem) recBundle.get("chosenProduct");
            Toast.makeText(getActivity(), curItem.name, Toast.LENGTH_SHORT).show();
            ImageView productImageView = (ImageView) v.findViewById(R.id.ImageViewProduct);
//            productImageView.setImageDrawable(curItem.productImage);
            Picasso.with(getContext()).load(curItem.thumbnailImage).into(productImageView);
            TextView productTitleTextView = (TextView) v.findViewById(R.id.TextViewProductTitle);
            productTitleTextView.setText(curItem.name);
            TextView productDetailsTextView = (TextView) v.findViewById(R.id.TextViewProductDetails);
            productDetailsTextView.setText(curItem.shortDescription);

            TextView productPriceTextView = (TextView) v.findViewById(R.id.TextViewProductPrice);
            productPriceTextView.setText("Rs" + curItem.salePrice);

//            TextView textViewCurrentQuantity = (TextView) v.findViewById(R.id.textViewCurrentlyInCart);
//            textViewCurrentQuantity.setText("Currently in Cart: "
//                    + ShoppingCartHelper.getProductQuantity(selectedProduct));

            editTextQuantity = (EditText) v.findViewById(R.id.editTextQuantity);

            Button addToCartButton = (Button) v.findViewById(R.id.ButtonAddToCart);
            addToCartButton.setOnClickListener(this);
        }
        return v;
    }

    @Override
    public void onClick(View view) {
    int quantity=0;
        try {
            quantity = Integer.parseInt(editTextQuantity.getText().toString());

            if (quantity < 0) {
                Toast.makeText(getContext(),
                        "Please enter a quantity of 0 or higher",
                        Toast.LENGTH_SHORT).show();
                return;
            }

        } catch (Exception e) {
            Toast.makeText(getContext(),
                    "Please enter a numeric quantity",
                    Toast.LENGTH_SHORT).show();

            return;
        }
        ChosenProduct cp=new ChosenProduct(curItem,quantity);
        List<ChosenProduct> prodDeleteList=new Select()
                .from(ChosenProduct.class)
                .where("itemId=?",curItem.itemId).execute();
        if(prodDeleteList!=null && prodDeleteList.size()>0){
            new Delete()
                    .from(ChosenProduct.class)
                    .where("itemId=?",curItem.itemId).execute();
        }
        if(quantity>0){
            cp.save();
            Toast.makeText(getContext(), "Saved successful: " , Toast.LENGTH_SHORT).show();
        }
        MainActivity.updateShoppingList();
    }
}
