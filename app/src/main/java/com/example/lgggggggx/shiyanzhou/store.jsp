<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page language="java"%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
<%
	String type=request.getParameter("type");

	
	String driverName = "com.mysql.jdbc.Driver";
	String userName = "root";
	String userPasswd = "zwy1738249401";
	String dbName = "nfood";
	String tableName = "store";
	String url = "jdbc:mysql://localhost/" + dbName + "?user="
			+ userName + "&password=" + userPasswd;
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection connection = DriverManager.getConnection(url);
	Statement statement = connection.createStatement();

	String sql = "SELECT *	FROM "
			+ tableName
			+ " WHERE type	='"+type+"'";

	ResultSet rs = statement.executeQuery(sql);
	ResultSetMetaData rmeta = rs.getMetaData();
	int numColumns = rmeta.getColumnCount();
	while (rs.next())
    	{
        	for (int i = 1; i <= 2; i++) {
        		String temp = rs.getString(i);//getstring  存在问题

        		//回去尝试重新建表尝试
        		out.print(temp + "$");//以?作为间隔符
        	}
        }

	rs.close();
	statement.close();
	connection.close();
%>