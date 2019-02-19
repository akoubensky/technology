package atomic;

class Counter {
    private int cnt = 0;
    public void incr() { { cnt++; } }
    public int getCounter() { return cnt; }
}
