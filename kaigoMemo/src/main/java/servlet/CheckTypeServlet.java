package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bo.CompaniesLogic;
import model.Companies;
import model.InputCompany;
import model.ModelError;

@WebServlet("/CheckTypeServlet")
public class CheckTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//業務形態「確認」画面からの遷移
		
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String action = (String)request.getParameter("action");
		
		//actionがnullでない場合、対応する各画面へリダイレクト
		if(action != null) {
			if("company".equals(action)) {
				
				//職員選択画面へリダイレクト
				response.sendRedirect("KaigoMemoServlet");
				
			} else if("facility".equals(action)) {
				
				//職員選択画面へリダイレクト
				response.sendRedirect("CompanyServlet");
				
			} else if("type".equals(action)) {
				
				//職員選択画面へリダイレクト
				response.sendRedirect("FacilityServlet");
				
			}
		}
		
		//入力会社インスタンスをデータベースから検索
		HttpSession session = request.getSession();
		InputCompany inputCom = (InputCompany)session.getAttribute("inputCom");
		
		//会社インスタンスを取得・セッションスコープに保存
		CompaniesLogic comLogic = new CompaniesLogic();
		Companies com = comLogic.execute(inputCom);
		session.setAttribute("com", com);
		
		//会社情報がない、または取得できなかった場合エラー画面へフォワード
		if(com == null || com.getErrorMsg() != null) {
			
			//エラーコードを生成してセッションスコープに保存
			ModelError error = new ModelError("com");
			session.setAttribute("error", error);
			
			//エラー画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		//セッションスコープに保存してある入力会社インスタンスを破棄
		session.removeAttribute("inputCom");
		
		//職員選択画面へリダイレクト
		response.sendRedirect("EmployeeServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
