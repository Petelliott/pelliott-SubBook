package ca.pelliott.pelliott_subbook;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by peter on 22/01/18.
 */

public class SubscriptionList {
    private static SubscriptionList instance = null;

    private ArrayList<Subscription> sublist;
    private Context context;

    private final String FILENAME = "ca.pelliott.pelliott_subbook.SubscriptionList.sav";

    // make sure SubscriptionList cant be instantiated
    private SubscriptionList(Context context) {
        this.context = context;
        this.load();
    }

    public static SubscriptionList getInstance(Context context) {
        if (instance == null) {
            instance = new SubscriptionList(context);
        }
        return instance;
    }

    public Subscription getSubscr(int index) {
        return sublist.get(index);
    }

    public void addSubscr(Subscription sub) {
        sublist.add(sub);
        save();
    }

    // WARNING: this function will invalidate all indexes you have lying around.
    // make sure to account for this
    public void remove(int index) {
        sublist.remove(index);
        save();
    }

    public void remove(Subscription sub) {
        sublist.remove(sub);
        save();
    }

    public ArrayList<Subscription> getArray() {
        return sublist;
    }

    private void load() {
        // this is adapted from the lab 26-01-2018
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //26-01-2018
            sublist = gson.fromJson(in, new TypeToken<ArrayList<Subscription>>(){}.getType());


        } catch (FileNotFoundException e) {
            sublist = new ArrayList<Subscription>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void save() {
        // this is adapted from the lab 26-01-2018
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(sublist, out);

            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
