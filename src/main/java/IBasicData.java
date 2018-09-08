public interface IBasicData{
    /**
     * タンパク質
     * @return
     */
    double getProtein();
    void setProtein(double protein);
    /**
     * カロリー
     */
    double getCalorie();
    void setCalorie(double calorie);
    /**
     * 脂質
     * @return
     */
    double getLipid();
    void setLipid(double lipid);
    /**
     * 炭水化物
     * @return
     */
    double getCarbohydrate();
    void setCarbohydrate(double carbohydrate);
    /**
     * データ保存
     */
    boolean Save();
    /**
     * ファイルを読み込めたかどうか
     * @return
     */
    boolean isExist();
}