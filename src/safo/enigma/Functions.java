package safo.enigma;

public class Functions {

    public static int charToDigit(char c) {
        return (int)(c) - 65;
    }
    
    public static char digitToChar(int i) {
        return (char)(i+65);
    }
    
}
