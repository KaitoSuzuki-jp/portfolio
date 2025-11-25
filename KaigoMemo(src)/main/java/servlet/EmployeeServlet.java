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

import bo.EmployeesLogic;
import model.Companies;
import model.Employees;
import model.ModelError;


@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//会社情報確認画面からの遷移
		
		//セッションスコープから会社インスタンスを取得
		HttpSession session = request.getSession();
		Companies com = (Companies)session.getAttribute("com");
		
		//職員一覧をデータベースから取得・セッションスコープに保存
		EmployeesLogic empLogic = new EmployeesLogic();
		List<Employees> empList = empLogic.execute(com);
		session.setAttribute("empList", empList);
		
		//職員情報がない、または取得できなかった場合エラー画面へフォワード
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
			
		//会社情報、職員情報が取得できていたら職員選択画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/employee.jsp");
		dispatcher.forward(request, response);
		return;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//職員名選択画面からの遷移
		
		//セッションスコープから会社インスタンスを取得
		HttpSession session = request.getSession();
		Companies com = (Companies)session.getAttribute("com");
		
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		
		//職員インスタンスを生成
		Employees emp = new Employees(com.getCompanyName(),com.getFacilityName(),com.getType(),name);
		
		//職員インスタンスをセッションスコープに保存
		session.setAttribute("emp", emp);
		
		//メイン画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("MainServlet");
		dispatcher.forward(request, response);
		return;	
		
		
	}

}
