package bo;

import dao.EmployeesDAO;
import model.Employees;
import model.InputEmployee;

public class EmployeeRegisterLogic {
	//EMPLOYEESテーブルに登録
	public Employees execute(InputEmployee inputEmp) {
		
		//入力職員インスタンスをEMPLOYEESテーブルに登録
		EmployeesDAO empDAO = new EmployeesDAO();
		Employees emp = empDAO.insertEmployees(inputEmp);
		return emp;
	}

}
