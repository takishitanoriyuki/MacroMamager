package window;

import java.util.List;

import javax.swing.*;

import model.*;

public interface IMainWindow {
    /**
     * JFrameを返す
     */
    JFrame getFrame();

    /**
     * JTableを返す
     */
    JTable getTable();

    /**
     * データリストを返す
     */
    List<DataRecord> getDataRecords();

    /**
     * 基本データを更新したときにコールする
     * 合計行を更新する
     */
    void UpdateBasicData();

    /**
     * ダイアログで入力したデータをテーブルに追加する
     */
    void SetRecord(DataRecord inputDataRecord);

    /** 
     * ダイアログで入力したデータでテーブルを更新する
     */
    void EditRecord(int index, DataRecord editDataRecord);

    /**
     * テーブルから行を削除する
     */
    void RemoveRecord(int index);
}