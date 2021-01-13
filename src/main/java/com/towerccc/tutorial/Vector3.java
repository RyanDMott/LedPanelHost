package com.towerccc.tutorial;

public class Vector3 {
    public double x;
    public double y;
    public double z;

    public Vector3(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void print()
    {
        System.out.print(String.format(
                "Vector3:\n\tX = %f\n\tY = %f\n\tZ = %f\n",
                this.x, this.y, this.z));
    }

    public Vector3 cross(Vector3 right)
    {
        double xOut = this.y * right.z - this.z * right.y;
        double yOut = this.z * right.x - this.x * right.z;
        double zOut = this.x * right.y - this.y * right.x;
        return new Vector3(xOut, yOut, zOut);
    }
}