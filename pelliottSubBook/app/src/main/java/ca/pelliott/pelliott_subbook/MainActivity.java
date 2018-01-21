package ca.pelliott.pelliott_subbook;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static ca.pelliott.pelliott_subbook.EditSubscriptionActivity.SUBSCRIPTION_EXTRA;

public class MainActivity extends AppCompatActivity {
    static final int NEW_SUBSCRIPTION_REQUEST = 0;

    private ArrayList<Subscription> subscriptions;
    private SubscriptionArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subscriptions = new ArrayList<Subscription>();
        adapter = new SubscriptionArrayAdapter(this, subscriptions);

        ListView listview = (ListView) findViewById(R.id.ListView);
        listview.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditSubscriptionActivity.class);

                startActivityForResult(intent, NEW_SUBSCRIPTION_REQUEST);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Subscription sub = (Subscription) adapter.getItemAtPosition(position);

                Intent intent = new Intent(view.getContext(), ViewSubscriptionActivity.class);
                intent.putExtra(SUBSCRIPTION_EXTRA, sub);

                startActivity(intent);
            }
        });
    }

    private ArrayList<Subscription> makeTestData(int n) {
        ArrayList<Subscription> subs = new ArrayList<Subscription>();

        for (int i = 0; i < n; i++) {
            Subscription sub = new Subscription(
                    "peter monthly",
                    (double) i,
                    "this is a commment"
            );

            subs.add(sub);
        }

        return subs;
    }

    @Override
    protected void onActivityResult(int request, int result, Intent intent) {
        if (request == NEW_SUBSCRIPTION_REQUEST && result == RESULT_OK) {
            Subscription sub = (Subscription) intent.getSerializableExtra(SUBSCRIPTION_EXTRA);
            subscriptions.add(sub);

            adapter.notifyDataSetChanged();
        }
    }
}
