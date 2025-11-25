package model;

import java.time.LocalDateTime;

public class Memos {
	
	private int id;					//申し送りID
	private String companyName;		//会社名
	private String facilityName;	//施設名
	private String type;			//サービス形態
	private LocalDateTime dateTime; //投稿日時
	private String title;			//申し送りタイトル
	private String memo;			//申し送り内容
	private String name;			//職員名（記入者）
	private String errorMsg;		//エラーメッセージ
	
	//コンストラクタ
	public Memos() {}
	//申し送りリスト生成時に使用
	public Memos(int id, LocalDateTime dateTime, String title, String name) {
		this.id = id;
		this.dateTime = dateTime;
		this.title = title;
		this.name = name;
	}
	
	//getterとsetter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDatetime(LocalDateTime datetime) {
		this.dateTime = datetime;
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
