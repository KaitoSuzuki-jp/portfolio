package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Companies;
import model.InputCompany;

public class CompaniesDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/KaigoMemo";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	//職場インスタンスを検索して返す
	public Companies findCompanies(InputCompany inputCom) {
		Companies com = new Companies();
		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//SELECT文を準備
			String sql = "SELECT COMPANY,FACILITY,TYPE FROM COMPANIES "
					+ "WHERE COMPANY = ? AND FACILITY = ? AND TYPE = ? ";
			try(PreparedStatement pStmt = conn.prepareStatement(sql)){
				pStmt.setString(1, inputCom.getCompanyName());
				pStmt.setString(2, inputCom.getFacilityName());
				pStmt.setString(3, inputCom.getType());
				
				//SELECT文を実行し、社名、施設名、業務形態名を取得
				try(ResultSet rs = pStmt.executeQuery()){
					
					if(rs.next()) {
						com.setCompanyName(rs.getString("COMPANY"));
						com.setFacilityName(rs.getString("FACILITY"));
						com.setType(rs.getString("TYPE"));
					} else {
						return null;
					}
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			com.setErrorMsg("データベースに接続できませんでした");
		}
		return com;
	}
	
	//入力会社インスタンスをテーブルに追加
	public Companies insertCompanies(InputCompany inputCom) {
		
		//会社インスタンスを生成
		Companies com = new Companies();
		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//INSERT文を準備
			String sql = "INSERT INTO COMPANIES (COMPANY, FACILITY, TYPE) VALUES (?,?,?)";
			try(PreparedStatement pStmt = conn.prepareStatement(sql)){
				
				pStmt.setString(1,inputCom.getCompanyName());	//会社名
				pStmt.setString(2, inputCom.getFacilityName());	//施設名
				pStmt.setString(3, inputCom.getType());			//サービス形態
				
				int result = pStmt.executeUpdate();
				
				if(result == 1) {
					System.out.println("新しい会社・施設情報が追加されました");	
					
					//登録された会社情報を会社インスタンスに設定
					com = this.findCompanies(inputCom);	    
				} else {
					com.setErrorMsg("施設情報の登録に失敗しました");
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			com.setErrorMsg("施設情報の登録に失敗しました");
		}
		return com;
	}
	
	//指定された施設情報を削除する
	public void deleteCompanies(Companies com) {
		
		/*
		削除機能未実装のため、サーブレットで処理する可能性あり
		
		//comがnullでないかを調べる
		if("null".equals(com)) {
			System.out.println("施設情報の削除に失敗しました");
		}
		*/
		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//INSERT文を準備
			String sql = "DELETE FROM COMPANIES WHERE COMPANY = ? AND FACILITY = ? AND TYPE = ?";
			try(PreparedStatement pStmt = conn.prepareStatement(sql)){
				
				pStmt.setString(1,com.getCompanyName());	//会社名
				pStmt.setString(2, com.getFacilityName());	//施設名
				pStmt.setString(3, com.getType());			//サービス形態
				
				int result = pStmt.executeUpdate();
				if(result == 1) {
					System.out.println("施設情報が削除されました");	
				} else {
					System.out.println("施設情報の削除に失敗しました");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}