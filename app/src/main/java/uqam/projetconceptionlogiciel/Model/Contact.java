
public class Contact {

	private String email;
	private String phoneNumber;
	private String websiteUrl;
	
	public Contact(String email, String phoneNumber, String websiteUrl) {
		super();
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.websiteUrl = websiteUrl;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	
	@Override
	public String toString() {
		return "Contact [email=" + email + ", phoneNumber=" + phoneNumber + ", websiteUrl=" + websiteUrl + "]";
	}
	
	
}
