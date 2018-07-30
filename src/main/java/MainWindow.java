import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MainWindow{
    private String[] columnNames = {"品目", "タンパク質", "炭水化物", "脂質", "カロリー"};
    private Object[] columnCalc = {"合計", 0.00, 0.00, 0.00, 0.00};
    private MainWindow mainWindow;
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    private JTable listTable;

    public MainWindow(){
        this.mainWindow = this;
        CreateWindow();
    }

    public void SetRecord(DataRecord inputDataRecord){
        Object[] row = new Object[5];
        row[0] = inputDataRecord.ItemName;
        row[1] = inputDataRecord.Protein;
        row[2] = inputDataRecord.Carbohydrate;
        row[3] = inputDataRecord.Lipid;
        row[4] = inputDataRecord.Calorie;
        tableModel.insertRow(tableModel.getRowCount(), row);
    }

    private void CreateWindow(){
        JFrame frame = new JFrame();
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        JButton addButton = new JButton("追加");
        addButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent event){
                InputDialog dialog = new InputDialog(frame, mainWindow);
                dialog.Show();
            }
        });
        contentPane.add(addButton, BorderLayout.CENTER);
        tableModel.insertRow(0, columnCalc);

        JTable listTable = new JTable(tableModel);
        
        JScrollPane sp = new JScrollPane(listTable);
        contentPane.add(sp, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}