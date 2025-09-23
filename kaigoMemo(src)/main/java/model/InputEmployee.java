package model;

public class InputEmployee {
	private String companyName;	 //会社名
	private String facilityName; //施設名
	private String type;		 //サービス形態
	private String name;		 //職員名
	
	//コンストラクタ
	public InputEmployee() {}
	public InputEmployee(String companyName, String facilityName, String type) {
		this.companyName = companyName;
		this.facilityName = facilityName;
		this.type = type;
	}
	
	//getterとsetter
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
