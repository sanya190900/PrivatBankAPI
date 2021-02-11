package org.example;

import java.util.ArrayList;

public class Base {
    private String date;
    private String bank;
    private int baseCurrency;
    private String baseCurrencyLit;
    private ArrayList<Exchange> exchangeRate = new ArrayList<>();
    private Exchange usd = new Exchange();

    public Exchange getUsd() {
        return usd;
    }

    public void setUsd(Exchange usd) {
        this.usd = usd;
    }

    @Override
    public String toString() {
        return "Base{" +
                "date='" + date + '\'' +
                ", bank='" + bank + '\'' +
                ", baseCurrency=" + baseCurrency +
                ", baseCurrencyLit='" + baseCurrencyLit + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(int baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }

    public ArrayList<Exchange> getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ArrayList<Exchange> exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    Exchange findUSD(){
        for (Exchange ex : exchangeRate){
            if (ex.getCurrency()!= null) {
                if (ex.getCurrency().equals("USD")){
                    usd = ex;
                    break;
                }
            }
        }
        return usd;
    }

    class Exchange{
        private String baseCurrency;
        private String currency;
        private double saleRateNB;
        private double purchaseRateNB;
        private double saleRate;
        private double purchaseRate;

        @Override
        public String toString() {
            return "Exchange{" +
                    "baseCurrency='" + baseCurrency + '\'' +
                    ", currency='" + currency + '\'' +
                    ", saleRateNB=" + saleRateNB +
                    ", purchaseRateNB=" + purchaseRateNB +
                    ", saleRate=" + saleRate +
                    ", purchaseRate=" + purchaseRate +
                    '}';
        }

        public String getBaseCurrency() {
            return baseCurrency;
        }

        public void setBaseCurrency(String baseCurrency) {
            this.baseCurrency = baseCurrency;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public double getSaleRateNB() {
            return saleRateNB;
        }

        public void setSaleRateNB(double saleRateNB) {
            this.saleRateNB = saleRateNB;
        }

        public double getPurchaseRateNB() {
            return purchaseRateNB;
        }

        public void setPurchaseRateNB(double purchaseRateNB) {
            this.purchaseRateNB = purchaseRateNB;
        }

        public double getSaleRate() {
            return saleRate;
        }

        public void setSaleRate(double saleRate) {
            this.saleRate = saleRate;
        }

        public double getPurchaseRate() {
            return purchaseRate;
        }

        public void setPurchaseRate(double purchaseRate) {
            this.purchaseRate = purchaseRate;
        }
    }
}
