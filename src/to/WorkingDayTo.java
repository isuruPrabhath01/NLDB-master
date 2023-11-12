package to;

import java.sql.Date;

public class WorkingDayTo {
    private String id;
    private Date date;

    public WorkingDayTo(String string, Date date) {
        id=string;
        this.date=date;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
