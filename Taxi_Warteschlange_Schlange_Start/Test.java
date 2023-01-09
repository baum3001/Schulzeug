
/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test{
  public static void main(){
      Taxi t1 = new Taxi("Albert");
      Taxi t2 = new Taxi("Berta");
      Taxi t3 = new Taxi("Otto");
      
      t1.setKennzeichen("AAAA") ;
      t2.setKennzeichen("BBBB");
      t3.setKennzeichen("CCCC");
      
      TaxiWarteschlange tw = new TaxiWarteschlange();
      
      tw.hintenAnstellen(t1);
      tw.hintenAnstellen(t2);
      tw.hintenAnstellen(t3);
      //tw.schlangeAusgeben();
      tw.vorneAbfahren();
     // tw.schlangeAusgeben();
    }
      
      

}