package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Companies;
import model.Employees;
import model.InputEmployee;

public class EmployeesDAO {
	//職員テーブルのDAO
	
	//データベースに使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/KaigoMemo";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	//職員テーブルで検索
	public Employees findEmployees(InputEmployee inputEmp) {
		Employees emp = new Employees();
		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//SELECT文を準備
			String sql = "SELECT COMPANY, FACILITY, TYPE, NAME "
					+ "FROM EMPLOYEES "
					+ "WHERE COMPANY = ? AND FACILITY = ? AND TYPE = ? AND NAME = ? ";
			try(PreparedStatement pStmt = conn.prepareStatement(sql)){
				pStmt.setString(1, inputEmp.getCompanyName());	//会社名
				pStmt.setString(2, inputEmp.getFacilityName());	//施設名
				pStmt.setString(3, inputEmp.getType());			//サービス形態
				pStmt.setString(4, inputEmp.getName());			//職員名
				
				//SELECT文を実行し、社名、施設名、業務形態名、職員名を取得
				try(ResultSet rs = pStmt.executeQuery()){
					
					if(rs.next()) {
						emp.setCompanyName(rs.getString("COMPANY"));
						emp.setFacilityName(rs.getString("FACILITY"));
						emp.setType(rs.getString("TYPE"));
						emp.setName(rs.getString("NAME"));
					}
				}
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
			emp.setErrorMsg("データベースに接続できませんでした");
		}
		return emp;
	}
	
	//職員切り替え（職員一覧を取得）
	public List<Employees> findEmployeesList(Companies com){
		
		//職員一覧リストインスタンスを生成
		List<Employees> empList = new ArrayList<>();
		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//SELECT文を準備
			String sql = "SELECT COMPANY, FACILITY, TYPE, NAME "
					+ "FROM EMPLOYEES "
					+ "WHERE COMPANY = ? AND FACILITY = ? AND TYPE = ? ";
			try(PreparedStatement pStmt = conn.prepareStatement(sql)){
				pStmt.setString(1, com.getCompanyName());
				pStmt.setString(2, com.getFacilityName());
				pStmt.setString(3, com.getType());
				
				//SELECT文を実行し、社名、施設名、業務形態名、職員名を取得
				try(ResultSet rs = pStmt.executeQuery()){
					
					while(rs.next()) {
						Employees emp = new Employees();
						emp.setCompanyName(rs.getString("COMPANY"));	//会社名
						emp.setFacilityName(rs.getString("FACILITY"));	//施設名
						emp.setType(rs.getString("TYPE"));				//サービス形態
						emp.setName(rs.getString("NAME"));				//職員名
						
						//申し送り情報をリスト化
						empList.add(emp);
					}
				}
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return empList;
	}
	
	//入力職員インスタンスをテーブルに追加
	public Employees insertEmployees(InputEmployee inputEmp) {
		
		//職員インスタンスを生成
		Employees emp = new Employees();
		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			//INSERT文を準備
			String sql = "INSERT INTO EMPLOYEES (COMPANY, FACILITY, TYPE, NAME) VALUES (?,?,?,?)";
			try(PreparedStatement pStmt = conn.prepareStatement(sql)){
				pStmt.setString(1,inputEmp.getCompanyName());	//会社名
				pStmt.setString(2, inputEmp.getFacilityName());	//施設名
				pStmt.setString(3, inputEmp.getType());			//サービス形態
				pStmt.setString(4, inputEmp.getName());			//職員名
				
				int result = pStmt.executeUpdate();
				if(result == 1) {
					
					//登録した職員情報を取得
					emp = this.findEmployees(inputEmp);
				} else {
					emp.setErrorMsg("職員情報の取得に失敗しました");
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
}