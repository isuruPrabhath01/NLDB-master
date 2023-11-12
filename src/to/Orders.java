package to;

public class Orders {
    private String id;
    private String orderDate;
    private String customerId;

    public Orders(String id, String orderDate, String customerId) {
        this.setId(id);
        this.setOrderDate(orderDate);
        this.setCustomerId(customerId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
