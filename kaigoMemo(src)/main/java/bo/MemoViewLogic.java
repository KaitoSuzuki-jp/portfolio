package bo;

import dao.MemosDAO;
import model.MemoView;
import model.Memos;

public class MemoViewLogic {
	//データベースからIDが一致する申し送り内容を取得して返す（引数にクリックされた申し送りのID）
	public Memos execute(MemoView memoView) {
		
		MemosDAO memosDAO = new MemosDAO();
		Memos memos = memosDAO.findMemosID(memoView);
		return memos;
	}

}
