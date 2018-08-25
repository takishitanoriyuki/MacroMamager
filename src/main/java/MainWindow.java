import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * メインウインドウ
 */
public class MainWindow{
    // このWindow
    private MainWindow mainWindow;
    // データリスト
    private List<DataRecord> dataRecord = new ArrayList<DataRecord>();
    // テーブルモデル
    private DefaultTableModel tableModel;
    private final String DELETE = "Delete";
    private final String EDITITEM = "EditItem";

    // コンストラクタ
    public MainWindow(){
        // 自分自身を保持
        this.mainWindow = this;
        // データ初期化
        initializeTable();
        // Window作成
        createWindow();
    }

    /**
     * ダイアログで入力したデータをテーブルに追加する
     */
    public void SetRecord(DataRecord inputDataRecord){
        // データリストにデータを追加する
        this.dataRecord.add(inputDataRecord);

        // テーブルにデータを追加する
        Object[] row = new Object[5];
        row[0] = inputDataRecord.ItemName;
        row[1] = inputDataRecord.Protein;
        row[2] = inputDataRecord.Carbohydrate;
        row[3] = inputDataRecord.Lipid;
        row[4] = inputDataRecord.Calorie;
        this.tableModel.insertRow(this.tableModel.getRowCount(), row);

        // 合計を算出し、テーブルを更新する
        updateTable();
    }

    /** 
     * ダイアログで入力したデータでテーブルを更新する
     */
    public void EditRecord(int index, DataRecord editDataRecord){
        // データを更新する
        DataRecord record = this.dataRecord.get(index);
        record.ItemName = editDataRecord.ItemName;
        record.Protein = editDataRecord.Protein;
        record.Carbohydrate = editDataRecord.Carbohydrate;
        record.Lipid = editDataRecord.Lipid;
        record.Calorie = editDataRecord.Calorie;

        // テーブルを更新する
        this.tableModel.setValueAt(record.ItemName, index + 1, 0);
        this.tableModel.setValueAt(record.Protein, index + 1, 1);
        this.tableModel.setValueAt(record.Carbohydrate, index + 1, 2);
        this.tableModel.setValueAt(record.Lipid, index + 1, 3);
        this.tableModel.setValueAt(record.Calorie, index + 1, 4);

        // 合計を算出し、テーブルを更新する
        updateTable();
    }

    /**
     * データ初期化
     */
    private void initializeTable(){
        // カラムのタイトル
        String[] columnNames = {"品目", "タンパク質", "炭水化物", "脂質", "カロリー"};

        // テーブルモデルの初期化
        this.tableModel = new DefaultTableModel(columnNames, 0);

        // 合計の行を追加する
        DataRecord columnCalc = new DataRecord("合計", 0.00, 0.00, 0.00, 0.00);
        Object[] row = new Object[5];
        row[0] = columnCalc.ItemName;
        row[1] = columnCalc.Protein;
        row[2] = columnCalc.Carbohydrate;
        row[3] = columnCalc.Lipid;
        row[4] = columnCalc.Calorie;
        this.tableModel.insertRow(0, row);
    }

    /**
     * Window作成
     */
    private void createWindow(){
        JFrame frame = new JFrame();
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();

        // 追加ボタンの実装
        JButton addButton = new JButton("追加");
        addButton.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent event){
                InputDialog dialog = new InputDialog(frame, mainWindow);
                dialog.Show();
            }
        });
        contentPane.add(addButton, BorderLayout.CENTER);

        // テーブルの実装
        JTable table = new JTable(this.tableModel);
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent event){
                // テーブルを右クリックしたときの処理
                if(event.getButton() == MouseEvent.BUTTON3){
                    int indexs[] = table.getSelectedRows();
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
                        JMenuItem menuItemEdit = new JMenuItem("編集");
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
                    }

                    // 削除メニューの実装
                    JMenuItem menuItemDelele = new JMenuItem("削除");
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
                                    updateTable();
                                }
                            }
						}
                    });
                    popupMenu.add(menuItemDelele);

                    popupMenu.show(event.getComponent(), event.getX(), event.getY());
                }
            }
        });
        JScrollPane sp = new JScrollPane(table);
        contentPane.add(sp, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
     * テーブル表示の更新
     */
    private void updateTable(){
        DataRecord columnCalc = new DataRecord("合計", 0.00, 0.00, 0.00, 0.00);
        // リストの全データの合計を求める
        for (DataRecord record : dataRecord) {
            columnCalc.Protein += record.Protein;
            columnCalc.Carbohydrate += record.Carbohydrate;
            columnCalc.Lipid += record.Lipid;
            columnCalc.Calorie += record.Calorie;
        }

        // 合計の行の値を更新する
        this.tableModel.setValueAt(columnCalc.Protein, 0, 1);
        this.tableModel.setValueAt(columnCalc.Carbohydrate, 0, 2);
        this.tableModel.setValueAt(columnCalc.Lipid, 0, 3);
        this.tableModel.setValueAt(columnCalc.Calorie, 0, 4);
    }
}
