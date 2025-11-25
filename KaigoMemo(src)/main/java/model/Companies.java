package model;

public class Companies {
	//COMPANIESテーブルから取得した職場インスタンス
	private String companyName;	 //会社名
	private String facilityName; //施設名
	private String type;		 //サービス形態
	private String errorMsg;	 //エラーメッセージ
	
	//コンストラクタ
	public Companies() {}
	
	public Companies(String companyName, String facilityName, String type) {
		this.companyName = companyName;
		this.facilityName = facilityName;
		this.type = type;
	}
	
	//getter と setter
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
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
