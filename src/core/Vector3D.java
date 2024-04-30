package core;

public class Vector3D {
    // 定义矢量在X、Y、Z轴上的分量
    public float x, y, z;

    // 构造函数，传入矢量的三个分量
    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // 把x，y，z赋值为另一个Vector3D的X，y，z
    public void set(Vector3D v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    // 与另一个矢量相加（矢量的加法）
    public void add(Vector3D v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
    }

    // 与另一个矢量相减（矢量减法）
    public void subtract(Vector3D v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
    }

    // 矢量的点积，结果代表两个矢量之间的相似程度
    public float dot(Vector3D v1, Vector3D v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    // 矢量的叉乘，求一个与这两个矢量形成的平面垂直的法向量
    public void cross(Vector3D v1, Vector3D v2) {
        x = v1.y * v2.z - v1.z * v2.y;
        y = v1.z * v2.x - v1.x * v2.z;
        z = v1.x * v2.y - v1.y * v2.x;
    }

    // 返回矢量的长度
    public float getLength() {
        return (float) Math.sqrt(x*x + y*y + z*z);
    }

    // 将矢量单位化
    public void unit() {
        float length = getLength();
        x = x / length;
        y = y / length;
        z = z / length;
    }

    // 将矢量乘上一个标量
    public void scale(float scalar) {
        x *= scalar;
        y *= scalar;
        z *= scalar;
    }
}
