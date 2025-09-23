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

@WebServlet("/TypeServlet")
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//サービス形態確認画面からの遷移
		//actionの値によって各入力画面へフォワード
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if(action.equals("facility")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/facility.jsp");
			dispatcher.forward(request, response);
		} else if(action.equals("type")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/type.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/company.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//サービス形態入力画面からの遷移
		
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		
		//入力会社インスタンスに保存
		HttpSession session = request.getSession();
		InputCompany inputCom = (InputCompany)session.getAttribute("inputCom");
		inputCom.setType(type);
		
		//入力会社インスタンスをセッションスコープに保存
		session.setAttribute("inputCom", inputCom);
		
		//サービス形態確認画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/checkType.jsp");
		dispatcher.forward(request, response);
	}

}
