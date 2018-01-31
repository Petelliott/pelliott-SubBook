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

/**
 * SubscriptionArrayAdapter is an ArrayAdapter<Subscription> that
 * displays arrays according to R.layout.sub_list_item
 * and displays the total price as the first element according to
 * R.layout.sub_list_total
 * NOTE: be sure to account for the total in your onclick listener
 */

// the idea for this class and its layout from:
// https://github.com/codepath/android-custom-array-adapter-demo

public class SubscriptionArrayAdapter extends ArrayAdapter<Subscription> {

    private ArrayList<Subscription> subs;

    public SubscriptionArrayAdapter(Context context, ArrayList<Subscription> subs) {
        super(context, 0, subs);
        this.subs = subs;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (position == 0) {
            // create the total list element
            if (view == null) {
                // this line was modified from the afformentioned demo
                view = LayoutInflater.from(getContext()).inflate(R.layout.sub_list_total, parent, false);
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
                view = LayoutInflater.from(getContext()).inflate(R.layout.sub_list_item, parent, false);
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

    @Override
    public int getCount() {
        return super.getCount() + 1;
    }
}
