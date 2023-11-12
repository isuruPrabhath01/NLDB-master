package tm;

import java.sql.Date;

public class AnimalTM {
    private String id;
    private String type;
    private int qty;
    private Date date;
    private String supplierId;
    private String cageId;

    public AnimalTM(String id, String type, int qty, Date date, String supplierId, String cageId) {
        this.id = id;
        this.type = type;
        this.qty = qty;
        this.date = date;
        this.supplierId = supplierId;
        this.cageId = cageId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getCageId() {
        return cageId;
    }

    public void setCageId(String cageId) {
        this.cageId = cageId;
    }
}
