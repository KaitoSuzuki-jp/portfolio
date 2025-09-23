package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Employees;
import model.MemoPost;
import model.MemoView;
import model.Memos;

//申し送りテーブルを取得するクラス
public class MemosDAO {
	
	//データベースに使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/KaigoMemo";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	//職員インスタンスの職場情報に一致する申し送りリストを返す
	public List<Memos> findMemos(Employees emp) {
		
		//申し送りインスタンスを生成
		List<Memos> memosList = new ArrayList<>();
		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//SELECT文を準備
			String sql = "SELECT ID, DATETIME, TITLE, NAME "
					+ "FROM MEMOS "
					+ "WHERE COMPANY = ? AND FACILITY = ? AND TYPE = ? "
					+ "ORDER BY DATETIME DESC ";
			try(PreparedStatement pStmt = conn.prepareStatement(sql)){
				pStmt.setString(1, emp.getCompanyName());
				pStmt.setString(2, emp.getFacilityName());
				pStmt.setString(3, emp.getType());
				
				
				//SELECT文を実行し、社名、施設名、業務形態名、日時、タイトル、申し送り、記入社員を取得
				try(ResultSet rs = pStmt.executeQuery()){
					
					while(rs.next()) {
						int id = rs.getInt("ID");
						Timestamp ts = rs.getTimestamp("DATETIME");	//投稿日時
						String title = rs.getString("TITLE");		//申し送りタイトル
						String name = rs.getString("NAME");			//記入社員
						
						LocalDateTime dateTime = ts.toLocalDateTime();
						
						//返す用の申し送り一覧情報を準備
						Memos memos = new Memos(id, dateTime, title, name);
						memosList.add(memos);
					}
				}
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return memosList;
	}
	
	//IDに一致する申し送り情報を検索して返す
	public Memos findMemosID(MemoView memoView) {
		
		//申し送りインスタンスを生成
		Memos memos = new Memos();
		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//SELECT文を準備
			String sql = "SELECT ID, COMPANY, FACILITY, TYPE, DATETIME, TITLE, MEMO, NAME "
					+ "FROM MEMOS "
					+ "WHERE ID = ? ";
			try(PreparedStatement pStmt = conn.prepareStatement(sql)){
				pStmt.setInt(1,memoView.getId());
				
				//SELECT文を実行し、社名、施設名、業務形態名、日時、タイトル、申し送り、記入社員を取得
				try(ResultSet rs = pStmt.executeQuery()){
					
					while(rs.next()) {
						memos.setId(rs.getInt("ID"));						//申し送りID
						memos.setCompanyName(rs.getString("COMPANY"));		//会社名
						memos.setFacilityName(rs.getString("FACILITY"));	//施設名
						memos.setType(rs.getString("TYPE"));				//サービス形態
						Timestamp ts = rs.getTimestamp("DATETIME");			//投稿日時
						memos.setTitle(rs.getString("TITLE"));				//申し送りタイトル
						memos.setMemo(rs.getString("MEMO"));				//申し送り内容
						memos.setName(rs.getString("NAME"));				//記入社員
						
						memos.setDatetime(ts.toLocalDateTime());
					}
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			memos.setErrorMsg("申し送り情報の取得に失敗しました");
			return memos;
		}
		return memos;
	}
	
	//入力申し送りインスタンスをデータベースに保存・保存内容を返す
	public Memos insertMemos(MemoPost memoPost) {
		
		//申し送りインスタンスを生成
		Memos memos = new Memos();
		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//INSERT文を準備
			String sql = "INSERT INTO MEMOS (COMPANY, FACILITY, TYPE, DATETIME, TITLE, MEMO, NAME) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?) ";
			try(PreparedStatement pStmt = conn.prepareStatement(sql)){
				pStmt.setString(1, memoPost.getCompanyName());					//会社名
				pStmt.setString(2, memoPost.getFacilityName());					//施設名
				pStmt.setString(3, memoPost.getType());							//サービス形態
				pStmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));	//投稿日時
				pStmt.setString(5, memoPost.getTitle());						//申し送りタイトル
				pStmt.setString(6, memoPost.getMemo());							//申し送り内容
				pStmt.setString(7, memoPost.getName());							//記入社員
				
				int result = pStmt.executeUpdate();
				//追加成功なら追加した確認用申し送り情報を申し送りインスタンスに保存
				if(result == 1) {
					
					memos.setTitle(memoPost.getTitle());
					memos.setMemo(memoPost.getMemo());
					memos.setName(memoPost.getName());
					
				} else {
					memos.setErrorMsg("申し送り情報の投稿に失敗しました");
				}
				return memos;
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
			memos.setErrorMsg("申し送り情報の投稿に失敗しました");
			return memos;
		}
	}
}
