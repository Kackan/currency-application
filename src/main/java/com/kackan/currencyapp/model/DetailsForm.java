package com.kackan.currencyapp.model;

public class DetailsForm {

    private String start;
    private String end;
    private String base;
    private String symbol;

    public DetailsForm() {
    }

    public DetailsForm(String base) {
        this.base = base;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String ends) {
        this.end = ends;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "DetailsForm{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", base='" + base + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
