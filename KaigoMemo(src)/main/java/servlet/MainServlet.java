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
		
		//職員インスタンスの会社情報に一致する申し送り一覧情報をデータベースから取得（引数に職員インスタンス）
		MemosLogic memosLogic = new MemosLogic();
		List<Memos> memosList = memosLogic.execute(emp);
		
		//申し送りリストをセッションスコープに保存
		session.setAttribute("memosList", memosList);
		
		//メイン画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//職員名登録画面または職員登録確認画面または職員切り替え画面からの遷移
		
		//職員インスタンスをセッションスコープから取得
		HttpSession session = request.getSession();
		Employees emp = (Employees)session.getAttribute("emp");
		
		//職員インスタンスの会社情報に一致する申し送り一覧情報をデータベースから取得（引数に職員インスタンス）
		MemosLogic memosLogic = new MemosLogic();
		List<Memos> memosList = memosLogic.execute(emp);
		
		//申し送りリストをセッションスコープに保存
		session.setAttribute("memosList", memosList);
		
		//メイン画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
		
	}

}
