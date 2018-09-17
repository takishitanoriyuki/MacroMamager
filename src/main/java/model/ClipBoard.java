package model;

public class ClipBoard implements IClipBoard{
    private static ClipBoard clipBoard= new ClipBoard();
    private static DataRecord storeData = null;

    private ClipBoard(){

    }

    public static IClipBoard getInstanse(){
        return clipBoard;
    }

    @Override
    public void Store(DataRecord data) {
        storeData = new DataRecord();
        storeData.ItemName = data.ItemName;
        storeData.Protein = data.Protein;
        storeData.Carbohydrate = data.Carbohydrate;
        storeData.Lipid = data.Lipid;
        storeData.Calorie = data.Calorie;
    }

    @Override
    public DataRecord Pull() {
        return this.storeData;
    }

    @Override
    public void Clear() {
        this.storeData = null;
    }


}