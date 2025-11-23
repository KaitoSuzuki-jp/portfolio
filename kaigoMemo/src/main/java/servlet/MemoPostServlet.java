package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bo.MemoPostLogic;
import model.Employees;
import model.MemoPost;
import model.Memos;
import model.ModelError;


@WebServlet("/MemoPostServlet")
public class MemoPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//メイン画面からの遷移
		
		//申し送り記入画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoPost.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//申し送り記入画面からの遷移
		
		//入力申し送りインスタンスを生成
		MemoPost memoPost = new MemoPost();	
		
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");	//申し送りタイトルを取得
		String memo = request.getParameter("memo");		//申し送り内容を取得
		
		//セッションスコープ
		HttpSession session = request.getSession();
		
		//申し送りの記入チェック
		if(title == null || title.length() == 0 ||memo == null || memo.length() == 0) {
			
			//エラーメッセージの作成
			memoPost.setErrorMsg("申し送りの件名、または本文の取得に失敗しました");
			
			//エラーインスタンスを作成(引数はエラーコード)
			ModelError error = new ModelError("memoPost");
			session.setAttribute("error", error);
			
			//件名と本文が空ならエラー画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		//入力申し送りインスタンスを作成
		Employees emp = (Employees)session.getAttribute("emp");
		memoPost.setCompanyName(emp.getCompanyName());		//会社名
		memoPost.setFacilityName(emp.getFacilityName());	//施設名
		memoPost.setType(emp.getType());					//サービス形態
		memoPost.setTitle(title);							//申し送りタイトル
		memoPost.setMemo(memo);								//申し送り内容
		memoPost.setName(emp.getName());					//記入社員
		
		//入力申し送りインスタンスをセッションスコープに保存
		session.setAttribute("memoPost", memoPost);
		
		//申し送りをデータベースに登録
		MemoPostLogic memoPostLogic = new MemoPostLogic();
		Memos memos = memoPostLogic.execute(memoPost);
		
		//登録した申し送り情報をセッションスコープに保存
		session.setAttribute("memos", memos);
		
		//申し送り確認画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/checkMemoPost.jsp");
		dispatcher.forward(request, response);
	}
}
