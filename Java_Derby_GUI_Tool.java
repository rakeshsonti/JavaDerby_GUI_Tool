import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.sql.*;
//----------------------------------------------------------------------------------------------------------------------------------------
class MainMenu extends JFrame implements ActionListener
{
JScrollPane scrollresult,scrollerror,scrollList;
Connection c=null;
ConnectionFrame cf;
public int close=0;

private  JList<String> tableList;
boolean connectList=false;
private JLabel listLabel;
private JButton accountDTOButton,accountDAOButton,accountInterfaceButton;
private JTextField queryTextField;
private JTextArea resultArea,errorArea;
private JLabel sqlQueryLabel,resultLabel,errorLabel;
private JButton runButton;
private JMenuBar mb;
private JMenu menu,radSetting;
private JMenuItem connectMenuItem,disconnectMenuItem,exitMenuItem;
private JMenuItem packageMenuItem,pathMenuItem;
private JTextField packageTextField,pathTextField;
String string1;
private static  String tableNames[]={"  "};
//---------------------------------------------------------------------------------
class getTableConnection
{
private String [] tableNames;
getTableConnection()
{
getTable();
}
public  void getTable()
{
int i=0;
int rec=0;
try
{
errorArea.setText("  ");
c=cf.getConnection();
ResultSet rs = null;
ResultSet r=null;
DatabaseMetaData metaData = c.getMetaData();
rs = metaData.getTables(null, null, null, new String[]{"TABLE"});
r = metaData.getTables(null, null, null, new String[]{"TABLE"});
while (r.next())
{
rec++;
}
tableList.setModel(new DefaultListModel());

DefaultListModel model = (DefaultListModel)tableList.getModel();
while (rs.next())
{
String tableName = rs.getString("TABLE_NAME");
model.addElement(tableName);
}
tableList.setBackground(Color.YELLOW);
tableList.setForeground(Color.BLUE);
tableList.setModel(model);
r.close();
rs.close();
c.close();
}
catch(SQLException sqle)
{

errorArea.setText(sqle.getMessage());
}
}//end get Table
}//class getTableConnection
//-----------------------------------------------------------------------------------
MainMenu()
{
setTitle("RDBMS");
ImageIcon appIcon=new ImageIcon("C:/java715amtts/bankapp/images/bank.png");
setIconImage(appIcon.getImage());
Font font6=new Font("Verdana",Font.BOLD,20);
Font font7=new Font("Verdana",Font.PLAIN,17);
sqlQueryLabel=new JLabel("SQL Query");
sqlQueryLabel.setBounds(20,85,200,50);
sqlQueryLabel.setFont(font6);
sqlQueryLabel.setForeground(Color.BLUE);
add(sqlQueryLabel);
queryTextField=new JTextField(30);
queryTextField.setBounds(200,85,600,50);
queryTextField.setFont(font7);
queryTextField.setForeground(Color.BLACK);
add(queryTextField);
runButton=new JButton("run");
runButton.setBounds(800,85,95,50);
runButton.setFont(font6);
runButton.setForeground(Color.BLUE);
add(runButton);
runButton.addActionListener(this);
//------------------------------------------------------------------
listLabel=new JLabel("List");
listLabel.setBounds(1050,150,175,50);
listLabel.setFont(new Font("Verdana",Font.BOLD,30));
listLabel.setForeground(Color.BLUE);
add(listLabel);
tableList=new JList<String>(tableNames);
tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
tableList.setBackground(Color.YELLOW);
tableList.setForeground(Color.BLUE);
tableList.setFont(font6);
scrollList=new JScrollPane(tableList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
scrollList.setBounds(1000,200,170,300);
add(scrollList);
accountInterfaceButton=new JButton("Interface Button");
accountInterfaceButton.setFont(new Font("Verdana",Font.BOLD,15));
accountInterfaceButton.setBounds(1000,520,175,50);
add(accountInterfaceButton);
accountInterfaceButton.addActionListener(this);
accountDTOButton=new JButton("DTO Button");
accountDTOButton.setFont(font6);
accountDTOButton.setBounds(1000,595,175,50);
add(accountDTOButton);
accountDTOButton.addActionListener(this);
accountDAOButton=new JButton("DAO Button");
accountDAOButton.setFont(font6);
accountDAOButton.setBounds(1000,660,175,50);
add(accountDAOButton);
accountDAOButton.addActionListener(this);
tableList.setEnabled(false);
scrollList.setEnabled(false);
accountDAOButton.setEnabled(false);
accountInterfaceButton.setEnabled(false);
accountDTOButton.setEnabled(false);
//---------------------------------------------------------
resultLabel=new JLabel("Result");
resultLabel.setBounds(20,210,200,50);
resultLabel.setFont(font6);
resultLabel.setForeground(Color.BLUE);
add(resultLabel);
resultArea=new JTextArea();
resultArea.setFont(font7);
resultArea.setForeground(Color.WHITE);
resultArea.setBackground(Color.BLACK);
resultArea.setEditable(false);
errorLabel=new JLabel("Error");
errorLabel.setBounds(20,540,200,50);
errorLabel.setFont(font6);
errorLabel.setForeground(Color.BLUE);
add(errorLabel);
errorArea=new JTextArea();
errorArea.setFont(font7);
errorArea.setForeground(Color.RED);
errorArea.setBackground(Color.WHITE);
errorArea.setEditable(false);
errorArea.setLineWrap(true);
queryTextField.setEnabled(false);
runButton.setEnabled(false);
resultArea.setEnabled(false);
errorArea.setEnabled(false);
Font font2=new Font("Verdana",Font.BOLD,16);
Font font3=new Font("Verdana",Font.PLAIN,20);
connectMenuItem=new JMenuItem("Connect",new ImageIcon("c:/image/connection.png"));
connectMenuItem.setFont(font3);
connectMenuItem.setForeground(Color.GREEN);
connectMenuItem.addActionListener(this);
disconnectMenuItem=new JMenuItem("Disconnect",new ImageIcon("c:/image/disconnect.png"));
disconnectMenuItem.setFont(font3);
disconnectMenuItem.addActionListener(this);
exitMenuItem=new JMenuItem("Exit",new ImageIcon("c:/image/exit.png"));
exitMenuItem.setFont(font3);
exitMenuItem.setForeground(Color.RED);
exitMenuItem.addActionListener(this);
menu=new JMenu("Menu");
menu.setFont(font2);
menu.add(connectMenuItem);
menu.add(disconnectMenuItem);
menu.add(exitMenuItem);
mb=new JMenuBar();
mb.setFont(font2);
mb.add(menu);
this.setJMenuBar(mb);
radSetting=new JMenu("RadSetting");
radSetting.setFont(font2);
packageMenuItem=new JMenuItem("Package");
packageMenuItem.setFont(font3);
pathMenuItem=new JMenuItem("path");
pathMenuItem.setFont(font3);
radSetting.add(packageMenuItem);
radSetting.add(pathMenuItem);
mb.add(radSetting);
disconnectMenuItem.setEnabled(false);
setDefaultCloseOperation(EXIT_ON_CLOSE);
add(queryTextField);
setLocation(10,10);
setSize(1000,700);
scrollerror=new JScrollPane(errorArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
scrollerror.setBounds(200,540,700,175);
add(scrollerror);
scrollresult=new JScrollPane(resultArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
scrollresult.setBounds(200,210,700,300);
 add(scrollresult);
setLayout(null);
setVisible(true);
tableList.addListSelectionListener( new javax.swing.event.ListSelectionListener()
 {
@Override
public void valueChanged(ListSelectionEvent event)
{
if(!event.getValueIsAdjusting())
{
try
{
String tableName=null;
if(close==1)
{
c.close();
}
else
{
c=cf.getConnection();
}
Statement s=c.createStatement();
ResultSet rs;
tableName=tableList.getSelectedValue();
string1="select name from "+tableName;
rs=s.executeQuery(string1);
ResultSetMetaData rsmd=rs.getMetaData();
int colCount=rsmd.getColumnCount();
int i=1;
resultArea.setText("  ");
while(i<=colCount)
{
resultArea.append(rsmd.getColumnName(i)+"\t");
i++;
}
resultArea.append("  "+"\n");
resultArea.setCaretPosition(resultArea.getDocument().getLength());
int k;
int j;
String str=null;
while(rs.next())
{
j=1;
while(j<=colCount)
{
k=rsmd.getColumnType(j);
str=rsmd.getColumnName(j);
if(k==1)
{
resultArea.append(rs.getString(str)+"\t");
j++;
}
if(k==4)
{
resultArea.append(rs.getInt(str)+"\t");
j++;
}
}//while
resultArea.append("\n");
}//next loop
queryTextField.setText("   ");
errorArea.setText("  ");
//tableList.clearSelection();
rs.close();
s.close();
c.close();
}
catch(SQLException sqle)
{
errorArea.setText(sqle.getMessage());
}
catch(NullPointerException npe)
{
errorArea.setText(npe.getMessage());
}
}//valueads
}
});
}//constructor end here
public void actionPerformed(ActionEvent ev)
{
//@
if(ev.getSource()==accountDTOButton)
{
new AccountDTOGenerator();
resultArea.setText("  ");
errorArea.setText("  ");
queryTextField.setText("  ");
tableList.clearSelection();
accountDTOButton.requestFocus();
}
if(ev.getSource()==accountDAOButton)
{
resultArea.setText("  ");
errorArea.setText("  ");
queryTextField.setText("  ");
tableList.clearSelection();
accountDAOButton.requestFocus();
resultArea.setText("accountDAOButton Clicked");
}
if(ev.getSource()==accountInterfaceButton)
{
resultArea.setText("  ");
errorArea.setText("  ");
queryTextField.setText("  ");
tableList.clearSelection();
accountInterfaceButton.requestFocus();
resultArea.setText("accountInterfaceButton Clicked");
}
if(ev.getSource()==connectMenuItem)
{
close=0;
cf=new ConnectionFrame();
}
if(ev.getSource()==disconnectMenuItem)
{
try
{
close=1;
c.close();
connectMenuItem.setEnabled(true);
disconnectMenuItem.setEnabled(false);
runButton.setEnabled(false);
resultArea.setEnabled(false);
errorArea.setEnabled(false);
queryTextField.setEnabled(false);
resultArea.setText("  ");
errorArea.setText("  ");
queryTextField.setText("  ");
tableList.setEnabled(false);
tableList.clearSelection();
tableList.setVisible(false);
accountDAOButton.setEnabled(false);
accountInterfaceButton.setEnabled(false);
accountDTOButton.setEnabled(false);
}
catch(SQLException sqle)
{
errorArea.setText(sqle.getMessage());
}
catch(NullPointerException npe)
{
errorArea.setText(npe.getMessage());
}
}
if(ev.getSource()==exitMenuItem)
{
close=0;
System.exit(0);
}
//-------------------------------------------------------------------------------------------------------------------
if(ev.getSource()==runButton)
{
try
{
resultArea.setText("  ");
tableList.clearSelection();
errorArea.setText("  ");
if(close==1)
{
c.close();
}
else
{
c=cf.getConnection();
}
string1=queryTextField.getText().trim();
Statement s=c.createStatement();
char character;
character=string1.charAt(0);
if(character=='s' )
{
ResultSet rs=s.executeQuery(string1);
ResultSetMetaData rsmd=rs.getMetaData();
int colCount=rsmd.getColumnCount();
int i=1;
while(i<=colCount)
{
resultArea.append(rsmd.getColumnName(i)+"\t");
i++;
}
resultArea.append("  "+"\n");
resultArea.setCaretPosition(resultArea.getDocument().getLength());
int k;
int j=1;
String str=null;
while(rs.next())
{
j=1;
while(j<=colCount)
{
k=rsmd.getColumnType(j);
str=rsmd.getColumnName(j);
if(k==1)
{
resultArea.append(rs.getString(str)+"\t");
j++;
}
if(k==4)
{
resultArea.append(rs.getInt(str)+"\t");
j++;
}
}//while
resultArea.append("\n");
}//next loop
rs.close();
}
else
{
s.executeUpdate(string1);
resultArea.setText("  ");
}
errorArea.setText("  ");
s.close();
c.close();
queryTextField.requestFocus();
JOptionPane.showMessageDialog(null,"SQL Statement Fired");
}
catch(SQLException sqle)
{
errorArea.setText(sqle.getMessage());
resultArea.setText("  ");
}
catch(NullPointerException npe)
{
errorArea.setText(npe.getMessage());
resultArea.setText("  ");
}
catch(StringIndexOutOfBoundsException siobe)
{
errorArea.append("Please provide inpute\n");
errorArea.append(siobe.getMessage());
resultArea.setText("  ");
}
queryTextField.setText("  ");
}//IF
}//end action perfomed
//------------------------------------------------------------------------------------------------------------------
//#
class AccountDTOGenerator 
{ 

class Column
{ 
private String name; 
private String dataType; 
public void setName(String name) 
{ 
this.name=name; 
} 
public String getName() 
{ 
return this.name; 
} 
public void setDataType(String dataType) 
{ 
this.dataType=dataType; 
} 
public String getDataType() 
{ 
return this.dataType; 
} 
} 
class Table 
{ 
private String name; 
private LinkedList<Column> columns=new LinkedList<>(); 
public void setName(String name) 
{ 
this.name=name; 
} 
public String getName() 
{ 
return this.name; 
} 
public void setColumns(LinkedList<Column> columns) 
{ 
this.columns=columns; 
} 
public LinkedList<Column> getColumns() 
{ 
return this.columns; 
} 
public void addColumn(Column column) 
{ 
columns.add(column); 
} 
} 

AccountDTOGenerator()
{ 
// some dummy code 
Column column1=new Column(); 
column1.setName("roll_number"); 
column1.setDataType("int"); 
Column column2=new Column(); 
column2.setName("name"); 
column2.setDataType("char"); 
Column column3=new Column(); 
column3.setName("gender"); 
column3.setDataType("char"); 
Table table=new Table(); 
table.setName("student"); 
table.addColumn(column1); 
table.addColumn(column2); 
table.addColumn(column3); 
// 
try 
{ 
String bpn="com.thinking.machines.studentApp.dl"; 
String pth="c:\\project\\studentApp\\dl\\src"; 
String a=bpn.replaceAll("\\.","\\\\"); 
String fullPath=pth+"\\"+a; 
File file=new File(fullPath); 
if(file.exists()==false) 
{ 
file.mkdirs(); 
} 
// assume that we are writing this code in actionPerformed as discussed in the classroom session 
// and the table pointer contains the address of the object  
String className=table.getName(); 
/* convert the first char to upperCase 
*/ 
String propertyName; 
String propertyType; 
String javaType; 
className=className.substring(0,1).toUpperCase()+className.substring(1); 
/* 
more logic to remove _ and the char next to underscore should be uppercased eg.   
abc_pqr  -->  Abc_pqr -->  AbcPqr 
*/ 
String fileName=fullPath+"\\"+className+".java"; 
//System.out.println(fileName); 
File javaFile=new File(fileName); 
if(javaFile.exists()) javaFile.delete(); 
RandomAccessFile randomAccessFile=new RandomAccessFile(javaFile,"rw"); 
randomAccessFile.writeBytes("package "+bpn+".dl.dto;\r\n"); 
randomAccessFile.writeBytes("public class  "+className+" implements java.io.Serializable\r\n"); 
randomAccessFile.writeBytes("{\r\n");
LinkedList<Column> cc=table.getColumns(); 
for(Column c:cc) 
{ 
propertyName=c.getName(); 
propertyType=c.getDataType(); 
// some if conditions to decide 
javaType=" "; 
if(propertyType.equals("int")) javaType="int"; 
if(propertyType.equals("char")) javaType="String"; 
randomAccessFile.writeBytes("private "+javaType+" "+propertyName+";\r\n"); 
} 
// code to generate default constructor 
for(Column c:cc) 
{ 
propertyName=c.getName(); 
// some  
propertyType=c.getDataType(); 
// some if conditions to decide 
javaType=""; 
if(propertyType.equals("int")) javaType="int"; 
if(propertyType.equals("char")) javaType="String"; 
randomAccessFile.writeBytes("public void set"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1)+"("+javaType+" "+propertyName+")\r\n"); randomAccessFile.writeBytes("{\r\n"); 
randomAccessFile.writeBytes("this."+propertyName+"="+propertyName+";\r\n"); 
randomAccessFile.writeBytes("}\r\n"); 
// more code to generate getter method 
} 
 randomAccessFile.writeBytes("}\r\n"); 
randomAccessFile.close(); 
JOptionPane.showMessageDialog(null,"Account DTO Generated");
}
catch(Exception e) 
{ 
 JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);

} 
}//constructor end 
}//end DTOGenerator class










//-------------------------------inner Class First-----------------------------------------------
public  class ConnectionFrame extends JFrame implements ActionListener 
{
Connection cc=null;
private JLabel serverLabel,portLabel,databaseNameLabel ,label1 ;
private JButton connectionButton,quitButton;
private JTextField serverTextField,databaseTextField,portTextField;
private String string;
private boolean foundError=false;
private boolean quitInvoked=false;
ConnectionFrame()
{
Font font=new Font("Verdana",Font.BOLD,25);
Font font1=new Font("Verdana",Font.PLAIN,20);
setTitle("Connection Module");
serverLabel=new JLabel("Server");
serverLabel.setFont(font);
serverTextField=new JTextField(20);
serverTextField.setFont(font1);
portLabel=new JLabel("Port");
portLabel.setFont(font);
portTextField=new JTextField(20);
portTextField.setFont(font1);
databaseNameLabel=new JLabel("Database Name");
databaseNameLabel.setFont(font);
databaseTextField=new JTextField(50);
databaseTextField.setFont(font1);
connectionButton=new JButton("Connect");
connectionButton.setFont(font);
connectionButton.setForeground(Color.GREEN);
connectionButton.addActionListener(this);
quitButton=new JButton("Quit");
quitButton.setFont(font);
quitButton.setForeground(Color.RED);
quitButton.addActionListener(this);
label1=new JLabel(" ");
JPanel p1=new JPanel();
p1.setLayout(new GridLayout(3,2));
p1.add(serverLabel);
p1.add(serverTextField);
p1.add(portLabel);
p1.add(portTextField);
p1.add(databaseNameLabel);
p1.add(databaseTextField);
JPanel p2=new JPanel();
p2.setLayout(new GridLayout(3,1));
p2.add(label1);
p2.add(connectionButton);
p2.add(quitButton);
setLayout(new BorderLayout());
add(p1,BorderLayout.NORTH);
add(p2,BorderLayout.SOUTH);
setBounds(10,10,500,400);
setUndecorated(true);
setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
connectMenuItem.setEnabled(false);
setVisible(true);
}//constructor end here
public  Connection getConnection() 
{
String serverName,databaseName;
int portNumber;
Connection c=null;
try 
{
serverName=String.valueOf (serverTextField.getText().trim());
portNumber=Integer.parseInt(portTextField.getText().trim());
databaseName=String.valueOf(databaseTextField.getText().trim());
string="jdbc:derby://"+serverName+":"+portNumber+"/db/"+databaseName+"";
Class.forName("org.apache.derby.jdbc.ClientDriver");
 c=DriverManager.getConnection(string);
return c;
}catch(SQLException sql)
{
foundError=true;
JOptionPane.showMessageDialog(null,sql.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
}
catch(ClassNotFoundException cnfe)
{
  foundError=true;
JOptionPane.showMessageDialog(null,cnfe.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
}		
catch(NumberFormatException nfe)
{
foundError=true;
JOptionPane.showMessageDialog(null,"Please provide valid input","ERROR",JOptionPane.ERROR_MESSAGE);
}
return c;
}//end get Connection 
public void actionPerformed(ActionEvent ev)
{
if(ev.getSource()==quitButton)
{
quitInvoked=true;
connectMenuItem.setEnabled(true);
setVisible(false);
}
if(quitInvoked==false)
{
if(foundError==false)
{
if(ev.getSource()==connectionButton)
{
cc=getConnection();
if(cc!=null)
{
disconnectMenuItem.setEnabled(true);
connectMenuItem.setEnabled(false);
queryTextField.setEnabled(true);
resultArea.setEnabled(true);
errorArea.setEnabled(true);
runButton.setEnabled(true);
tableList.setVisible(true);
tableList.setEnabled(true);
scrollList.setEnabled(true);
accountDAOButton.setEnabled(true);
accountInterfaceButton.setEnabled(true);
accountDTOButton.setEnabled(true);
errorArea.setText("  ");
JOptionPane.showMessageDialog(null,"Connected");
new getTableConnection();
errorArea.setText("  ");
queryTextField.requestFocus();
setVisible(false);
}
foundError=false;
}//if end
}//if end
}// if upper vale
}  //end actionPerformed....
}//ConnectionFrame class end
}//Main class end here
class Java_Derby_GUI_Tool
{
public static void main(String gg[])
{
MainMenu m=new MainMenu();
}
}
