package bo;

import dao.CompaniesDAO;
import model.Companies;
import model.InputCompany;

public class CompanyRegisterLogic {
	 //会社名・施設名をCOMPANIESテーブルに新規登録
    public Companies execute(InputCompany inputCom) {
    	
    	//入力会社インスタンス（会社名・施設名）をCOMPANIESテーブルに登録
        CompaniesDAO comDAO = new CompaniesDAO();
        Companies com = comDAO.insertCompanies(inputCom);

        // DAO が null を返した場合に備えて安全な Companies インスタンスを作る
        if(com == null) {
            com = new Companies();
            com.setErrorMsg("会社情報の登録に失敗しました");
        }

        // INSERT 成功後、必ず会社名などが取得できているかチェック
        if(com.getCompanyName() == null || com.getFacilityName() == null || com.getType() == null) {
            com.setErrorMsg("登録後の会社情報取得に失敗しました");
        }

        return com;
    }
}
