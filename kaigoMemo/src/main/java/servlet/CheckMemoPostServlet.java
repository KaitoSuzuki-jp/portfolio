package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/CheckMemoPostServlet")
public class CheckMemoPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//申し送り内容確認画面からの遷移
		
		//申し送り一覧画面（MainServlet）ヘリダイレクト
		response.sendRedirect("MainServlet");
		return;	
	}

}
