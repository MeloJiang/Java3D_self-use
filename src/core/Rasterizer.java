package core;

import com.sun.tools.javac.Main;

public class Rasterizer {
    // 设置屏幕的分辨率
    public static int screen_w = MainThread.screen_w;
    public static int screen_h = MainThread.screen_h;
    public static int half_screen_w = MainThread.half_screen_w;
    public static int half_screen_h = MainThread.half_screen_h;

    // 屏幕的像素组
    public static int[] screen = MainThread.screen;

    // 视角的原点到屏幕的距离（以像素为单位），这个值越大视角就越狭窄。
    // 常用的值为屏幕宽的一半
    public static int screenDistance = screen_w / 2;

    // 未经变换的三角形的顶点
    public static Vector3D[] triangleVertices;

    // 经过变换后的三角形顶点
    public static Vector3D[] updatedVertices;

    // 三角形的顶点数，一般为3个，但当三角形与视角Z平面相切时有可能会变成4个
    public static int verticesCount = 3;

    // 三角形变换后的顶点投影在屏幕上的2D坐标
    public static float[][] vertices2D = new float[4][2];

    /*
    用于扫描三角形的两个数组，每行有两个值，分别表示描线的起点和终点的x坐标
    为什么是长度为screen_h的数组？
        因为扫描一个三角形是从上到下的顺序，一行一行像素地去扫描
        所以数组的最大长度是整个屏幕的高度
     */
    public static int[] xLeft = new int[screen_h];
    public static int[] xRight = new int[screen_h];

    // 三角形扫描线最高和最低的位置
    public static int scanUpperPosition, scanLowerPosition;

    // 三角形的颜色
    public static int triangleColor;

    // 三角形的类型
    public static int renderType;

    // 初始化光栅渲染器
    public static void init() {
        updatedVertices = new Vector3D[] {
            new Vector3D(0, 0, 0),
                new Vector3D(0, 0, 0),
                new Vector3D(0, 0, 0),
                new Vector3D(0, 0, 0)
        };
    }

    // 光栅渲染器的入口
    public static void rasterize() {
        // 变换三角型的顶点

        // 将三角形转换为扫描线

        // 给三角形的像素着色
    }

}
