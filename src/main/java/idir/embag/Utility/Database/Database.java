package idir.embag.Utility.Database;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import idir.embag.Models.CheckDataModel.CheckModel;
import idir.embag.Models.CheckDataModel.CheckStatus;

public class Database implements DatabaseInterface{
    private  String path ;
    private  final String DATABASE_NAME = "Checks.db"; 
    private  final String DATABASE_PATH = "Data";
    private final String TABLE_NAME = "Checks";
    private  Connection conn ;

    public Database(){
       
    }

    @Override
    public void Connect() {
        String AbsolutePath = new File("").getAbsolutePath();
        File directory = new File(DATABASE_PATH);
        if (! directory.exists()){
            directory.mkdir();
        }
        path =  AbsolutePath + "/" +DATABASE_PATH  +"/"+ DATABASE_NAME ;   
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:"+path);
            if (conn != null){
                conn.getMetaData();
                CreateTables();
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
        + "	amount REAL NOT NULL,\n"
        + "	status text NOT NULL\n"
        + ");";
        
        Statement qStatement = conn.createStatement() ;
        qStatement.execute(CREATE_TABLE_QUERY);
        
    }

    @Override
    public void Disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update(CheckModel checkModel) throws SQLException{
        String UPDATE_STATUS_QUERY = "UPDATE "+TABLE_NAME+" SET status = ?  "
        + "WHERE id = ? ;";

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
        qStatement.setDouble(4,checkModel.getAmount());
        qStatement.setString(5,checkModel.getStatus());
        qStatement.setString(6,checkModel.getLocation());
        qStatement.executeUpdate();

    }

    @Override

    public ArrayList<CheckModel> RequestData() throws SQLException{
        String SELECT_CHECK_QUERY = "SELECT * FROM  " + TABLE_NAME ;
        
        PreparedStatement qStatement = conn.prepareStatement(SELECT_CHECK_QUERY) ;
        ResultSet resultSet =  qStatement.executeQuery();
     
      
        return IterateQueryToCheckModel(resultSet, 100);
    }    

    private CheckModel QueryToCheckModel(ResultSet rSet) throws SQLException{
            Integer ID = rSet.getInt("id");
            BigDecimal AMOUNT = BigDecimal.valueOf(rSet.getDouble("amount"));
            String RECEIVER = rSet.getString("receiver");
            String TDATE = rSet.getString("date");
            String LOCATION = rSet.getString("location");
            CheckStatus STATUS = CheckStatus.valueOf(rSet.getString("status"));
    
        return new CheckModel(RECEIVER, TDATE, AMOUNT, ID, STATUS, LOCATION);
    }

    private String[][] QueryToExcelData(ResultSet rSet) throws SQLException{
        String[][] result = new String[6][1];
        String[] stringSets = {"receiver" , "date" , "location" ,"status" };
        for (int i = 0 ; i < stringSets.length ; i++){
            result[i][0] = rSet.getString(stringSets[i]);
        }
        result[4][0] = String.valueOf(rSet.getInt("id"));
        result[5][0] = String.valueOf(rSet.getDouble("amount"));
        
        return result;
    }

    @Override
    public ArrayList<CheckModel> Search(String rawQueryString) throws SQLException {
        String queryString = "SELECT * FROM " + TABLE_NAME + " WHERE ";
        PreparedStatement statement = SearchQuery.filterRawQuery(rawQueryString,queryString , conn);
        ResultSet resultSet =  statement.executeQuery();
        return IterateQueryToCheckModel(resultSet, 1000);
    }

    private  ArrayList<CheckModel> IterateQueryToCheckModel(ResultSet resultSet , int MAX_ITEMS_PER_QUERY ) throws SQLException{
        ArrayList<CheckModel> models = new ArrayList<CheckModel>();
        int counter = 0 ;
        while (resultSet.next() && counter < MAX_ITEMS_PER_QUERY){
            models.add(QueryToCheckModel(resultSet));
        }
        return models;

    }
   
   
}
