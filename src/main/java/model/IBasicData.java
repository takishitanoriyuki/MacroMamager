package model;

public interface IBasicData {
    /**
     * タンパク質
     * @return
     */
    double getProtein();
    void setProtein(double value);
    /**
     * カロリー
     */
    double getCalorie();
    void setCalorie(double value);
    /**
     * 脂質
     * @return
     */
    double getLipid();
    void setLipid(double value);
    /**
     * 炭水化物
     * @return
     */
    double getCarbohydrate();
    void setCarbohydrate(double value);
    /**
     * ファイルを読み込めたかどうか
     * @return
     */
    boolean isExist();
    boolean Save();
}