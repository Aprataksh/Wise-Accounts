package com.wiselap.accounts.model;

public class SalaryReport {
    private String name;
    private double amount;

    public SalaryReport(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName(){
        return this.name;
    }

    public double getAmount(){
        return this.amount;
    }
}
