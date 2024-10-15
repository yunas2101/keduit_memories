package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InputServlet")
public class InputServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		System.out.println(writer);
		System.out.println(content);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbID = "servlet";
		String dbPW = "servlet";
		
		//JDBC 데이터 입력코드
		
		String sql = "insert into messages values(messages_seq.nextval, ?, ?, sysdate)";

		try(Connection con = DriverManager.getConnection(url,dbID,dbPW);
				PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setString(1, writer);
			pstat.setString(2, content);
			int result = pstat.executeUpdate();
			System.out.println(result);
			
		}catch(Exception e) {
			e.printStackTrace();
		};
	}
	
	

}
