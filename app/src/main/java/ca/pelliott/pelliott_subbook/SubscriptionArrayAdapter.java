/* SubscriptionArrayAdapter
 *
 * Version 1.0
 *
 * Feb 01, 2018
 *
 * Copyright (c) 2018 Peter Elliott, CMPUT301, University of Alberta - All
 * rights Reserved you may use, distribute or modify this code under terms and
 * conditions of Code of Student Behavior at University of Alberta you can find
 * a copy of the license in this project. Otherwise, please contact
 * pelliott@ualberta.ca
 */
package ca.pelliott.pelliott_subbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

// the idea for this class from:
// https://github.com/codepath/android-custom-array-adapter-demo
// 2018-01-20 (no code copied) (MIT)

/**
 * SubscriptionArrayAdapter is an ArrayAdapter<Subscription> that
 * displays arrays according to R.layout.sub_list_item
 * and displays the total price as the first element according to
 * R.layout.sub_list_total.
 *
 * NOTE: be sure to account for the total in your onclick
 *
 * @author pelliott
 * @version 1.0
 *
 * @see ArrayAdapter
 * @see Subscription
 */
public class SubscriptionArrayAdapter extends ArrayAdapter<Subscription> {

    /**
     * the internal ArrayList of Subscriptions.
     */
    private ArrayList<Subscription> subs;

    /**
     * constructor for SubscriptionArrayAdapter.
     *
     * @param context android Context (for saving)
     * @param subs    ArrayList of Subscriptions
     */
    public SubscriptionArrayAdapter(Context context,
                                    ArrayList<Subscription> subs) {
        super(context, 0, subs);
        this.subs = subs;
    }

    /**
     * renders an individual list item.
     *
     * @param position index to be drawn at
     * @param view     android View to be possibly reused
     * @param parent   unused
     * @return the rendered view
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (position == 0) {
            // create the total list element
            if (view == null) {
                // this line was modified from the afformentioned demo
                view = LayoutInflater.from(getContext()).inflate(
                        R.layout.sub_list_total, parent, false);
                // counter intuitive, but this disables clicking
                view.setClickable(true);
            }

            double totalPrice = 0;

            for (Subscription sub: this.subs) {
                totalPrice += sub.getCharge();
            }

            TextView priceview = (TextView) view.findViewById(R.id.totalPrice);
            priceview.setText(String.format("$%.2f", totalPrice));

            return view;
        } else {
            // create the other list elements
            position -= 1;
            if (view == null) {
                // this line was modified from the afformentioned demo
                view = LayoutInflater.from(getContext()).inflate(
                        R.layout.sub_list_item, parent, false);
            }

            Subscription sub = getItem(position);

            TextView name    = (TextView) view.findViewById(R.id.LIname);
            TextView price   = (TextView) view.findViewById(R.id.LIprice);
            TextView date    = (TextView) view.findViewById(R.id.LIdate);

            name.setText(sub.getName());
            price.setText(String.format("$%.2f", sub.getCharge()));

            DateFormat datef = new SimpleDateFormat("yyyy-mm-dd");
            date.setText(datef.format(sub.getDate()));

            return view;
        }
    }

    /**
     * gets the count + 1, in order to make room for the total.
     *
     * @return the length of the arraylist + 1
     */
    @Override
    public int getCount() {
        return super.getCount() + 1;
    }
}
