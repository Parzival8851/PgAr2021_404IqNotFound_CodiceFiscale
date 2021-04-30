import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *questa classe contiene appunti i vari controlli che permettono di capire se
 * il codice del file xml è giusto, sbagliato o spaiato
 */
public class Controlli {

    /* è un array che contiene i vari codici fiscali che sono spaiati quindi non appartengono  a nessuna persona inserita
     */
    private ArrayList<String> CF_Spaiati = new ArrayList<String>();
    /*è un array che contiene i vari codici fiscali che sono sbagla quindi non idonei ai valori del codice
      fiscale, per esempio ha dei numeri dove ci dovrebbero essere lettere o viceversa,...
     */
    private ArrayList<String> CF_Sbagliati = new ArrayList<String>();

    /**
     * è il get dell'array che contiene i codici sbagliati
     * @return
     */
    public ArrayList<String> getCF_Sbagliati() {
        return CF_Sbagliati;
    }

    /**
     *è il get dell'array che contiene i codici spaiati
     *  @return
     */
    public ArrayList<String> getCF_Spaiati() {
        return CF_Spaiati;
    }

    /**
     *serve a creare ed istanziare la variabile xmlr di tipo XMLStreamReader, che sarà
     * utilizzata per leggere il file XML,e controlla che divide i codici sbagliati,giusti e spaiati
     * @param list
     * @throws XMLStreamException
     */
    public Controlli(ArrayList<Persona> list) throws XMLStreamException {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try
        {
            xmlif = XMLInputFactory.newInstance();
            // capire come passare il file codiciFiscali correttamente
            xmlr = xmlif.createXMLStreamReader("src/codiciFiscali.xml", new FileInputStream("src/codiciFiscali.xml"));
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
                if(temp.length()>5)
                {
                    if(codiceSpaiato(list, temp))
                    {
                        // controllo se è già di una persona altrimenti lo inserisco tra gli spaiati
                        CF_Spaiati.add(temp);
                        System.out.println("CF spaiato");
                    }
                }



            }
            xmlr.next();
        }

        // controllo quali CF negli spaiati sono sbagliati
        for (int i = 0; i < CF_Spaiati.size(); i++)
        {
            // controllo i-esimo codice
            if(!codiceCorretto(CF_Spaiati.get(i)))
            {
                // se il CF è sbagliato lo aggiungo a CF_sbagliati
                CF_Sbagliati.add(CF_Spaiati.get(i));
                // e lo rimuovo da spaiati
                CF_Spaiati.remove(i);

                System.out.println("CF sbagliato");
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
                && charControlloCorretto(codice) && controlloNumGiorni(codice))
            return true;
        return false;
    }

    /**
     *con questo metodo controllo se un codice è sbagliato rispetto alle regole che identificano un codice fiscale
     *  @param codice
     * @return
     */
private boolean controlloPosizioni(String codice){
        int i;
        String temp;
        if(codice.length()!=16)//controllo che il codice abbia il giusto numero di caratteri (magari è superfluo ops)
            return false;
        for(i=0;i<6;i++){//controllo che nome e cognome siano lettere
            if(Pattern.matches("[a-zA-Z]+", String.valueOf(codice.charAt(i))) == false )
                return false;
        }
        //creo una stringa formata da tutti i caratteri del codice che dovrebbero essere stringhe
        temp= String.valueOf(codice.charAt(6))+String.valueOf(codice.charAt(7))+String.valueOf(codice.charAt(9))+
                String.valueOf(codice.charAt(10))+String.valueOf(codice.charAt(12))+String.valueOf(codice.charAt(13))+
                String.valueOf(codice.charAt(14));
        if(!isIntero(temp))//controllo che la stringa creata sopra sia di soli interi
            return false;
        //controllo che nelle posizioni restanti ci siano solo lettere
        if(Pattern.matches("[a-zA-Z]+", String.valueOf(codice.charAt(8))) == false )
            return false;
        if(Pattern.matches("[a-zA-Z]+", String.valueOf(codice.charAt(11))) == false)
        return false;
        if(Pattern.matches("[a-zA-Z]+", String.valueOf(codice.charAt(15))) == false)
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

         // leggo l'ultimo carattere della stringa da controllare
         char ultimo=codice.charAt(15);
         CodiceFiscale temp = new CodiceFiscale(codice);
         // calcolo il carattere corretto e comparo

         String last = temp.getCodice();
         //String calc = temp.calcolaCodiceControllo(codice);
         if (String.valueOf(ultimo).equalsIgnoreCase(last))
             return true;
         return false;
     }


    /**
     * metodo che controlla che la stringa sia formata da interi
     * @param input
     * @return
     */
    private boolean isIntero( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }

    /**
     *metodo che controlla se il giorno è valido quindi compreso tra 1 e 31 o tra 41 e 71
     *  @param codice
     * @return
     */
    private boolean giornoValido(String codice){
        String temp;

        temp= String.valueOf(codice.charAt(9)) + String.valueOf(codice.charAt(10));//creo un intero con all'interno le cifre che corrispondono al giorno

        int calc = Integer.valueOf(temp);
        if((calc<1||calc>71))//faccio i controlli e viene ritornato true solo se corretto
            return false;
        return calc <= 31 || calc >= 41;
    }

    /**
     * metodo che controlla che il la lettera inserita per il mese sia valido in base ai criteri prediposti
     * @param codice
     * @return
     */
    private boolean meseValido(String codice){
         if(codice.charAt(8)!='A' && codice.charAt(8)!='B' && codice.charAt(8)!='C' && codice.charAt(8)!='D' &&
                 codice.charAt(8)!='E' && codice.charAt(8)!='H' && codice.charAt(8)!='L' && codice.charAt(8)!='M' &&
                 codice.charAt(8)!='P' && codice.charAt(8)!='R' && codice.charAt(8)!='S' && codice.charAt(8)!='T' )
             return false;
         return true;

    }

    /**
     *controllo che il numero di giorni sia sensato per ogni mese
     *  @param codice
     * @return
     */
    private boolean controlloNumGiorni(String codice) {
        int temp;
        String calc = String.valueOf(codice.charAt(9)) + String.valueOf(codice.charAt(10));

        temp = Integer.valueOf(calc); //creo un intero che corrisponde al giorno di nascita
        //gender free
        char mese = codice.charAt(8);//per comodità mi salvo il mese in un carattere
        if (mese == 'A' || mese == 'C' || mese == 'E' || mese == 'L' || mese == 'M' || mese == 'R' || mese == 'T') {
            if (temp < 1 || temp > 71)
                return false;
            if (temp > 31 && temp < 41)
                return false;
            return true;
        }
        if (mese == 'B') {
            if (temp < 1 || temp > 68)
                return false;
            if (temp > 28 && temp < 41)
                return false;
            return true;
        }
        if (mese == 'D' || mese == 'H' || mese == 'S' || mese == 'P') {
            if (temp < 1 || temp > 70)
                return false;
            if (temp > 30 && temp < 41)
                return false;
            return true;
        }
        return false;
    }

    /**
     * il codice fiscale viene paragonato a quello delle persone inserite per vedere se è esistente altrimenti è
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
        // nessun persona ha il CF che sto controllando, NON è sbagliato -> è spaiato
        return true;
    }
}
