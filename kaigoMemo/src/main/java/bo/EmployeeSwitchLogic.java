package bo;

import java.util.List;

import dao.EmployeesDAO;
import model.Companies;
import model.Employees;

public class EmployeeSwitchLogic {
	//会社情報が一致する職員一覧をデータベースから取得
	public List<Employees> execute(Companies com){
		
		//EMPLOYEESテーブルを検索（引数に会社インスタンス）
		EmployeesDAO empDAO = new EmployeesDAO();
		List<Employees> empList = empDAO.findEmployeesList(com);
		return empList;
	}
}
