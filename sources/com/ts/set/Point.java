package com.ts.set;

/* compiled from: SettingSoundActivity */
class Point {
    public float x;
    public float y;

    public Point() {
        setXY(0.0f, 0.0f);
    }

    public Point(float x2, float y2) {
        setXY(x2, y2);
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x2) {
        this.x = x2;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y2) {
        this.y = y2;
    }

    public void setXY(float x2, float y2) {
        this.x = x2;
        this.y = y2;
    }
}
