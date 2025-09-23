package bo;

import java.util.List;

import dao.MemosDAO;
import model.Employees;
import model.Memos;

public class MemosLogic {
	//職員の会社名・施設・サービス形態と一致する申し送りインスタンスを検索（引数に職員インスタンス）
	public List<Memos> execute(Employees emp){
		
		MemosDAO memosDAO = new MemosDAO();
		List<Memos> memosList = memosDAO.findMemos(emp);
		return memosList;
	}
}
