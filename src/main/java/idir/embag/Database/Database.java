package idir.embag.Database;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import idir.embag.modules.CheckModel;

public class Database implements DatabaseInterface{
    private  String path ;
    private  final String DATABASE_NAME = "Checks.db"; 
    private  final String DATABASE_PATH = "/Data/";
    private final String TABLE_NAME = "Checks";
    private  Connection conn ;

    public Database(){
       
    }

    @Override
    public void Connect() {
        String AbsolutePath = new File("").getAbsolutePath();
        path =  AbsolutePath + DATABASE_PATH + DATABASE_NAME ;   
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:"+path);
            if (conn != null){
                DatabaseMetaData meta = conn.getMetaData();
                CreateTables();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("Connected");
            }    
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    private void CreateTables() throws SQLException{
        String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS Checks (\n"
        + "	id integer PRIMARY KEY,\n"
        + "	receiver text NOT NULL,\n"
        + "	date text NOT NULL,\n"
        + "	location text NOT NULL,\n"
        + "	amount integer NOT NULL,\n"
        + "	status text NOT NULL\n"
        + ");";
        
        Statement qStatement = conn.createStatement() ;
        qStatement.execute(CREATE_TABLE_QUERY);
        
    }

    @Override
    public void Disconnect() {
        
    }

    

    @Override
    public void Update(CheckModel checkModel) throws SQLException{

        String UPDATE_STATUS_QUERY = "UPDATE "+TABLE_NAME+" SET status = ? , "
        + "WHERE id = ?";

        PreparedStatement qStatement = conn.prepareStatement(UPDATE_STATUS_QUERY) ;
        qStatement.setString(1, checkModel.getStatus());
        qStatement.setInt(2, checkModel.getID());
        qStatement.executeUpdate();
    }

    @Override
    public void Insert(CheckModel checkModel) throws SQLException {
        String INSERT_CHECK_QUERY = "INSERT INTO "+TABLE_NAME+"(date,id,receiver,amount,status,location) VALUES(?,?,?,?,?,?)";

        PreparedStatement qStatement = conn.prepareStatement(INSERT_CHECK_QUERY) ;
        qStatement.setString(1,checkModel.getDate());
        qStatement.setInt(2,checkModel.getID());
        qStatement.setString(3,checkModel.getReceiver());
        qStatement.setInt(4,checkModel.getAmount());
        qStatement.setString(5,checkModel.getStatus());
        qStatement.setString(6,checkModel.getLocation());
        qStatement.executeUpdate();

    }

    @Override
    public CheckModel[] RequestData(String fields, String values) throws SQLException{
        String SELECT_CHECK_QUERY = "SELECT * FROM  " + TABLE_NAME + selectQueryFormater(fields);

        PreparedStatement qStatement = conn.prepareStatement(SELECT_CHECK_QUERY) ;

        qStatement.executeQuery();


        return null;
    }    

    private String selectQueryFormater(String fields ){
        //having count(distinct personName) = 2"
        String result = "WHERE ";
        String[] fieldsList = result.split(",");
        int length = fieldsList.length ;

        for (int i = 0 ; i < length ; i++){
            if (i != length-1){
            result += fieldsList[i] + " = ?" + " and ";}
            else {
                result += fieldsList[i] + " = ?"  ;}
            }
        
        return result;
    }
   
   
}
