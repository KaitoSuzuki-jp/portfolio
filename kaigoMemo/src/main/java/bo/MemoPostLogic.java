package bo;

import dao.MemosDAO;
import model.MemoPost;
import model.Memos;

public class MemoPostLogic {
	//入力申し送りインスタンスをデータベースに保存
	public Memos execute(MemoPost memoPost) {
		
		//入力申し送りインスタンスをMEMOSテーブルに登録（引数に入力申し送りインスタンス）
		MemosDAO memosDAO = new MemosDAO();
		Memos memos = memosDAO.insertMemos(memoPost);
		
		//申し送りインスタンスがnullの場合、エラーメッセージを作成して返す
		if(memos == null || memos.getErrorMsg() == null) {
			memos.setErrorMsg("申し送り情報の投稿に失敗しました");
		}
		return memos;
	}
}
