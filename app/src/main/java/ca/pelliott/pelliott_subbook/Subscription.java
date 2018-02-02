/* Subscription
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

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Date;

/**
 * Subscription holds relevant data and enforces type restrictions
 *
 * @author pelliott
 * @version 1.0
 *
 * @see SubscriptionList
 */
public class Subscription implements Serializable{
    private String name;  // max 20 characters
    private Date date;
    private double charge;
    private String comment;  // max 30 characters


    /* _setName, _setCharge, _setComment provide setters that are private so
       that they can be used by the constructor and setName() */

    private void _setName(String name) throws InvalidParameterException {
        if (name.length() < 1) {
            throw new InvalidParameterException("name cannot be empty");
        } else if (name.length() > 20) {
            throw new InvalidParameterException("name too long");
        } else {
            this.name = name;
        }
    }

    private void _setCharge(double charge) throws InvalidParameterException {
        if (charge < 0.0) {
            throw new InvalidParameterException("charge cannot be negative");
        } else {
            this.charge = charge;
        }
    }

    private void _setComment(String comment) throws InvalidParameterException {
        if (comment.length() > 30) {
            throw new InvalidParameterException("comment too long");
        } else {
            this.comment = comment;
        }
    }

    /**
     * constructor for Subscription with provided date
     *
     * @param name less than 20 characters non empty
     * @param date a valid Date
     * @param charge non negative
     * @param comment less than 30 characters
     * @throws InvalidParameterException thrown when varible conditions violated
     */
    public Subscription(String name, Date date, double charge, String comment) throws InvalidParameterException {
        _setName(name);
        this.date = date;
        _setCharge(charge);
        _setComment(comment);
    }

    /**
     * constructor for Subscription with current date
     *
     * @param name less than 20 characters non empty
     * @param charge non negative
     * @param comment less than 30 characters
     * @throws InvalidParameterException thrown when varable conditions violated
     */
    public Subscription(String name, double charge, String comment) throws InvalidParameterException {
        _setName(name);
        this.date = new Date();
        _setCharge(charge);
        _setComment(comment);
    }

    /**
     * @param name less than 20 characters non empty
     * @throws InvalidParameterException thrown when name condition violated
     */
    public void setName(String name) throws InvalidParameterException {
        _setName(name);
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @param charge non negative
     * @throws InvalidParameterException thrown when charge condition violated
     */
    public void setCharge(double charge) throws InvalidParameterException {
        _setCharge(charge);
    }

    /**
     * @param comment less than 30 characters
     * @throws InvalidParameterException thrown when comment condition violated
     */
    public void setComment(String comment) throws InvalidParameterException {
        _setComment(comment);
    }

    public String getName() {
        return this.name;
    }

    public Date getDate() {
        return this.date;
    }

    public double getCharge() {
        return this.charge;
    }

    public String getComment() {
        return this.comment;
    }
}
