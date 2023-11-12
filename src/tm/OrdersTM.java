package tm;

import java.time.LocalDate;

public class OrdersTM {
    private String cusId;
    private String cusName;
    private String orderId;
    private LocalDate date;
    private double total;

    public OrdersTM() {
    }

    public OrdersTM(String cusId, String cusName, String orderId, LocalDate date, double total) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.orderId = orderId;
        this.date = date;
        this.total = total;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
