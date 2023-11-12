package to;

import java.sql.Date;

public class AnimalTo {
    private String id;
    private String type;
    private int qty;

    public AnimalTo(String type, int qty) {
        this.type = type;
        this.qty = qty;
    }

    private Date date;

    public AnimalTo(String id, String type, int qty, Date date) {
        this.id = id;
        this.type = type;
        this.qty = qty;
        this.date = date;
    }

    public AnimalTo(int id, String qty) {
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
}
