package window.parts;

import javax.swing.*;
import java.util.List;

import model.*;

public interface ITableModelManager {
    /**
     * テーブルを更新する
     */
    void UpdateRow(int index, DataRecord record);
    /**
     * テーブルから行を削除する
     */
    void DeleteRow(int index);
    /**
     * 合計値を計算し、テーブルを更新する
     */
    void UpdateTotal(List<DataRecord> record);
    /**
     * レコードリストからテーブルを作成する
     * @param records
     */
    void CreateTableFromRecords(List<DataRecord> records);
    /**
     * レコードからテーブルに設定する
     * @param record
     */
    void InsertTableFromRecord(DataRecord record);
    /**
     * テーブルモデルからJTableオブジェクトを作成し返す
     */
    JTable GetTable();
}