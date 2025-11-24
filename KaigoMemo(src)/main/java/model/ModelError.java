package model;

public class ModelError {
	private String errorCode; //エラーコード
	
	//コンストラクタ
	public ModelError(){}
	public ModelError(String errorCode){
		this.errorCode = errorCode;
	}
	//getterとsetter
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
