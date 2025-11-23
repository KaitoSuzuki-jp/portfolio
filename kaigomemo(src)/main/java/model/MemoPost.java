package model;

//データベースに保存する前の一時的な入力申し送りインスタンス
public class MemoPost {
	
	private String companyName;	 //会社名
	private String facilityName; //施設名
	private String type;		 //サービス形態
	private String title;		 //申し送りタイトル
	private String memo;		 //申し送り内容
	private String name;		 //職員名（記入者）
	private String errorMsg;	 //エラーメッセージ
	
	//コンストラクタ
	public MemoPost() {}
	public MemoPost(String companyName, String facilityName, String type,
			String datetime, String title, String memo, String name) {
		this.companyName = companyName;
		this.facilityName = facilityName;
		this.type = type;
		this.title = title;
		this.memo = memo;
		this.name = name;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
