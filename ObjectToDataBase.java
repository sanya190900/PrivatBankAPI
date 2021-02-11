package org.example;

public class ObjectToDataBase {
    private String date;
    private double saleRateUSD;

    public ObjectToDataBase(String date, double saleRateUSD) {
        this.date = date;
        this.saleRateUSD = saleRateUSD;
    }

    public String getDate() {
        return date;
    }

    public double getSaleRateUSD() {
        return saleRateUSD;
    }

    @Override
    public String toString() {
        return "ObjectToDataBase{" +
                "date='" + date + '\'' +
                ", saleRateUSD=" + saleRateUSD +
                '}';
    }
}
