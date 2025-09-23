package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Companies;
import model.Employees;
import model.MemoPost;
import model.Memos;
import model.ModelError;


@WebServlet("/ErrorServlet")
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//エラー画面からの遷移
		
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		//セッションスコープからエラーインスタンス(エラーコード)を取得
		HttpSession session = request.getSession();
		ModelError error = (ModelError)session.getAttribute("error");
		
		//エラーメッセージを削除
		if("com".equals(error.getErrorCode())) {
			
			//引数（エラーコード）がcomなら会社インスタンスのエラーメッセージを削除
			Companies com = (Companies)session.getAttribute("com");
			com.setErrorMsg(null);
			
		} else if ("emp".equals(error.getErrorCode())) {
			
			//引数（エラーコード）がempなら職員インスタンスのエラーメッセージを削除
			Employees emp = (Employees)session.getAttribute("emp");
			emp.setErrorMsg(null);
			
		} else if ("memoPost".equals(error.getErrorCode())) {
			
			//引数（エラーコード）がmemoPostなら入力申し送りインスタンスのエラーメッセージを削除
			MemoPost memoPost = (MemoPost)session.getAttribute("memoPost");
			memoPost.setErrorMsg(null);
			
		} else if ("memos".equals(error.getErrorCode())) {
			
			//引数（エラーコード）がmemosなら申し送りインスタンスのエラーメッセージを削除
			Memos memos = (Memos)session.getAttribute("memos");
			memos.setErrorMsg(null);
			
		}
		
		error.setErrorCode(null);
		
		if("MainServlet".equals(action)) {
			
			//メイン画面へリダイレクト
			response.sendRedirect("MainServlet");
			return;	
			
		} else if("StartServlet".equals(action)) {
			
			//スタート画面ヘリダイレクト
			response.sendRedirect("StartServlet");
			return;
			
		}
	}

}
