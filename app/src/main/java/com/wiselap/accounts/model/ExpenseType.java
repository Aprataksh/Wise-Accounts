package com.wiselap.accounts.model;

public class ExpenseType {
    private int id;
    private String expense_name;
    private int frequency;
    private double amount;

    public ExpenseType(int id, String expense_name, int frequency, double amount) {
        this.id = id;
        this.expense_name = expense_name;
        this.frequency = frequency;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpense_name() {
        return expense_name;
    }

    public void setExpense_name(String expense_name) {
        this.expense_name = expense_name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
