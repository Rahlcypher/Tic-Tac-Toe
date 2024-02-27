
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Game {
    private static final Color c1 = Color.GREEN;    
    private static final Color c2 = Color.RED;
    public static JFrame fen;
    public static JPanel panel;  // le panneau sur lequel sont aff
    public static JLabel label  ;
    
    public static JLabel lblsc1;
    public static JLabel lblsc2;
    public static JLabel lblsc3;

    public static ArrayList <Line> liste ;
    public static int cptjoueur = 0 ;
    public static boolean fin_de_jeu = false ;
    public static int scoreJ1 = 0;
    public static int scoreJ2 = 0;
    public static int scorenull = 0;







    
    public static   int getCompteur(){
        return cptjoueur;
    }
    public  static void setCompeur(int newcpt){
        cptjoueur = newcpt;
    }
    public static void jeu (){

            fen = new JFrame("JEU TIC TAC TOE");
            fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            fen.setLocation(dim.width/2 - fen.getWidth()/2, dim.height/2 - fen.getHeight()/2);

            JPanel panelH = new JPanel();
            JPanel panelB = new JPanel();

            label = new JLabel("NOUVELLE PARTIE");
            panelH.add(label);

            lblsc1  = new JLabel(""+scoreJ1+"");
            lblsc2  = new JLabel(""+scoreJ2+"");
            lblsc3  = new JLabel(""+scorenull+"");
            
            
            JButton restart = new JButton("RESTART");
            restart.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    reset();
                }
            });
            
            panelB.add(lblsc1);
            panelB.add(lblsc2);
            panelB.add(lblsc3);
            panelB.add(restart);

            panel = new JPanel();
            fen.add(panelH,BorderLayout.NORTH);
            fen.add(panel,BorderLayout.CENTER);
            fen.add(panelB,BorderLayout.SOUTH);
            panel.setLayout(new GridLayout(3,3));
            
            for(int i = 0; i<3; i++){
                for(int j = 1; j<4;j++){
                    
                    Point point = new Point(j+3*i);
                    point.addMouseListener(new MouseAdapter() {
    
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int jeu = 0;
                            // // Votre code ici
                            // // on verifie si le bouton a deja ete clique
                            if (point.changed == false){
                                    point.changed = true;
                                    jeu = getCompteur() + 1 ;
                                    setCompeur(jeu);
                                    
                                    // on determine quel joueur vient de cliquer
                                    if (jeu % 2 == 0){
                                        point.setBackground(c1);
                                        label.setText("joueur 1 jouez");
                                        System.out.println(" le JOUEUR 1 viens de cliquer");
                                    }
                                    else {
                                        label.setText("joueur 2 jouez");
                                        point.setBackground(c2);
                                        System.out.println(" le JOUEUR 2 viens de cliquer");
                                    }
                                    if(IsEnd(liste) == 1){
                                            System.out.println("le jouer1 gagne");
                                            label.setText("le joueur 1 gagne");
                                            scoreJ1++;
                                            lblsc1.setText("JOUEUR_1: "+scoreJ1);
                                            
                                            reset();
                                    }else{
                                        if(IsEnd(liste) == 2){
                                            System.out.println("le jouer2 gagne");
                                            label.setText("le joueur 2 gagne");
                                            scoreJ2++;
                                            lblsc2.setText("JOUEUR_2: "+scoreJ2);
                                            
                                            reset();
                                        }
                                        else {
                                            //on verifie si toutes les cases onete modifiees
                                            if(getCompteur() == 9){
                                                scorenull++;
                                                lblsc3.setText("NULL : "+scorenull);
                                                reset();
                                            }
                                        }
                                        
                                    }
                                            
                            }
                        }
                        });
                    panel.add(point);

                }
            }

        panel.validate();
        liste= new ArrayList<>();
        liste.add(new Line(panel.getComponent(1 - 1), panel.getComponent(2 - 1), panel.getComponent(3 - 1)));    
        liste.add(new Line(panel.getComponent(4 - 1), panel.getComponent(5 - 1), panel.getComponent(6 - 1)));    
        liste.add(new Line(panel.getComponent(7 - 1), panel.getComponent(8 - 1), panel.getComponent(9 - 1)));    
        liste.add(new Line(panel.getComponent(1 - 1), panel.getComponent(4 - 1), panel.getComponent(7 - 1)));    
        liste.add(new Line(panel.getComponent(2 - 1), panel.getComponent(5 - 1), panel.getComponent(8 - 1)));    
        liste.add(new Line(panel.getComponent(3 - 1), panel.getComponent(6 - 1), panel.getComponent(9 - 1)));    
        liste.add(new Line(panel.getComponent(3 - 1), panel.getComponent(5 - 1), panel.getComponent(7 - 1)));    
        liste.add(new Line(panel.getComponent(1 - 1), panel.getComponent(5 - 1), panel.getComponent(9 - 1)));    
        

        fen.setSize(500,500);
        fen.setVisible(true);
    }
    public static int IsEnd(ArrayList <Line>  list){
        int winner = 0;
            for (Line i : list) {
                try{
                    // System.out.println("+++++++");
                    if ( i.p1.getBackground() == c1 && i.p2.getBackground() == c1 &&  i.p3.getBackground() == c1) {
                         winner = 1;
                        System.out.println("+++++++++++++++++++++++++++ we  have a winner +++++++++++++++++++++++++++");
                        break;
                    }
                    if ( i.p1.getBackground() == c2 &&  i.p2.getBackground() == c2 &&  i.p3.getBackground() == c2) {
                        winner = 2;
                        System.out.println("+++++++++++++++++++++++++++ we  have a winner +++++++++++++++++++++++++++");
                        break;
                    }
                   
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        
        return winner;
    }

    public static void reset (){
        setCompeur(0);

        fen.add(panel, BorderLayout.CENTER);
        for (Component cpn : panel.getComponents()){
            if(cpn instanceof Point){
                Point pt = (Point) cpn;
                pt.changed = false;
                pt.setBackground(new Color(0,0,0));
            }
        }
    }



    public static void main(String[] args) {
        jeu();
    }

}
