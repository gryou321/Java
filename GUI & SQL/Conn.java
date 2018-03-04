import java.sql.*;
import java.util.HashMap;

public class Conn {
	private static String item;
	private static double total;
	private static HashMap<String,Double> itemMap;
	
	public Conn(String name,double sum,HashMap<String,Double> map) {
		item = name;
		total = sum;
		itemMap = map;
	}
	
	public void print() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DB;user=gryou321;password=try064net107;");
			
			//String query = "SELECT SUM(num) FROM dbo.Table1 WHERE status = '入庫'";
			//String query = "SELECT * FROM dbo.Table1";
			String insert = "INSERT INTO dbo.material VALUES('" + item + "'," + total;
			
			for(String key : itemMap.keySet()) {
				insert = insert + "," + String.format("%.2f",itemMap.get(key));
			}
			
			insert = insert + ")";
			System.out.println(insert);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(insert);
			
			/*
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int count = rsmd.getColumnCount();
			
			while(rs.next()) {
	        	//System.out.printf("單號： %6s / 數量： %4d / 狀態： %5s\n",rs.getString(1),rs.getInt(2),rs.getString(3));
	        	//System.out.println(rs.getString(1));
	        	for(int i = 1; i <= count; i++) {
	        		System.out.printf("%-6s : %-10s",rsmd.getColumnName(i),rs.getString(rsmd.getColumnName(i)));
	        	}
	        	
	        	System.out.println();
			}
			*/
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
