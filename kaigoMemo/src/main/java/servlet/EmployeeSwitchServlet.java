package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bo.EmployeeSwitchLogic;
import model.Companies;
import model.Employees;
import model.ModelError;

@WebServlet("/EmployeeSwitchServlet")
public class EmployeeSwitchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//メイン画面からの遷移
		
		//会社情報が一致する職員一覧を取得
		HttpSession session = request.getSession();
		Companies com = (Companies)session.getAttribute("com");
		EmployeeSwitchLogic empSwitchLogic = new EmployeeSwitchLogic();
		List<Employees> empList = empSwitchLogic.execute(com);
		
		//職員一覧を取得できなかった場合エラー画面へフォワード
		if(empList.isEmpty()) {
			
			//エラーインスタンスを作成(引数はエラーコード)
			ModelError error = new ModelError("emp");
			session.setAttribute("error", error);
			
			//エラーメッセージを作成・セッションスコープに保存
			Employees emp = new Employees();
			emp.setErrorMsg("職員情報を取得できませんでした");
			session.setAttribute("emp", emp);
			
			//エラー画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		//職員一覧をセッションスコープに保存
		session.setAttribute("empList", empList);
		
		//職員選択画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/employeeSwitch.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//職員切り替え画面からの遷移
		
		//職員インスタンスをセッションスコープから取得
		HttpSession session = request.getSession();
		Employees emp = (Employees)session.getAttribute("emp");
		
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		emp.setName(request.getParameter("name"));		
		
		//切り替えた職員情報（職員インスタンス）をセッションスコープに保存
		session.setAttribute("emp", emp);
		
		//申し送り一覧画面（MainServlet）ヘリダイレクト
		response.sendRedirect("MainServlet");
		return;	
		
	}

}
