package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.InputCompany;

@WebServlet("/CompanyServlet")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//会社名確認画面から遷移
		
		//施設名入力画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/facility.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//会社名入力画面からの遷移
		
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String companyName = request.getParameter("company");
		
		//入力会社インスタンスに会社名を保存
		InputCompany inputCom = new InputCompany();
		inputCom.setCompanyName(companyName);
		
		//入力会社インスタンスをセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("inputCom", inputCom);
		
		//会社名確認画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/checkCompanyName.jsp");
		dispatcher.forward(request, response);
	}

}
