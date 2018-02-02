/* Subscription
 *
 * Version 1.0
 *
 * Feb 01, 2018
 *
 * Copyright (c) 2018 Peter Elliott, CMPUT301, University of Alberta - All
 * rights Reserved you may use, distribute or modify this code under terms and
 * conditions of Code of Student Behavior at University of Alberta you can find
 * a copy of the license in this project. Otherwise, please contact
 * pelliott@ualberta.ca
 */
package ca.pelliott.pelliott_subbook;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Date;

/**
 * Subscription holds relevant data and enforces type restrictions.
 *
 * @author pelliott
 * @version 1.0
 *
 * @see SubscriptionList
 */
public class Subscription implements Serializable {
    /**
     * stores a name.
     * between 1 and 20 characters inclusive.
     */
    private String name;
    /**
     * stores the date a subscription was created.
     */
    private Date date;
    /**
     * stores the monthly price of a subscription.
     * non-negative
     */
    private double charge;
    /**
     * 0-30 characters inclusive.
     */
    private String comment;


    /* setNameInternal, setChargeInternal, _setComment provide setters that
    are private so that they can be used by the constructor and setName() */

    /**
     * internal function for setting the name with proper restrictions.
     *
     * @param name the name
     * @throws InvalidParameterException when restrictions are not met
     */
    private void setNameInternal(String name)
            throws InvalidParameterException {
        if (name.length() < 1) {
            throw new InvalidParameterException("name cannot be empty");
        } else if (name.length() > 20) {
            throw new InvalidParameterException("name too long");
        } else {
            this.name = name;
        }
    }

    /**
     * internal function for setting the charge with proper restrictions.
     *
     * @param charge the charge
     * @throws InvalidParameterException when restrictions are not met
     */
    private void setChargeInternal(double charge)
            throws InvalidParameterException {
        if (charge < 0.0) {
            throw new InvalidParameterException("charge cannot be negative");
        } else {
            this.charge = charge;
        }
    }

    /**
     * internal function for setting the comment with proper restrictions.
     *
     * @param comment the comment
     * @throws InvalidParameterException when restrictions are not met
     */
    private void setCommentInternal(String comment)
            throws InvalidParameterException {
        if (comment.length() > 30) {
            throw new InvalidParameterException("comment too long");
        } else {
            this.comment = comment;
        }
    }

    /**
     * constructor for Subscription with provided date.
     *
     * @param name less than 20 characters non empty
     * @param date a valid Date
     * @param charge non negative
     * @param comment less than 30 characters
     * @throws InvalidParameterException thrown when any condition is violated
     */
    public Subscription(String name, Date date, double charge, String comment)
            throws InvalidParameterException {
        setNameInternal(name);
        this.date = date;
        setChargeInternal(charge);
        setCommentInternal(comment);
    }

    /**
     * constructor for Subscription with current date.
     *
     * @param name less than 20 characters non empty
     * @param charge non negative
     * @param comment less than 30 characters
     * @throws InvalidParameterException thrown when any condition is violated
     */
    public Subscription(String name, double charge, String comment)
            throws InvalidParameterException {
        setNameInternal(name);
        this.date = new Date();
        setChargeInternal(charge);
        setCommentInternal(comment);
    }

    /**
     * @param name less than 20 characters non empty
     * @throws InvalidParameterException thrown when name condition violated
     */
    public void setName(String name) throws InvalidParameterException {
        setNameInternal(name);
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
        setChargeInternal(charge);
    }

    /**
     * @param comment less than 30 characters
     * @throws InvalidParameterException thrown when comment condition violated
     */
    public void setComment(String comment) throws InvalidParameterException {
        setCommentInternal(comment);
    }

    /**
     * simple getter for name.
     *
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * simple getter for date.
     *
     * @return this.date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * simple getter for charge.
     *
     * @return this.charge
     */
    public double getCharge() {
        return this.charge;
    }

    /**
     * simple getter for comment.
     *
     * @return this.comment
     */
    public String getComment() {
        return this.comment;
    }
}
