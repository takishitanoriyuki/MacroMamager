package window.parts;

import javax.swing.*;
import java.util.List;

import model.*;

public interface ITableModelManager {
    void UpdateRow(int index, DataRecord record);
    void DeleteRow(int index);
    void UpdateTotal(List<DataRecord> record);
    void CreateTableFromRecords(List<DataRecord> records);
    void InsertTableFromRecord(DataRecord record);
    void RemoveRow(int index);
    JTable GetTable();
}