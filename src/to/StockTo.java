package to;

public class StockTo<SingleSelectionMode> {
    private String id;
    private String name;
    private  SingleSelectionMode type;
    private int qty;
    private double price;

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

    public  SingleSelectionMode getType() {
        return type;
    }

    public void setType( SingleSelectionMode type) {
        this.type = type;
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

    public StockTo(String id, String name,  SingleSelectionMode type, int qty, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.qty = qty;
        this.price = price;

    }
}
