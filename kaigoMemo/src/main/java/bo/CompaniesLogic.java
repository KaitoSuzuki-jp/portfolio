package bo;

import dao.CompaniesDAO;
import model.Companies;
import model.InputCompany;

public class CompaniesLogic {
	
	public Companies execute(InputCompany inputCom) {
		//会社情報をデータベースから取得
		CompaniesDAO comDAO = new CompaniesDAO();
		Companies com = comDAO.findCompanies(inputCom);
		//会社情報がnullの場合、エラーメッセージを作成して返す
		if(com == null) {
			com = new Companies();
			com.setErrorMsg("会社情報を取得できませんでした");
			return com;
		}
		return com;
	}
}
