package window.parts;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.*;

public class TableAccess {
    /**
     * リストデータから合計を計算し、テーブルを更新する
     * @param dataRecord
     * @param tableModel
     */
    public static void UpdateTable(List<DataRecord> dataRecord, DefaultTableModel tableModel){
        DataRecord columnCalc = new DataRecord("Total", 0.00, 0.00, 0.00, 0.00);
        // リストの全データの合計を求める
        for (DataRecord record : dataRecord) {
            columnCalc.Protein += record.Protein;
            columnCalc.Carbohydrate += record.Carbohydrate;
            columnCalc.Lipid += record.Lipid;
            columnCalc.Calorie += record.Calorie;
        }

        IBasicData basicData = BasicData.getInstanse();
        // 合計の行の値を更新する
        if(basicData.isExist() == true){
            if(basicData.getProtein() == 0){
                tableModel.setValueAt(String.format("%.2f", columnCalc.Protein, basicData.getProtein()), 0, 1);
            }else{
                tableModel.setValueAt(String.format("%.2f / %.2f (%3.1f%%)", columnCalc.Protein, basicData.getProtein(),
                    columnCalc.Protein / basicData.getProtein() * 100), 0, 1);
            }
            if(basicData.getLipid() == 0){
                tableModel.setValueAt(String.format("%.2f", columnCalc.Lipid, basicData.getLipid()), 0, 2);
            }else{
                tableModel.setValueAt(String.format("%.2f / %.2f (%3.1f%%)", columnCalc.Lipid, basicData.getLipid(),
                        columnCalc.Lipid / basicData.getLipid() * 100), 0, 2);
            }
            if(basicData.getCarbohydrate() == 0){
                tableModel.setValueAt(String.format("%.2f", columnCalc.Carbohydrate, basicData.getCarbohydrate()), 0, 3);
            }else{
                tableModel.setValueAt(String.format("%.2f / %.2f (%3.1f%%)", columnCalc.Carbohydrate, basicData.getCarbohydrate(),
                    columnCalc.Carbohydrate / basicData.getCarbohydrate() * 100), 0, 3);
            }
            if(basicData.getCalorie() == 0){
                tableModel.setValueAt(String.format("%.2f", columnCalc.Protein, basicData.getCalorie()), 0, 4);
            }else{
                tableModel.setValueAt(String.format("%.2f / %.2f (%3.1f%%)", columnCalc.Calorie, basicData.getCalorie(),
                    columnCalc.Calorie / basicData.getCalorie() * 100), 0, 4);
            }
        }else{
            tableModel.setValueAt(String.format("%.2f", columnCalc.Protein), 0, 1);
            tableModel.setValueAt(String.format("%.2f", columnCalc.Lipid), 0, 2);
            tableModel.setValueAt(String.format("%.2f", columnCalc.Carbohydrate), 0, 3);
            tableModel.setValueAt(String.format("%.2f", columnCalc.Calorie), 0, 4);
        }
    }
}