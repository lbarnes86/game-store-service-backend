package com.company.gamestoreservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class SalesTax {

    @Id
    private String state;
    private BigDecimal rate;


    public SalesTax() {
    }

    public SalesTax(String state, double rate) {
        this.state = state;
        this.rate = new BigDecimal(rate);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesTax salesTax = (SalesTax) o;
        return Objects.equals(state, salesTax.state) && Objects.equals(rate, salesTax.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, rate);
    }

    @Override
    public String toString() {
        return "SalesTax{" +
                "state='" + state + '\'' +
                ", rate=" + rate +
                '}';
    }
}
