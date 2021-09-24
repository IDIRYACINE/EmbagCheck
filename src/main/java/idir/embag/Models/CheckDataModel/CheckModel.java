package idir.embag.Models.CheckDataModel;

import java.math.BigDecimal;

public class CheckModel{
    private String receiver ;
    private String date ;
    private BigDecimal amount ;
    private String ID ;
    private CheckStatus status ;
    private String location ;

    public CheckModel(String receiver, String date, BigDecimal amount, String iD, CheckStatus status , String location) {
        this.setReceiver(receiver);
        this.date = date;
        this.setAmount(amount);
        ID = iD;
        this.status = status;
        this.location = location;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Double getAmount() {
        return amount.doubleValue();
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        this.ID = iD;
    }

   
    public String getStatus() {
        return status.toString();
    }

    public void setStatus(CheckStatus status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String value) {
        location = value ;
    }
    public String getLocation() {
        return location;
    }

    

    
}