package tm;

public class CartTM {
    private String itemCode;
    private String itemName;
    private int qty;
    private double unitPrice;
    private double subTotal;

    public CartTM(String itemCode, String itemName, int qty, double unitPrice, double subTotal) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
