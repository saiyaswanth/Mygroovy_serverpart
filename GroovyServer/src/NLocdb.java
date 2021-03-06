

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class NLocdb
 */
@WebServlet("/NLocdb")
public class NLocdb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NLocdb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		Connection connection=null;
		String tname = request.getParameter("tname").toString();
		String uid = request.getParameter("uid").toString();
		//	String tname = "Naddress";
		ResultSet rs=null;
		String grquery = "select * from groovyusers where userid=?";
		String retriveQuery = " select * from "+tname+" where stype= ? or stype = ?";
		try{
		connection=DataBaseConnectionClass.getConnection();
		/*
		String dbquery = "use groovy_mg";
		PreparedStatement ps = connection.prepareStatement(dbquery);
		ps.executeUpdate();
		*/
		PreparedStatement pt = connection.prepareStatement(grquery);
		pt.setString(1, uid);
		ResultSet rss=pt.executeQuery();
		rss.next();
		String gender = rss.getString(5);
		
		PreparedStatement pst = connection.prepareStatement(retriveQuery);
		pst.setString(1, gender);
		String s = "unisex";
		pst.setString(2, s);
		rs=pst.executeQuery();
		PrintWriter writer=response.getWriter();
		JSONArray array=new JSONArray();
		while(rs.next())
		{ 
		String Name=rs.getString(2);
		System.out.println(Name);
		JSONObject object=new JSONObject();
		object.put("Name",Name);
		array.put(object);
		}
		writer.print(array.toString());
		}catch(Exception e)
		{
			System.out.print(e);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
