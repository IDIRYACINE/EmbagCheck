package idir.embag.Utility.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import idir.embag.App;
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
        App.executorService.submit(new Runnable(){
            @Override
            public void run() {
                try {
                    database.Update(checkModel);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void add(CheckModel checkModel) {
        App.executorService.submit(new Runnable(){
            @Override
            public void run() {
                try {
                    database.Insert(checkModel);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ArrayList<CheckModel> loadData() {
        Future<ArrayList<CheckModel>> future  = App.executorService.submit(new Callable<ArrayList<CheckModel>>(){
            @Override
            public ArrayList<CheckModel> call() throws Exception {
                ArrayList<CheckModel> models = null;
                try {
                    models = database.RequestData();
                } catch (SQLException e) {
                    e.printStackTrace();
                }        
                return models;
            }
        });

        ArrayList<CheckModel> models = null;
        try {
            models = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return models;

    }

    public  ArrayList<CheckModel> Search(String queryMetaData) {
        Future<ArrayList<CheckModel>> future  = App.executorService.submit(new Callable<ArrayList<CheckModel>>(){
            @Override
            public ArrayList<CheckModel> call() throws Exception {
                ArrayList<CheckModel> models = null;
                try {
                    models = database.Search(queryMetaData);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return models;
            }
            
        });

        ArrayList<CheckModel> models = null;
        try {
            models = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return models;
    }
}
