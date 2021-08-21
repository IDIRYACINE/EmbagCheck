package idir.embag.Utility.Database;

import java.sql.SQLException;

import idir.embag.Modules.CheckModel;

public class DatabaseAcessHelper {
    private  DatabaseInterface database ; 

    public DatabaseAcessHelper() {
        setUpDatabase();
    }

    private void setUpDatabase(){
        database = new Database();
        database.Connect();
    }

    public void Update(CheckModel checkModel) {
        try {
            database.Update(checkModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(CheckModel checkModel) {
        try {
            database.Insert(checkModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try {
            database.RequestData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
