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
    public SubscriptionArrayAdapter(Context context, ArrayList<Subscription> subs) {
        super(context, 0, subs);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
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
