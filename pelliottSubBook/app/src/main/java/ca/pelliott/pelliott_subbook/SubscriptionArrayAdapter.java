package ca.pelliott.pelliott_subbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by peter on 20/01/18.
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
            if (view == null) {
                // this line was modified from the afformentioned demo
                view = LayoutInflater.from(getContext()).inflate(R.layout.sub_list_total, parent, false);
            }

            double totalPrice = 0;

            for (Subscription sub: this.subs) {
                totalPrice += sub.getCharge();
            }

            TextView priceview = (TextView) view.findViewById(R.id.totalPrice);
            priceview.setText(String.format("$%.2f", totalPrice));

            return view;
        } else {
            position -= 1;
            if (view == null) {
                // this line was modified from the afformentioned demo
                view = LayoutInflater.from(getContext()).inflate(R.layout.sub_list_item, parent, false);
            }

            Subscription sub = getItem(position);

            TextView name    = (TextView) view.findViewById(R.id.LIname);
            TextView price   = (TextView) view.findViewById(R.id.LIprice);
            TextView comment = (TextView) view.findViewById(R.id.LIcomment);

            name.setText(sub.getName());
            price.setText(String.format("$%.2f", sub.getCharge()));
            comment.setText(sub.getComment());

            return view;
        }
    }

    @Override
    public int getCount() {
        return super.getCount() + 1;
    }
}
