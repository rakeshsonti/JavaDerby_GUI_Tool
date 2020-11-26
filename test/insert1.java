import java.sql.*;
class insert1
{
public static void main(String gg[])
{
try
{
Class.forName("org.apache.derby.jdbc.ClientDriver");
Connection c=DriverManager.getConnection("jdbc:derby://localhost:1527/db/tmplacements");
Statement s=c.createStatement();
s.executeUpdate("insert into city(name,state_code)values('Shajapur',5)");
s.close();
c.close();
System.out.println("Record inserted");
}
catch(SQLException sqlException)
{
System.out.println(sqlException);
}
catch(ClassNotFoundException cnfe)
{
System.out.println(cnfe);
}
}
}
