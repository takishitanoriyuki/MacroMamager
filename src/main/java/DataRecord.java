public class DataRecord{
    //品名
    public String ItemName;
    //タンパク質
    public float Protein;
    //炭水化物
    public float Carbohydrate;
    //脂質
    public float Lipid;
    //カロリー
    public float Calorie;

    public DataRecord(String inputItemName, float inputProtein, float inputCarbohydrate, float inputLipid, float inputCalorie){
        this.ItemName = inputItemName;
        this.Protein = inputProtein;
        this.Carbohydrate = inputCarbohydrate;
        this.Lipid = inputLipid;
        this.Calorie = inputCalorie;
    }
}