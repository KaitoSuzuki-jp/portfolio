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

import bo.MemosLogic;
import model.Employees;
import model.Memos;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//申し送り詳細画面、エラー画面からの遷移
		
		//職員インスタンスをセッションスコープから取得
		HttpSession session = request.getSession();
		Employees emp = (Employees)session.getAttribute("emp");
		
		//会社インスタンスに一致する申し送り一覧情報を取得
		MemosLogic memosLogic = new MemosLogic();
		List<Memos> memosList = memosLogic.execute(emp);
			
		session.setAttribute("memosList", memosList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
		
		/*取得成功なら申し送り一覧情報をセッションスコープに保存して申し送り一覧画面へフォワード
		if(!memosList.isEmpty()) {

		} else {
			Error error = new Error("memosList");
			session.setAttribute("error", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//職員名画面または職員登録確認画面または職員切り替え画面からの遷移
		
		//職員インスタンスをセッションスコープから取得
		HttpSession session = request.getSession();
		Employees emp = (Employees)session.getAttribute("emp");
		
		//会社インスタンスに一致する申し送り一覧情報を取得
		MemosLogic memosLogic = new MemosLogic();
		List<Memos> memosList = memosLogic.execute(emp);
		
		session.setAttribute("memosList", memosList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
			
			
		/*取得成功なら申し送り一覧情報をセッションスコープに保存して申し送り一覧画面へフォワード
		if(memosList != null) {
			
		} else {
			Error error = new Error("memosList");
			session.setAttribute("error", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
		*/
	}

}
