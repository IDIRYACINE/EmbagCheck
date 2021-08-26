package idir.embag.Models.CheckDataModel;


public class CheckModel{
    private String receiver ;
    private String date ;
    private Double amount ;
    private Integer ID ;
    private CheckStatus status ;
    private String location ;

    

    public CheckModel(String receiver, String date, Double amount, Integer iD, CheckStatus status , String location) {
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
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
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