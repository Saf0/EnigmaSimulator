package safo.enigma;

import safo.enigma.machine.*;

public class test {

    public static void main(String[] args) {
        
        /*
         
            U-264 (Kapitänleutnant Hartwig Looks), 1942
            Machine Settings for Enigma I/M3
            
            Reflector:       B
            Wheel order:     II IV V
            Ring positions:  02 21 12
            Plug pairs:      AV BS CG DL FU HZ IN KM OW RX
            
            Message key: BLA
            
            EDPUD NRGYS ZRCXN UYTPO MRMBO FKTBZ REZKM LXLVE FGUEY SIOZV EQMIK UBPMM 
            YLKLT TDEIS MDICA GYKUA CTCDO MOHWX MUUIA UBSTS LRNBZ SZWNR FXWFY SSXJZ 
            VIJHI DISHP RKLKA YUPAD TXQSP INQMA TLPIF SVKDA SCTAC DPBOP VHJK
        
        */
        
        Enigma e = new Enigma();
        e.setReflector(Reflector.B);
        e.setRotor(0, Rotor.II);
        e.setRotor(1, Rotor.IV);
        e.setRotor(2, Rotor.V);
        e.setRotorState(0, 'B');
        e.setRotorState(1, 'L');
        e.setRotorState(2, 'A');
        e.setRingSetting(0, 1);
        e.setRingSetting(1, 20);
        e.setRingSetting(2, 11);
        
        PlugBoard pb = new PlugBoard();
        pb.setNumberOfPairs(10);
        pb.setPair(0, new Pair('A', 'V'));
        pb.setPair(1, new Pair('B', 'S'));
        pb.setPair(2, new Pair('C', 'G'));
        pb.setPair(3, new Pair('D', 'L'));
        pb.setPair(4, new Pair('F', 'U'));
        pb.setPair(5, new Pair('H', 'Z'));
        pb.setPair(6, new Pair('I', 'N'));
        pb.setPair(7, new Pair('K', 'M'));
        pb.setPair(8, new Pair('O', 'W'));
        pb.setPair(9, new Pair('R', 'X'));
        
        e.setPlugboard(pb);
        
        String encryptedMessage = "EDPUD NRGYS ZRCXN UYTPO MRMBO FKTBZ REZKM LXLVE FGUEY SIOZV EQMIK UBPMM";        
        System.out.println(e.getOutput(encryptedMessage));

    }

}
