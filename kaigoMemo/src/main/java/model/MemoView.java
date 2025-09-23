package model;

//申し送り詳細取得用インスタンス
public class MemoView {
	private int id;	//申し送りID
	
	//コンストラクタ
	public MemoView() {}
	public MemoView(int id) {
		this.id = id;
	}
	//getterとsetter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
