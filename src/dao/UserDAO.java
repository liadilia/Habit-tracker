package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class UserDAO {

	
	public static int save(String name,String password,String email,String address,String city,String contact){
		int status=0;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into users(name,password,email,address,city,contact) values(?,?,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,password);
			ps.setString(3,email);
			ps.setString(4,address);
			ps.setString(5,city);
			ps.setString(6,contact);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int delete(String id){
		int status=0;
		try{
			Connection con=DB.getConnection();
		
			PreparedStatement ps2=con.prepareStatement("call deleteUserWithData(?)");
			ps2.setString(1,id);
			status=ps2.executeUpdate();
		con.close();
			
		}catch(Exception e){System.out.println(e);}
		return status;
	}

	public static boolean validate(String name,String password){
		boolean status=false;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from users where name=? and password like hashpass(?)");
			ps.setString(1,name);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			status=rs.next();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	
	public static int getUserId(String name){
		int id=-1;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("select id from users where name=?");
			ps.setString(1,name);			
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				 id=rs.getInt("id");
			}
			//System.out.println(rs);
		}catch(Exception e){System.out.println(e);}
		return id;
	}

}
