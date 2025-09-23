package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bo.MemoViewLogic;
import model.MemoView;
import model.Memos;
import model.ModelError;

@WebServlet("/MemoViewServlet")
public class MemoViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//申し送り一覧画面(main.jsp)からの遷移
		
		//リクエストパラメータ(申し送りのID)を取得
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));	//ID
		
		//申し送り詳細取得用インスタンスの生成
		MemoView memoView = new MemoView(id);
		
		//申し送り情報をデータベースから取得
		MemoViewLogic memoViewLogic = new MemoViewLogic();
		Memos memos = memoViewLogic.execute(memoView);
		
		//エラーメッセージが！NULLならエラー画面へフォワード
		if(memos == null || memos.getErrorMsg() != null) {
			
			//エラーインスタンスを作成(引数はエラーコード)
			ModelError error = new ModelError("memos");
			HttpSession session = request.getSession();
			
			//エラーインスタンスをセッションスコープに保存
			session.setAttribute("error", error);
			
			//エラー画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
		
		//申し送り詳細画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoView.jsp");
		dispatcher.forward(request, response);
	}
}
