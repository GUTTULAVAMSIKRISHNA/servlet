package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class delete extends HttpServlet {
@Override
protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
		Class.forName("com.mqsql.cj.jdbc.Driver");
		
		Connection connect=DriverManager.getConnection("jdbc.mysql://localhost:3306/krishna", "root", "robinhood");
		
		String sql = "delete from new_table where id=?";
		PreparedStatement pmst=connect.prepareStatement(sql);
		int id=Integer.parseInt(req.getParameter("id")) ;
		pmst.setInt(1, id);
int i=pmst.executeUpdate();
		
		PrintWriter pw= resp.getWriter();
				if(i>0) {
					pw.println("successfully deleted");
				}
				else {
					pw.println("error");
				}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
