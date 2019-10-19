class Record {
    private int value;
    private boolean isRed;
    private Record parent;
    private Record leftSon;
    private Record rightSon;

    Record(int value, boolean isRed, Record parent, Record leftSon, Record rightSon) {
        //Normal element constructor
        this.value = value;
        this.isRed = isRed;
        this.parent = parent;
        this.leftSon = leftSon;
        this.rightSon = rightSon;
    }

    Record() {
        //Sentry constructor
        this.value = 0;
        this.isRed = false;
        this.parent = null;
        this.leftSon = this;
        this.rightSon = this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("");

        if (this.leftSon == this && this.rightSon == this) {
            //Check if sentinel
            sb.append("n");
        } else {
            if (isRed) {
                sb.append("\033[31;1m");
                sb.append(this.value);
                sb.append("\033[0m");
            } else
                sb.append(this.value);
        }

        return sb.toString();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public Record getParent() {
        return parent;
    }

    public void setParent(Record parent) {
        this.parent = parent;
    }

    public Record getLeftSon() {
        return leftSon;
    }

    public void setLeftSon(Record leftSon) {
        this.leftSon = leftSon;
    }

    public Record getRightSon() {
        return rightSon;
    }

    public void setRightSon(Record rightSon) {
        this.rightSon = rightSon;
    }
}
