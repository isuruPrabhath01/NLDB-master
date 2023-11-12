package to;

public class ItemTo {
    private String id;
    private String name;
    private int qty;
    private double price;
    private String type;

    public ItemTo(String id, String name, int qty, double price, String type) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.type = type;
    }

    public ItemTo(String name, int qty) {
        this.name = name;
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
