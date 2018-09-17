package other;

import javax.swing.*;

import model.DataRecord;

public class GetValueFromTextField {
    /**
     * テキストフィールドから入力データ値を取り出す
     * @param dialog
     * @param protein
     * @param carbohydrate
     * @param lipid
     * @param calorie
     * @return
     */
    public static DataRecord GetValue(JDialog dialog, JTextField protein, JTextField carbohydrate, JTextField lipid, JTextField calorie){
        float inputProteinValue;
        float inputCarbohydrateValue;
        float inputLipidValue;
        float inputCalorieValue;
        //パラメータチェック
        try {
            inputProteinValue = Float.parseFloat(protein.getText());
        } catch (NumberFormatException e) {
            JOptionPane joption = new JOptionPane();
            joption.showMessageDialog(dialog, "Please input proteins by numbers.");
            return null;
        }
        try{
            inputCarbohydrateValue = Float.parseFloat(carbohydrate.getText());
        } catch(NumberFormatException e){
            JOptionPane joption = new JOptionPane();
            joption.showMessageDialog(dialog, "Please input carbohydrate by numbers.");
            return null;
        }
        try{
            inputLipidValue = Float.parseFloat(lipid.getText());
        }catch(NumberFormatException e){
            JOptionPane joption = new JOptionPane();
            joption.showMessageDialog(dialog, "Please input lipid by numbers.");
            return null;
        }
        try{
            inputCalorieValue = Float.parseFloat(calorie.getText());
        }catch(NumberFormatException e){
            JOptionPane joption = new JOptionPane();
            joption.showMessageDialog(dialog, "Please input calorie by numbers.");
            return null;
        }
        // データの登録
        DataRecord record = new DataRecord("", inputProteinValue, inputCarbohydrateValue, inputLipidValue, inputCalorieValue);
        return record;
    }
}