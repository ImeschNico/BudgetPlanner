import java.awt.*;
import javax.swing.*;
import java.awt.geom.RoundRectangle2D;



//Damit die Panels richtig die agberundeten Ecken haben
public class RoundedPanel extends JPanel{
    private int radius;

    //Konstruktor der den Radius der Ecken festlget
    public RoundedPanel(int radius){
      this.radius = radius;
      setOpaque(false);  //  das Hintergrund richtig angezeigt wird
    }

    @Override
    protected void paintComponent(Graphics g){
      super.paintComponent(g);  // Standard Zeichnung des Panels

      //Die Grafik in Graphics2D umwandeln
      Graphics2D g2d = (Graphics2D)g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //glatte Kanten

      //abgerundetes Rechteck zeichnen
      g2d.setColor(getBackground());
      g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius)); {
      }
    }
   }
