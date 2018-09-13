package adapter;

import java.util.List;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dialog.EditDialog;
import model.DataRecord;
import window.MainWindow;
import window.parts.TableAccess;

public class TableClickAdapter extends MouseAdapter {
    private MainWindow mainWindow;
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<DataRecord> dataRecord;

    private final String DELETE = "Delete";
    private final String EDITITEM = "EditItem";
    private final String COPY = "Copy";

    public TableClickAdapter(MainWindow main, JFrame frame, JTable inputTable, DefaultTableModel inputTableModel, List<DataRecord> list){
        this.mainWindow = main;
        this.frame = frame;
        this.table = inputTable;
        this.tableModel = inputTableModel;
        this.dataRecord = list;
    }

    public void mouseClicked(MouseEvent event){
        // テーブルを右クリックしたときの処理
        if(event.getButton() == MouseEvent.BUTTON3){
            int indexs[] = this.table.getSelectedRows();
            // 選択している行がないときは何もしない
            if(indexs.length == 0){
                return;
            }
            // 選択している行が合計の行だった場合
            for (int index : indexs) {
                if(index == 0){
                    return;
                }
            }
            JPopupMenu popupMenu = new JPopupMenu();
            // 複数行選択している場合は編集メニューを表示しない
            if(indexs.length == 1){
                // 編集メニューの実装
                JMenuItem menuItemEdit = new JMenuItem("EDIT");
                menuItemEdit.setActionCommand(EDITITEM);
                menuItemEdit.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 削除メニュークリック時の処理
                        if(e.getActionCommand() == EDITITEM){
                            int index = table.getSelectedRow() - 1;
                            DataRecord record = dataRecord.get(index);
                            EditDialog dialog = new EditDialog(frame, mainWindow, index, record);
                            dialog.Show();
                        }
                    }
                });
                popupMenu.add(menuItemEdit);

                // 複製メニューの実装
                JMenuItem menuItemCopy = new JMenuItem("COPY");
                menuItemCopy.setActionCommand(COPY);
                menuItemCopy.addActionListener(new ActionListener(){
                
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 削除メニュークリック時の処理
                        if(e.getActionCommand() == COPY){
                            int index = table.getSelectedRow() - 1;
                            DataRecord record = dataRecord.get(index);
                            DataRecord copiedRecord = new DataRecord();
                            copiedRecord.ItemName = record.ItemName;
                            copiedRecord.Protein = record.Protein;
                            copiedRecord.Carbohydrate = record.Carbohydrate;
                            copiedRecord.Lipid = record.Lipid;
                            copiedRecord.Calorie = record.Calorie;
                            mainWindow.SetRecord(copiedRecord);
                        }
                    }
                });
                popupMenu.add(menuItemCopy);
            }

            // 削除メニューの実装
            JMenuItem menuItemDelele = new JMenuItem("DELETE");
            menuItemDelele.setActionCommand(DELETE);
            menuItemDelele.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 削除メニュークリック時の処理
                    if(e.getActionCommand() == DELETE){
                        int indexs[] = table.getSelectedRows();
                        for (int index : indexs) {
                            tableModel.removeRow(index);
                            dataRecord.remove(index - 1);
                            TableAccess.UpdateTable(dataRecord, tableModel);
                        }
                    }
                }
            });
            popupMenu.add(menuItemDelele);
            popupMenu.show(event.getComponent(), event.getX(), event.getY());
        }
    }
}