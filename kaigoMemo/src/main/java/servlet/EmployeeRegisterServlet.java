package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bo.EmployeeRegisterLogic;
import model.Companies;
import model.Employees;
import model.InputEmployee;
import model.ModelError;

@WebServlet("/EmployeeRegisterServlet")
public class EmployeeRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//職員登録画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/employeeRegister.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//職員登録画面・職員選択画面からの遷移
		
		//セッションスコープから会社インスタンスを取得
		HttpSession session = request.getSession();
		Companies com = (Companies)session.getAttribute("com");
		
		//入力職員インスタンスを生成
		InputEmployee inputEmp = new InputEmployee();

		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		inputEmp.setName(request.getParameter("name"));
		inputEmp.setCompanyName(com.getCompanyName());
		inputEmp.setFacilityName(com.getFacilityName());
		inputEmp.setType(com.getType());
		String action = request.getParameter("action");
		
		//actionの値がemployeeCheckなら確認画面へフォワード
		if("employeeCheck".equals(action)) {
			
			//入力職員インスタンスをセッションスコープに保存
			session.setAttribute("inputEmp", inputEmp);
			
			//確認画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/checkEmployeeRegister.jsp");
			dispatcher.forward(request, response);
			return;
			
		//actionの値がemployeeCheckOKならメイン画面へフォワード
		} else if("employeeCheckOK".equals(action)) {
			
			//入力職員インスタンスをEMPLOYEESテーブルに登録
			EmployeeRegisterLogic empRegiLogic = new EmployeeRegisterLogic();
			Employees emp = empRegiLogic.execute(inputEmp);
			
			//データベースに登録された職員インスタンスをセッションスコープに保存
			session.setAttribute("emp", emp);
			
			//職員情報がない、または取得できなかった場合エラー画面へフォワード
			if(emp == null || emp.getErrorMsg() != null) {
				
				//エラーインスタンスを生成（引数はエラーコード）してセッションスコープに保存
				ModelError error = new ModelError("emp");
				session.setAttribute("error", error);
				
				//エラー画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
				return;
				
			}
			
			//申し送り一覧画面(MainServlet)へリダイレクト
			response.sendRedirect("MainServlet");
			return;	
			
		} else {
			//職員登録画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/employeeRegister.jsp");
			dispatcher.forward(request, response);
			return;
		}


	}

}
