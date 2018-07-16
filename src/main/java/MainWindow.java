import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class MainWindow{
    private String[] columnNames = {"品目", "タンパク質", "炭水化物", "脂質", "カロリー"};
    private String[][] tabledata = {{"","","","",""}};

    public MainWindow(){
        CreateWindow();
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
                InputDialog dialog = new InputDialog(frame);
                dialog.Show();
            }
        });
        contentPane.add(addButton, BorderLayout.CENTER);

        JTable listTable = new JTable(tabledata, columnNames);
        JScrollPane sp = new JScrollPane(listTable);
        contentPane.add(sp, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}