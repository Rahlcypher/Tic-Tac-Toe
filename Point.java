

import java.awt.Color;
import javax.swing.JButton;
public class Point extends JButton {
    public int cpt;
    int i;
    int j;
    Color color;
    int numero;
    boolean changed;

    public   int getCompteurpion(){
        return this.cpt;
    }
    public   void setCompteurpion(){
        this.cpt  +=  1;
    }
    
    Point(int num){
        // super(name);
            this.i = 100;
            this.j = 100;
            this.numero = num;
            this.color = new Color(12, 254, 117);
    }
    
}
