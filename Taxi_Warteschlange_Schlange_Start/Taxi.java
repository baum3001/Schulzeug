/**
 *
 * Beschreibung
 *
 * @version 1.0 vom %DATUM%
 * @author %AUTOR%
 */

public class Taxi {

    // Anfang Attribute
    private String kennzeichen;
    private String fahrerName;
    private Taxi nachfolger;
    private Objektgraph grafik;
    private int xPos,yPos;

    // Ende Attribute
    public Taxi(String name)
    { 
        fahrerName = name;
        grafik = new Objektgraph(name,-200,0);
    }
    // Anfang Methoden
    public Taxi nachfolgerGeben()
    {
        return nachfolger;
    }

    public void nachfolgerSetzen(Taxi t)
    {
        nachfolger = t;
        if(nachfolger!=null){
            nachfolger.setzePosition(grafik.gibRechtenRand(),200);
        }
    }

    public String getFahrerName() {
        return fahrerName;
    }

    public void setFahrerName(String fahrerName) {
        this.fahrerName = fahrerName;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    public void setzePosition(int x, int y){
        xPos=x;
        grafik.bewegeZuPosition(x, y);
        if(nachfolger!=null){
            nachfolger.setzePosition(grafik.gibRechtenRand(),200);
        }
    }      
}
