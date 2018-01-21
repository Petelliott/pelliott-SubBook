package ca.pelliott.pelliott_subbook;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Subscription> testarray = makeTestData(50);

        SubscriptionArrayAdapter adapter = new SubscriptionArrayAdapter(this, testarray);

        ListView listview = (ListView) findViewById(R.id.ListView);
        listview.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditSubscriptionActivity.class);

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
}
