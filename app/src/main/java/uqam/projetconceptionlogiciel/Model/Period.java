package uqam.projetconceptionlogiciel.Model;

import java.sql.Date;

public class Period {

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
