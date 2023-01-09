public class TaxiWarteschlange 

{

    

    Taxi start;

    Taxi ende;

    

    public TaxiWarteschlange()

    {

        start = null;

        ende = null;

    }

 

    public void hintenAnstellen(Taxi t)

    {

        if (ende == null)

       {

            start = t;

            ende = t;

       } 

        else

       {

            ende.nachfolgerSetzen(t);

            ende = t;

       }

    }



    public Taxi vorneAbfahren()

    {   

        if(start == null) return null;

        Taxi t = start;

        start = t.nachfolgerGeben();

        if(start == null) return t;

        if(start.nachfolgerGeben() == null) ende = start;

        return t;

    }



}