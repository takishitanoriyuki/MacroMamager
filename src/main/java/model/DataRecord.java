package model;

public class DataRecord{
    //品名
    public String ItemName;
    //タンパク質
    public double Protein;
    //炭水化物
    public double Carbohydrate;
    //脂質
    public double Lipid;
    //カロリー
    public double Calorie;

    public DataRecord(){
        this.ItemName = "";
        this.Protein = 0.0;
        this.Carbohydrate = 0.0;
        this.Lipid = 0.0;
        this.Calorie = 0.0;
    }

    public DataRecord(String inputItemName, double inputProtein, double inputCarbohydrate, double inputLipid, double inputCalorie){
        this.ItemName = inputItemName;
        this.Protein = inputProtein;
        this.Carbohydrate = inputCarbohydrate;
        this.Lipid = inputLipid;
        this.Calorie = inputCalorie;
    }
}