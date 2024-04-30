package core;

public class LookupTables {
    // 直接设置两个用于存放cos值和sin值的数组
    public static float[] sin;
    public static float[] cos;

    public static void init() {
        // 产生一个用于快速查找的表
        sin = new float[360];
        cos = new float[360];
        for (int i = 0; i < 360; i++) {
            sin[i] = (float) Math.sin(Math.PI*i/180);
            cos[i] = (float) Math.cos(Math.PI*i/180);
        }
    }
}
