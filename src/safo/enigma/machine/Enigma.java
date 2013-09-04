package safo.enigma.machine;

import safo.enigma.Functions;

public class Enigma {
    
    private Rotor[] rotors = new Rotor[3];
    private int[] rotorStates = new int[3];
    private Reflector reflector;
    private PlugBoard plugboard;
    
    public Enigma() {
        rotorStates = new int[]{0, 0, 0};
        rotors[0] = Rotor.III;
        rotors[1] = Rotor.II;
        rotors[2] = Rotor.I;
        reflector = Reflector.B;   
        plugboard = new PlugBoard();
    }
    
    public void setRotor(int num, Rotor r) {
        rotors[num] = r;
    }
    
    public Rotor getRotor(int num) {
        return rotors[num];
    }
    
    public void setRotorState(int num, int state) {
        rotorStates[num] = state;
    }
    
    public void setRotorState(int num, char state) {
        rotorStates[num] = (int)(state) - 65;
    }
    
    public int getRotorState(int num) {
        return rotorStates[num];
    }
    
    public void setRingSetting(int num, int setting) {
        rotors[num].setRingSetting(setting);
    }

    public Reflector getReflector() {
        return reflector;
    }

    public void setReflector(Reflector reflector) {
        this.reflector = reflector;
    }

    public PlugBoard getPlugboard() {
        return plugboard;
    }

    public void setPlugboard(PlugBoard plugboard) {
        this.plugboard = plugboard;
    }

    public int getOutput(int in) {
        int input = in;
        input = plugboard.getOutput(input);
        
        /* Hardcoded for 3 rotors. */
        
        rotorStates[2] = (rotorStates[2] + 1) % 26;
        if (rotorStates[2] == rotors[2].getNotch()) {
            
            rotorStates[1] = (rotorStates[1] + 1) % 26;
            
            if (rotorStates[1] == rotors[1].getNotch()) {
                rotorStates[0] = (rotorStates[0] + 1) % 26;
            }
            
        }

        int f1 = rotors[2].getOutput(rotorStates[2], input);
        int f2 = rotors[1].getOutput(rotorStates[1], f1);
        int f3 = rotors[0].getOutput(rotorStates[0], f2);
        
        int b = reflector.getOutput(f3);
        int b1 = rotors[0].getInput(rotorStates[0], b);
        int b2 = rotors[1].getInput(rotorStates[1], b1);
        int b3 = rotors[2].getInput(rotorStates[2], b2);
        
        int output = plugboard.getOutput(b3);
        
        return output;
    }
    
    public String getOutput(String s) {
        String out  = "";
        for (int i = 0; i < s.length(); i++) {
            
            if (s.codePointAt(i) >= 65 && s.codePointAt(i) <= 90)
                out += Functions.digitToChar(this.getOutput(Functions.charToDigit(s.charAt(i))));
            else if (s.codePointAt(i) == 32)
                out += " ";
            
        }
        return out;
    }

}
