package core;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class MainThread extends JFrame{
    // 屏幕分辨率的相关设置
    public static int screen_w = 1024;
    public static int screen_h = 720;
    public static int half_screen_w = screen_w / 2;
    public static int half_screen_h = screen_h / 2;

    // 渲染时的帧数
    public static int frames = 0;
    // 希望达到的每帧之间的间隔时间，也就是用来调整刷新率的参数
    // 当每帧之间间隔时间短时，也就提高了每秒渲染的帧数，也就是拉高了刷新率
    public static int frameInterval = 0;

    // CPU的睡眠时间，数字越小说明运行算效率越高
    public static int sleepTime;

    // 刷新率，以及计算刷新了所用到一些辅助参数
    public static int framePerSecond;
    public static long lastDraw = 0;
    public static double thisTime = 0, lastTime = 0;

    // 用JPanel作为面板
    public static JPanel panel;

    // 使用一个int型数组用以存储屏幕上的像素点
    public static int[] screen;

    // 屏幕图像缓冲区，它提供了在内存中操作屏幕图像的方法
    public static BufferedImage screenBuffer;

    // 程序的入口点
    public static void main(String[] args) {
        new MainThread();
    }

    // 主程序入口的构造函数
    public MainThread() {
        // 弹出一个宽为screen_w、高为screen_h的JPanel窗口，并把它放置在屏幕中间
        setTitle("Java windows");
        panel = (JPanel) this.getContentPane();
        panel.setPreferredSize(new Dimension(screen_w, screen_h));
        panel.setMinimumSize(new Dimension(screen_w, screen_h));
        panel.setLayout(null);

        setResizable(false);
        pack();
        setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 用TYPE_INT_RGB来创建BufferedImage，然后把屏幕的像素数组指向（链接）到BufferedImage中的DataBuffer
        // 这样通过改变屏幕的像素数组（screen[]）中的数据就可以在屏幕中渲染出图像
        screenBuffer = new BufferedImage(screen_w, screen_h, BufferedImage.TYPE_INT_RGB);
        DataBuffer dest = screenBuffer.getRaster().getDataBuffer();
        screen = ((DataBufferInt) dest).getData();


        // 初始化查找表
        LookupTables.init();

        while (true) {
            // 定义一个颜色
            int r_skyblue = 163, g_skyblue = 216, b_skyblue = 239;

            //
            Arrays.fill(screen, (r_skyblue<<16)|(g_skyblue<<8)|b_skyblue);

            frames++;

            // 计算当前的刷新率，并且尽量让刷新率保持稳定
            if (frames % 30 == 0) {
                thisTime = System.currentTimeMillis();

                framePerSecond = (int) (1000 / ((thisTime - lastTime) / 30));

                lastTime = thisTime;
            }
            sleepTime = 0;
            while (System.currentTimeMillis() - lastDraw<frameInterval) {
                try {
                    Thread.sleep(1);
                    sleepTime++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lastDraw = System.currentTimeMillis();

            // 显示当前刷新率
            Graphics2D g2 = (Graphics2D) screenBuffer.getGraphics();
            g2.setColor(Color.BLACK);
            g2.drawString("FPS: " + framePerSecond + "   " + "Thread Sleep: " + sleepTime + "ms    ", 5, 15);

            // 把图像发送到现存中，这是唯一需要调用到显卡的地方
            panel.getGraphics().drawImage(screenBuffer, 0, 0, this);
        }
    }
}
