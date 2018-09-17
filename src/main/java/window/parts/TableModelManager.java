package window.parts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import model.*;

public class TableModelManager implements ITableModelManager {
    private static TableModelManager tableModelManager = new TableModelManager();
    private DefaultTableModel tableModel;

    /**
     * コンストラクタ
     */
    private TableModelManager(){
        // カラムのタイトル
        this.tableModel = new DefaultTableModel();
        this.tableModel.addColumn("Item");
        this.tableModel.addColumn("Protein");
        this.tableModel.addColumn("Carbohydrate");
        this.tableModel.addColumn("Lipid");
        this.tableModel.addColumn("Calorie");

    }

    public static ITableModelManager getInstance(){
        return tableModelManager;
    }

    public void UpdateRow(int index, DataRecord record){
        // テーブルを更新する
        this.tableModel.setValueAt(record.ItemName, index + 1, 0);
        this.tableModel.setValueAt(String.format("%.2f", record.Protein), index + 1, 1);
        this.tableModel.setValueAt(String.format("%.2f", record.Carbohydrate), index + 1, 2);
        this.tableModel.setValueAt(String.format("%.2f", record.Lipid), index + 1, 3);
        this.tableModel.setValueAt(String.format("%.2f", record.Calorie), index + 1, 4);
    }

    public void DeleteRow(int index){
        // テーブルを更新する
        this.tableModel.removeRow(index);
    }

    public JTable GetTable(){
        JTable table = new JTable(this.tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }

    public void UpdateTotal(List<DataRecord> record){
        TableAccess.UpdateTable(record, this.tableModel);
    }

    /**
     * レコードリストからテーブルを作成する
     * @param records
     */
    public void CreateTableFromRecords(List<DataRecord> records){
        if(this.tableModel.getRowCount() > 1){
            while(this.tableModel.getRowCount() > 1)
            this.tableModel.removeRow(1);            
        }
        for (DataRecord record : records) {
            InsertTableFromRecord(record);
        }
    }

    /**
     * レコードからテーブルに設定する
     * @param record
     */
    public void InsertTableFromRecord(DataRecord record){
        Object[] row = new Object[5];
        row[0] = record.ItemName;
        row[1] = String.format("%.2f", record.Protein);
        row[2] = String.format("%.2f", record.Carbohydrate);
        row[3] = String.format("%.2f", record.Lipid);
        row[4] = String.format("%.2f", record.Calorie);
        this.tableModel.insertRow(this.tableModel.getRowCount(), row);
    }

    public void RemoveRow(int index){
        this.tableModel.removeRow(index);
    }

}
