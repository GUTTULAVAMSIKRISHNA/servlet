package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class servlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connect =DriverManager.getConnection("jdbc:mysql://localhost:3306/krishna", "root", "robinhood");
		
		String sql = "insert into new_table (name,email,branch) values(?,?,?)";
		PreparedStatement pmst = connect.prepareStatement(sql);
		
		String name= req.getParameter("name");
		String email= req.getParameter("email");
		String branch= req.getParameter("branch");
		
		pmst.setString(1, name);
		pmst.setString(2, email);
		pmst.setString(3, branch);
		
		int i=pmst.executeUpdate();
		
		PrintWriter pw= resp.getWriter();
				if(i>0) {
					pw.println("successfully inserted");
				}
				else {
					pw.println("error");
				}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
