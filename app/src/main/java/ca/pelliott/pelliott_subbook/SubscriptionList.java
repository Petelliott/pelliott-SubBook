/* SubscriptionList
 *
 * Version 1.0
 *
 * Feb 01, 2018
 *
 * Copyright (c) 2018 Peter Elliott, CMPUT301, University of Alberta - All rights Reserved
 * you may use, distribute or modify this code under terms and conditions of Code of Student
 * Behavior at University of Alberta
 * you can find a copy of the license in this project. Otherwise, please contact contact@abc.ca
 */
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
 * A singleton that holds multiple Subscriptions and saves them on changes
 * and reloads on creation
 *
 * @author pelliott
 * @version 1.0
 *
 * @see Subscription
 */
public class SubscriptionList {
    private static SubscriptionList instance = null;

    private ArrayList<Subscription> sublist;
    private Context context;

    private final String FILENAME = "ca.pelliott.pelliott_subbook.SubscriptionList.sav";

    /**
     * takes and android context in order to save data
     * private so we can control as singleton
     *
     * @param context an android Context
     */
    private SubscriptionList(Context context) {
        this.context = context;
        this.load();
    }

    /**
     * handles instantiating Subscriptionlist as singleton
     *
     * @param context an android Context
     * @return the current instance of SubscriptionList
     */
    public static SubscriptionList getInstance(Context context) {
        if (instance == null) {
            instance = new SubscriptionList(context);
        }
        return instance;
    }

    /**
     * gets a Subscription by it's integer index
     *
     * @param index the index in the list
     * @return the Subscription at Index
     */
    public Subscription getSubscr(int index) {
        return sublist.get(index);
    }

    /**
     * adds a subscription to the list (at the end)
     * and saves the data
     *
     * @param sub the Subscription
     */
    public void addSubscr(Subscription sub) {
        sublist.add(sub);
        save();
    }

    /**
     * removes a subscription by it's index
     * WARNING: this will invalidate all indexes you have out in the wild
     *
     * @param index
     */
    public void remove(int index) {
        sublist.remove(index);
        save();
    }

    /**
     * removes a subscription by it's value
     * WARNING: this will invalidate all indexes you have out in the wild
     *
     * @param sub
     */
    public void remove(Subscription sub) {
        sublist.remove(sub);
        save();
    }

    /**
     * gets the underlying subscription ArrayList, in case you need more specific acess
     *
     *
     * @return sublist the underlying ArrayList
     * @see SubscriptionArrayAdapter
     */
    public ArrayList<Subscription> getArray() {
        return sublist;
    }

    private void load() {
        // this is adapted from the lab 26-01-2018
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylist
            //26-01-2018 (CC-BY-SA)
            sublist = gson.fromJson(in, new TypeToken<ArrayList<Subscription>>(){}.getType());


        } catch (FileNotFoundException e) {
            sublist = new ArrayList<Subscription>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * saves the data to FILENAME
     */
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
