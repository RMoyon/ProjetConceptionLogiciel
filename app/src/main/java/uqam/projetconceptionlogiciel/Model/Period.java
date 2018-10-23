import java.sql.Date;

public class Period {

	private Integer id;
	private Date startDate;
	private Date endDate;
	
	public Period(Integer id, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}
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
