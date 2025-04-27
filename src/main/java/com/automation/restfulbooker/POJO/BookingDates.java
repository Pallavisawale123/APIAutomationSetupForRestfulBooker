package com.automation.restfulbooker.POJO;

/**
 * @author psawale
 * @project restfulbooker
 * @date 4/26/2025
 */
public class BookingDates {
    private String checkin;
    private String checkout;

    // Constructors
    public BookingDates() {
    }

    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    // Getters and Setters
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}

