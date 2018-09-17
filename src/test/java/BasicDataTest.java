import org.junit.Test;

import model.*;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Random;

public class BasicDataTest {
    Random random = new Random(7);
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

    @Test public void testBasicDataNotDirExists() {
        // テスト前準備
        deleteFile();

        IBasicData basic = BasicData.getInstanse();
        assertEquals("ディレクトリ作成に失敗しました", basic.isExist(), true);
    }

    @Test public void testBasicDataDirExists() {
        // テスト前準備
        deleteFile();
        createDir();

        IBasicData basic = BasicData.getInstanse();
        assertEquals("ファイル作成に失敗しました", basic.isExist(), true);
    }

    @Test public void testBasicDataSaveLoad() {
        // テスト前準備
        deleteFile();
        IBasicData basic = BasicData.getInstanse();

        double Protein = this.random.nextDouble();
        double Carbohydrate = this.random.nextDouble();
        double Lipid = this.random.nextDouble();
        double Calorie = this.random.nextDouble();

        basic.setProtein(Protein);
        basic.setCarbohydrate(Carbohydrate);
        basic.setLipid(Lipid);
        basic.setCalorie(Calorie);
        basic.Save();

        basic = null;
        basic = BasicData.getInstanse();
        assertEquals("Proteinの値が一致しません", String.format("%.2f", basic.getProtein()), String.format("%.2f", Protein));
        assertEquals("Carbohydrateの値が一致しません", String.format("%.2f", basic.getCarbohydrate()), String.format("%.2f", Carbohydrate));
        assertEquals("Lipidの値が一致しません", String.format("%.2f", basic.getLipid()), String.format("%.2f", Lipid));
        assertEquals("Calorieの値が一致しません", String.format("%.2f", basic.getCalorie()), String.format("%.2f", Calorie));
    }
}