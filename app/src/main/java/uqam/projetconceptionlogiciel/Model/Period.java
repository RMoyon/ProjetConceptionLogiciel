package uqam.projetconceptionlogiciel.Model;

import java.io.Serializable;
import java.sql.Date;

public class Period implements Serializable {

    private Integer id;
    private Date startDate;
    private Date endDate;

    public Integer getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

}
