package idir.embag.Utility.Database;

import java.sql.SQLException;
import java.util.ArrayList;

import idir.embag.Modules.CheckModel;

public interface DatabaseInterface {
    public void Connect();
    public void Disconnect();
    public void Update(CheckModel checkModel) throws SQLException;
    public ArrayList<CheckModel> Search(String qString) throws SQLException;
    public void Insert(CheckModel checkModel) throws SQLException;
    public ArrayList<CheckModel> RequestData()  throws SQLException;
    
}
