package ca.pelliott.pelliott_subbook;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Date;

/**
 * Subscription holds:
 * String name    (1-20 characters)
 * Date date      (valid date)
 * double charge  (non negative)
 * String comment (less than 30)
 */

public class Subscription implements Serializable{
    private String name;  // max 20 characters
    private Date date;
    private double charge;
    private String comment;  // max 30 characters


    // _setName, _setCharge, _setComment provide setters that are private so
    // that they can be used by the constructor and setName()

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

    public Subscription(String name, Date date, double charge, String comment) throws InvalidParameterException {
        _setName(name);
        this.date = date;
        _setCharge(charge);
        _setComment(comment);
    }

    public Subscription(String name, double charge, String comment) throws InvalidParameterException {
        _setName(name);
        this.date = new Date();
        _setCharge(charge);
        _setComment(comment);
    }

    public void setName(String name) throws InvalidParameterException {
        _setName(name);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCharge(double charge) throws InvalidParameterException {
        _setCharge(charge);
    }

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
