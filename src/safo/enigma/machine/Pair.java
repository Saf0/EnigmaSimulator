package safo.enigma.machine;

public class Pair {
    
    private int c1;  // char 1
    private int c2;  // char 2
    
    public Pair(int c1, int c2) {
        super();
        this.c1 = c1;
        this.c2 = c2;
    }
    
    public Pair(char c1, char c2) {
        super();
        this.c1 = (int)(c1) - 65;
        this.c2 = (int)(c2) - 65;
    }

    public int getC1() {
        return c1;
    }
    
    public void setC1(int c1) {
        this.c1 = c1;
    }
    
    public int getC2() {
        return c2;
    }
    
    public void setC2(int c2) {
        this.c2 = c2;
    }
    
    public String toString() {
        return (char)(c1+65) + "-" + (char)(c2+65);
    }
    
    

}
