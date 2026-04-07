class Robot {
    private final int w;
    private final int h;
    private final int perimeter;
    private int pos;
    private boolean moved;

    public Robot(int width, int height) {
        this.w = width;
        this.h = height;
        this.perimeter = 2 * (width + height) - 4;
        this.pos = 0;
        this.moved = false;
    }

    public void step(int num) {
        pos = (pos + num) % perimeter;
        moved = true;
    }

    public int[] getPos() {
        if (pos < w) {
            return new int[] {pos, 0};
        }

        if (pos < w + h - 1) {
            return new int[] {w - 1, pos - w + 1};
        }

        if (pos < 2 * w + h - 2) {
            int t = pos - (w + h - 1);
            return new int[] {w - 2 - t, h - 1};
        }

        int t = pos - (2 * w + h - 2);
        return new int[] {0, h - 2 - t};
    }

    public String getDir() {
        if (pos == 0) {
            return moved ? "South" : "East";
        }
        if (pos <= w - 1) {
            return "East";
        }
        if (pos <= w + h - 2) {
            return "North";
        }
        if (pos <= 2 * w + h - 3) {
            return "West";
        }
        return "South";
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */