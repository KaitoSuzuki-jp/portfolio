package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bo.CompanyRegisterLogic;
import model.Companies;
import model.InputCompany;
import model.ModelError;


@WebServlet("/CompanyRegisterServlet")
public class CompanyRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//スタート・会社名入力画面からの遷移
		
		//会社名・施設名　新規登録画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/companyRegister.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//会社名・施設名入力画面からの遷移
		
		//入力会社インスタンス生成
		InputCompany inputCom = new InputCompany();
		
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		inputCom.setCompanyName(request.getParameter("company"));	//会社名
		inputCom.setFacilityName(request.getParameter("facility")); //施設名
		inputCom.setType(request.getParameter("type"));				//サービス形態
		String action = request.getParameter("action");				//action
		
		//セッションスコープを取得
		HttpSession session = request.getSession();	
		
		//actionの値がnullなら確認画面へフォワード
		if("companyCheck".equals(action)) {
			
			//入力会社インスタンスをセッションスコープに保存
			session.setAttribute("inputCom", inputCom);
			
			//会社名・施設名　確認画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/checkCompanyRegister.jsp");
			dispatcher.forward(request, response);
			return;
			
		} else if("companyCheckOK".equals(action)) {
			
			//入力会社インスタンスをCOMPANIES テーブルに登録(追加)
			CompanyRegisterLogic comRegiLogic = new CompanyRegisterLogic();
			Companies com = comRegiLogic.execute(inputCom);
			
			//会社情報がない、または取得できなかった場合エラー画面へフォワード
			if(com == null || com.getErrorMsg() != null) {
				
				//エラーコードを生成
				ModelError error = new ModelError("com");
				session.setAttribute("error",error);
				
				//エラー画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
				dispatcher.forward(request, response);
				return;
			}
			//会社インスタンスをセッションスコープに保存
			session.setAttribute("com", com);
			
			//職員登録画面へリダイレクト
			response.sendRedirect("EmployeeRegisterServlet");
			return;
		} 
		

	}

}
