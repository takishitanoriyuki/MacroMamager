package window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.*;

public interface IMainWindow {
    JFrame getFrame();
    JTable getTable();
    DefaultTableModel getTableModel();
    void UpdateBasicData();
    void SetRecord(DataRecord inputDataRecord);
    void EditRecord(int index, DataRecord editDataRecord);
}