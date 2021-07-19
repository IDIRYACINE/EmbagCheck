package idir.embag.Database;

import java.sql.SQLException;

import idir.embag.modules.CheckModel;

public interface DatabaseInterface {
    public void Connect();
    public void Disconnect();
    public void Update(CheckModel checkModel) throws SQLException;
    public void Insert(CheckModel checkModel) throws SQLException;
    public CheckModel[] RequestData(String fields , String values)  throws SQLException;
}
