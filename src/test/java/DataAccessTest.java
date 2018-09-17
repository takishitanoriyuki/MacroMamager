import org.junit.Test;

import model.*;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DataAccessTest {
    Random random = new Random(6);
    private void deleteFile(){
        // 前準備
        String currentDirName = new File(".").getAbsoluteFile().getParent();
        File dataDir = new File(currentDirName + "/data");
        if(dataDir.exists() == true){
            File files[] = dataDir.listFiles();
            for (File file : files) {
                file.delete();
            }
        }
    }
    private void createDir(){
        // 前準備
        String currentDirName = new File(".").getAbsoluteFile().getParent();
        File dataDir = new File(currentDirName + "/data");
        if(dataDir.exists() == false){
            dataDir.mkdir();
        }
    }

    @Test public void testDataAccessNotExists(){
        deleteFile();

        // 現在の日付を取得
        Date today = new Date();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt(dateFormat1.format(today));
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM");
        int month = Integer.parseInt(dateFormat2.format(today));
        SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd");
        int day = Integer.parseInt(dateFormat3.format(today));

        DataAccess dataAccess = new DataAccess(year, month, day);
        List<DataRecord> records = dataAccess.OpenFile();

        assertEquals("データエラー", records.size(), 0);
    }

    @Test public void testDataAccessSaveLoad(){
        deleteFile();

        // 現在の日付を取得
        Date today = new Date();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt(dateFormat1.format(today));
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM");
        int month = Integer.parseInt(dateFormat2.format(today));
        SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd");
        int day = Integer.parseInt(dateFormat3.format(today));

        DataAccess dataAccess = new DataAccess(year, month, day);
        List<DataRecord> records = new ArrayList<DataRecord>();
        for(int i = 0; i < 5; i++){
            DataRecord record = new DataRecord();
            record.ItemName = "Test" + i;
            record.Protein = random.nextDouble();
            record.Carbohydrate = random.nextDouble();
            record.Lipid = random.nextDouble();
            record.Calorie = random.nextDouble();
            records.add(record);
        }
        dataAccess.OutputFile(records);

        List<DataRecord> tests = dataAccess.OpenFile();
        
        for (int i = 0; i < 5; i++) {
            DataRecord test = tests.get(i);
            DataRecord record = records.get(i);
            assertEquals("値が違います", test.ItemName, record.ItemName);
            assertEquals("値が違います", String.format("%.2f", test.Protein), String.format("%.2f", record.Protein));
            assertEquals("値が違います", String.format("%.2f", test.Carbohydrate), String.format("%.2f", record.Carbohydrate));
            assertEquals("値が違います", String.format("%.2f", test.Lipid), String.format("%.2f", record.Lipid));
            assertEquals("値が違います", String.format("%.2f", test.Calorie), String.format("%.2f", record.Calorie));
        }
        
    }
}
