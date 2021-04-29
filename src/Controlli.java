import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Controlli {

    private ArrayList<String> CF_Spaiati = new ArrayList<String>();
    private ArrayList<String> CF_Sbagliati = new ArrayList<String>();

    public ArrayList<String> getCF_Sbagliati() {
        return CF_Sbagliati;
    }

    public ArrayList<String> getCF_Spaiati() {
        return CF_Spaiati;
    }

    public Controlli(ArrayList<Persona> list) throws XMLStreamException {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try
        {
            xmlif = XMLInputFactory.newInstance();
            // capire come passare il file codiciFiscali correttamente
            xmlr = xmlif.createXMLStreamReader(nomefile, new FileInputStream(nomefile));
        }
        catch(Exception e)
        {
            System.out.println("Errore nell'inizializzazione del reader");
            System.out.println(e.getMessage());
        }

        while(xmlr.hasNext())
        {
            if(xmlr.isCharacters())
            {
                // salvo la stringa che contiene il codice da controllare
                String temp = xmlr.getText();
                if(!codiceCorretto(temp))
                {
                    // controllo se è corretto, altrimenti lo inserisco tra gli sbagliati
                    CF_Sbagliati.add(temp);
                }
                else(codiceSpaiato(list, temp))
                {
                    // controllo se è già di una persona altrimenti lo inserisco tra gli spaiati
                    CF_Spaiati.add(temp);
                }

            }
        }
    }

    /**
     * Viene passato il codice fiscale dal file xml e controllo la sua correttezza
     * @param codice
     * @return
     */
    public boolean codiceCorretto(String codice){
        //faccio tutti i controlli dati dalla consegna e ritorno true se ogni metodo è true
        if(controlloPosizioni(codice) && giornoValido(codice) && meseValido(codice)
                && charControlloCorretto(codice) && controlloNumGiorni(codice) && nomeCognomeValidi(codice))
            return true;
        return false;
    }

private boolean controlloPosizioni(String codice){
        int i;
        String temp;
        if(codice.length()!=16)//controllo che il codice abbia il giusto numero di caratteri (magari è superfluo ops)
            return false;
        for(i=0;i<6;i++){//controllo che nome e cognome siano lettere
            if(codice.charAt(i)<'a'||codice.charAt(i)>'Z')
                return false;
        }
        //creo una stringa formata da tutti i caratteri del codice che dovrebbero essere stringhe
        temp= String.valueOf(codice.charAt(6))+String.valueOf(codice.charAt(7))+String.valueOf(codice.charAt(9))+
                String.valueOf(codice.charAt(10))+String.valueOf(codice.charAt(12))+String.valueOf(codice.charAt(13))+
                String.valueOf(codice.charAt(14));
        if(!isIntero(temp))//controllo che la stringa creata sopra sia di soli interi
            return false;
        //controllo che nelle posizioni restanti ci siano solo lettere
        if(codice.charAt(8)<'a'||codice.charAt(8)>'Z')
            return false;
        if(codice.charAt(11)<'a'||codice.charAt(11)>'Z')
        return false;
        if(codice.charAt(15)<'a'||codice.charAt(15)>'Z')
        return false;

        return true;//ritorno true solo se arrivo in fondo al metodo, cioè se il codice è corretto

}

    /**
     * passato il codice da controllare, leggo l'ultimo carattere e lo comparo a quello corretto calcolato da CF
     * @param codice
     * @return boolean
     */
     private boolean charControlloCorretto(String codice)
     {
         // oggetto di appoggio CF
         CodiceFiscale temp = null;
         // leggo l'ultimo carattere della stringa da controllare
         char ultimo=codice.charAt(15);
         // calcolo il carattere corretto e comparo
         assert false;
         return Character.toString(ultimo).equalsIgnoreCase(temp.calcolaCodiceControllo(codice));
     }



    private boolean isIntero( String input ) {//metodo che controlla che la stringa sia formata solo da interi
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }

    private boolean giornoValido(String codice){
        int temp;

        temp= (int) codice.charAt(9) + (int) codice.charAt(10);//creo un intero con all'interno le cifre che corrispondono al giorno

        if((temp<1||temp>71))//faccio i controlli e viene ritornato true solo se corretto
            return false;
        return temp <= 31 || temp >= 41;
    }

    private boolean meseValido(String codice){
         if(codice.charAt(8)!='A' || codice.charAt(8)!='B' || codice.charAt(8)!='C' || codice.charAt(8)!='D' ||
                 codice.charAt(8)!='E' || codice.charAt(8)!='H' || codice.charAt(8)!='L' || codice.charAt(8)!='M' ||
                 codice.charAt(8)!='P' || codice.charAt(8)!='R' || codice.charAt(8)!='S' || codice.charAt(8)!='T' )
             return false;
         return true;

    }

    private boolean controlloNumGiorni(String codice){//controllo che il numero di giorni sia sensato per ogni mese
         int temp;
        temp= (int) codice.charAt(9) + (int) codice.charAt(10);//creo un intero che corrisponde al giorno di nascita
        //gender free
        char mese = codice.charAt(8);//per comodità mi salvo il mese in un carattere
        if(mese=='A' || mese=='C' || mese=='E' || mese=='L' || mese=='M' || mese=='R' || mese=='T'){
            if (temp<1||temp>71)
                return false;
            if(temp>31 && temp < 41)
                return false;
            return true;
        }
        if(mese=='B'){
            if (temp<1||temp>68)
                return false;
            if(temp>28 && temp < 41)
                return false;
            return true;
        }
        if(mese=='D'||mese=='H' || mese=='S' || mese=='P'){
            if (temp<1||temp>70)
                return false;
            if(temp>30 && temp < 41)
                return false;
            return true;
        }
        return false;
    }

    private boolean nomeCognomeValidi(String codice){

    }



    /**
     * il codice fiscale viene paragonato a quello delle persone inserite per vedere se è esistente altriementi è
     * sicuramente spaiato, perché prima controllo se è corretto
     * @param codice
     * @return
     */
    public boolean codiceSpaiato(ArrayList<Persona> list, String codice)
    {
        for(Persona temp : list)
        {
            // se il CF esiste tra le persone esco, altrimenti è spaiato
            if (temp.getCodiceFiscale().equalsIgnoreCase(codice)) return false;
        }
        return true;



    }
}
