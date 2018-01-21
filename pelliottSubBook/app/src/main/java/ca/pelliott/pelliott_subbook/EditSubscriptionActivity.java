package ca.pelliott.pelliott_subbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * Created by peter on 20/01/18.
 */

public class EditSubscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subscription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // from https://developer.android.com/guide/topics/ui/menus.html
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_sub_menu, menu);
        return true;
    }
}
