package idir.embag.Utility.Database;

import java.sql.SQLException;
import java.util.ArrayList;

import idir.embag.Models.CheckDataModel.CheckModel;

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

    public ArrayList<CheckModel> loadData() {
        ArrayList<CheckModel> models = null;
        try {
            models = database.RequestData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }

    public  ArrayList<CheckModel> Search(String queryMetaData) {
        ArrayList<CheckModel> models = null;
        try {
            models = database.Search(queryMetaData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models ;
    }
}
