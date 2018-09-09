public interface IBasicData{
    /**
     * タンパク質
     * @return
     */
    double getProtein();
    /**
     * カロリー
     */
    double getCalorie();
    /**
     * 脂質
     * @return
     */
    double getLipid();
    /**
     * 炭水化物
     * @return
     */
    double getCarbohydrate();
    /**
     * ファイルを読み込めたかどうか
     * @return
     */
    boolean isExist();
}