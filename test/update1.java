import java.sql.*;
public class update1
{
public static void main(String gg[])
{
try
{
Class.forName("org.apache.derby.jdbc.ClientDriver");
Connection c=DriverManager.getConnection("jdbc:derby://localhost:1527/db/tmplacements");
Statement s=c.createStatement();
s.executeUpdate("update city set name='Shujalpur' where  name='Shajapur'");
s.close();
c.close();
System.out.println("Updated");
}
catch(SQLException sqle)
{
System.out.println(sqle);
}
catch(ClassNotFoundException cnfe)
{
System.out.println(cnfe);
}
}
}