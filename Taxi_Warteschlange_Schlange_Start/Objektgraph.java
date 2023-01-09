
public class Objektgraph
{
    private Ausgabe labelSprechblase;
    private Ausgabe labelName;
    private String name;
    private String kommentar;
    private int x,y;
    private Linie linie,sprechlinie1, sprechlinie2;
    private Ellipse sprechblase;
    private Bild mann0,mann1, mann2;
    private static final int BREITE=100;
    private static final int HOEHE=190;
    private int zustand=0;

    /**
     * Erzeugt ein Legomännchen mit Beschriftung unterhalbe und anzeigbarer Sprechblase
     * Je nach Zustand schaut das Männchen nach links (inaktiv), nach vorne (aktiv) oder nach rechts (wartend)
     * 
     * @param text Beschreibung
     * 
     */
    public Objektgraph(String name){
        this(name, 0,0);
    }

    public Objektgraph(){
        this("Hans-Rolf", 200,100);
    }

    /**
     * Erzeugt einen Objektgraph mit Text in der linken oberen Ecke des Fensters
     * 
     * @param text Beschreibung
     * @param x x-Position
     * @param y y-Position
     * 
     */
    public Objektgraph(String name, int x, int y){
        this.name=name;
        this.x=x;
        this.y=y;
        mann0 = new Bild("taxi.jpg");
        mann0.setzeGroesse(75, 120);
        mann0.einpassen();
        mann1 = new Bild("taxi.jpg");
        mann1.setzeGroesse(75, 120);
        mann1.einpassen();
        mann2 = new Bild("taxi.jpg");
        mann2.setzeGroesse(75, 120);
        mann2.einpassen();

        labelSprechblase=new Ausgabe();
        labelSprechblase.setzeAusrichtung(1);
        labelSprechblase.setzeSchriftgroesse(9);
        labelName=new Ausgabe();
        labelName.setzeAusrichtung(1);
        labelName.setzeSchriftgroesse(12);
        sprechblase= new Ellipse();
        sprechblase.setzeFarbe("grau");
        sprechblase.rand();
        sprechblase.setzeBogen(-80, 325);
        linie=new Linie();
        linie.setzeLinienDicke(2);       
        sprechlinie1=new Linie();
        sprechlinie1.setzeLinienDicke(2);
        sprechlinie1.setzeFarbe("grau");
        sprechlinie2=new Linie();
        sprechlinie2.setzeLinienDicke(2);
        sprechlinie2.setzeFarbe("grau");
        setzeKommentar("");
        mann0.sichtbarMachen();
        mann1.unsichtbarMachen();
        mann2.unsichtbarMachen();
        aktualisiere();
    }

    /**
     * Liefert die rechte Begrenzung des Objektgraphs zurück. Dort kann sich ein zweiter Objektgraph anschließen.
     */
    public int gibRechtenRand(){
        return x+BREITE+15;
    }

    /**
     * Setzt die Position des Objektgraphs
     * @param x x-Position
     * @param y y-Position
     */
    public void setzePosition(int x, int y){
        this.x=x;
        this.y=y;
        aktualisiere();
    }

    /**
     * Bewegt den Objektgraph zu der angegebenen Position in 300ms
     * @param x x-Position
     * @param y y-Position
     */
    public void bewegeZuPosition(int zielX, int zielY){
        int dauer=400;
        int deltaX=zielX-x;
        int deltaY=zielY-y;
        if(deltaX!=0 || deltaY!=0){  // nur bewegen, falls neue Position
            for (int i=0;i<dauer/20;i++){
                x=x+deltaX/(dauer/20);
                y=y+deltaY/(dauer/20);
                aktualisiere();
                StaticTools.warte(20);
            }
            x=zielX;
            y=zielY;
            aktualisiere();
        }
    }

    /**
     * Setzt den Zustand des Objektgraphs, der damit farbig hervorgehoben wird. Anschließend wird die angegbene Zeit gewartet.
     * @ param zustand Zustand des Objektgraphs (0: inaktiv, 1: aktiv; 2: wartend)
     * @ param millitime Wartezeit in Millisekunden
     * 
     */
    public void setzeZustand(int zustand, int millitime){
        this.zustand=zustand;
        switch (zustand){
            case 0:  // inaktiv 
            mann0.sichtbarMachen();
            mann1.unsichtbarMachen();
            mann2.unsichtbarMachen();
            break;
            case 1:  // aktiv
            mann1.sichtbarMachen();
            mann0.unsichtbarMachen();
            mann2.unsichtbarMachen();
            break;
            case 2:  // warte
            mann2.sichtbarMachen();
            mann0.unsichtbarMachen();
            mann1.unsichtbarMachen();
            break;
        }
        StaticTools.warte(millitime);

    }

    /**
     * Setzt die grafischen Elemente auf die richtigen Positionen.
     */
    private void aktualisiere(){
        mann0.setzePosition(x+35, y+50);
        mann1.setzePosition(x+15, y+50);
        mann2.setzePosition(x+15, y+50);
        labelSprechblase.setzeAusgabetext(kommentar);
        labelName.setzeAusgabetext(name);
        sprechblase.setzeDimensionen(x, y, BREITE, 25);
        labelSprechblase.setzeDimensionen(x, y+5, BREITE, 15);
        labelName.setzeDimensionen(x+10, y+120, BREITE, 20);
        linie.setzeEndpunkte(x+BREITE, y+HOEHE/2, x+BREITE+15, y+HOEHE/2);
        sprechlinie1.setzeEndpunkte(x+30,y+22,x+50,y+45);
        sprechlinie2.setzeEndpunkte(x+57,y+23,x+50,y+45);
    }

    /**
     * Setzt die Kommentarzeile und blendet bei nichtleerem Text die Sprechblase  
     * @param kommentar
     */
    public void setzeKommentar(String kommentar){
        this.kommentar=kommentar;
        labelSprechblase.setzeAusgabetext(kommentar);
        if(kommentar.equalsIgnoreCase("")){
            sprechblase.unsichtbarMachen();
            sprechlinie1.unsichtbarMachen();
            sprechlinie2.unsichtbarMachen();
        }
        else{
            this.kommentar=kommentar;
            sprechblase.sichtbarMachen();
            sprechlinie1.sichtbarMachen();
            sprechlinie2.sichtbarMachen();
        }
        aktualisiere();
    }
}
