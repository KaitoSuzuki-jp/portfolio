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

@WebServlet("/FacilityServlet")
public class FacilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//施設名入力画面からの遷移
		
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String facilityName = request.getParameter("facility");	//施設名を取得
		
		//セッションスコープから会社インスタンスを取得
		HttpSession session = request.getSession();
		InputCompany inputCom = (InputCompany)session.getAttribute("inputCom");
		
		//施設名を会社インスタンスに保存
		inputCom.setFacilityName(facilityName);
		
		//入力会社インスタンスをセッションスコープに保存
		session.setAttribute("inputCom",inputCom);
		
		//サービス形態選択画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/type.jsp");
		dispatcher.forward(request, response);
	}

}
