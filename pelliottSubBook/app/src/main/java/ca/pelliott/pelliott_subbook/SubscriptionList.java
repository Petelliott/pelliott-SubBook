package ca.pelliott.pelliott_subbook;

import java.util.ArrayList;

/**
 * Created by peter on 22/01/18.
 */

public final class SubscriptionList {
    private static ArrayList<Subscription> sublist;

    static {
        // TODO: load on creation
        sublist = new ArrayList<>();
    }

    public static Subscription getSubscr(int index) {
        return sublist.get(index);
    }

    public static void addSubscr(Subscription sub) {
        sublist.add(sub);
    }

    public static ArrayList<Subscription> getArray() {
        return sublist;
    }

    public static void load() {
        // TODO
    }

    public static void save() {
        // TODO
    }

}
