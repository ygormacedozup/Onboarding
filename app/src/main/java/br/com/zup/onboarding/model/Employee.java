package br.com.zup.onboarding.model;

import java.util.Date;

public class Employee {
    private String name;
    private String email;
    private Date startDate;
    private String originalCity;
    private String workCity;
    private int phone;
    private String pointOfDelivery;
    private String deliverySuccessManager;
    private String businessPartner;

    public Employee(String name, String email, Date startDate, String originalCity,
                    String workCity, int phone, String pointOfDelivery, String deliverySuccessManager,
                    String businessPartner) {
        this.name = name;
        this.email = email;
        this.startDate = startDate;
        this.originalCity = originalCity;
        this.workCity = workCity;
        this.phone = phone;
        this.pointOfDelivery = pointOfDelivery;
        this.deliverySuccessManager = deliverySuccessManager;
        this.businessPartner = businessPartner;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getOriginalCity() {
        return originalCity;
    }

    public String getWorkCity() {
        return workCity;
    }

    public int getPhone() {
        return phone;
    }

    public String getPointOfDelivery() {
        return pointOfDelivery;
    }

    public String getDeliverySuccessManager() {
        return deliverySuccessManager;
    }

    public String getBusinessPartner() {
        return businessPartner;
    }
}
