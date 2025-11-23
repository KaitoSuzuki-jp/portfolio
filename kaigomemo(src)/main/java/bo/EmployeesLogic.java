package bo;

import dao.EmployeesDAO;
import model.Employees;
import model.InputEmployee;

public class EmployeesLogic {

	public Employees execute(InputEmployee inputEmp) {
		//職員情報をデータベースで検索（引数に入力職員インスタンス）
		EmployeesDAO empDAO = new EmployeesDAO();
		Employees emp = empDAO.findEmployees(inputEmp);
		
		//職員情報がnullの場合、エラーメッセージを作成して返す
		if(emp == null) {
			emp = new Employees();
			emp.setErrorMsg("職員情報を取得できませんでした");
			return emp;
		}
		return emp;
	}
}
