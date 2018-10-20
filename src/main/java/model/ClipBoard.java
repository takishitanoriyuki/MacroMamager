package model;

public class ClipBoard implements IClipBoard{
    private static ClipBoard clipBoard= new ClipBoard();
    private static DataRecord storeData = null;
    private static boolean isStore = false;

    private ClipBoard(){
        storeData = null;
        isStore = false;
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
        isStore = true;
    }

    @Override
    public DataRecord Pull() {
        return storeData;
    }

    @Override
    public void Clear() {
        storeData = null;
        isStore = false;
    }

    @Override
    public boolean isStore(){
        return isStore;
    }
}