import javax.management.BadAttributeValueExpException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public class CodiceFiscale
{
    private String codice;

    //getter del codice
    public String getCodice() {
        return codice;
    }

    /**
     * Metodo per calcolare, data la data di nascita, la sua corrispettiva nella forma per il codice fiscale
     * @param data
     *
     */
    public String calcolaData (String data) {
        int anno, giorno;
        String mese;
        anno = data.charAt(2) + data.charAt(3); //calcolo l'anno che corrisponde alle cifre in posizione 2 e 3
        giorno = data.charAt(8) + data.charAt(9);//calcolo il giorno che corrisponde alle cifre in posizione 8 e 9

        if (*/è una donna */){//se si tratta di una donna aggiungo 40 al giorno calcolato sopra
            giorno = giorno + 40;
        }
        mese=String.valueOf(data.charAt(5))+ String.valueOf(data.charAt(6));/*Trasformo il mese in formato stringa
                                                                            per poi passarlo al metodo successivo*/
        mese=calcolaMese(mese);
        return anno+mese+giorno;//viene ritornata una stringa che contiene anno, mese e giorno concatenati
    }

    /**
     * Viene passato il mese nella forma a due cifre e viene ritornato il corrispettivo per il codice fiscale
     * @param mese
     *
     */
    private String calcolaMese(String mese){
        //serie di if per abbinare il mese alla corrispettiva lettera
        char a = mese.charAt(0); //variabile "comoda" per fare il controllo seguente
        if(a=='0'){ /*if per diminuire (poco) la complessità, controlla la prima cifra in modo da escludere gli ultimi
                                        3 mesi dell'anno, che vengono controllati nell'else*/
            if (mese.equals("01"))
                mese = "A";

            if (mese.equals("02"))
                mese = "B";

            if (mese.equals("03"))
                mese = "C";

            if (mese.equals("04"))
                mese = "D";

            if (mese.equals("05"))
                mese = "E";

            if (mese.equals("06"))
                mese = "H";

            if (mese.equals("07"))
                mese = "L";

            if (mese.equals("08"))
                mese = "M";

            if (mese.equals("09"))
                mese = "P";

        }
        else{
            if (mese.equals("10"))
                mese = "R";

            if (mese.equals("11"))
                mese = "S";

            if (mese.equals("12"))
                mese = "T";
        }
        return mese;//viene ritornato il mese nella versione da codice fiscale
    }

    public String calcolaComune(String comune){
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nomefile, new FileInputStream(nomefile)); } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage()); }

        return comune;
    }






    /**
        * metodo per deciciedere le tre lettere che andranno nel codice fiscale
        * @param cognome
        */
   public String calcolaCognome (String cognome) {
       char[] carattere = new char[3];
       int conta=0;
       //il for serve per prelevare le consonanti del cognome e se si trovano 3 consonanti il ciclo si interrompe
       for (int i = 0; i < cognome.length(); i++) {
           if (!(cognome.charAt(i) == 'A' && cognome.charAt(i) == 'E' && cognome.charAt(i) == 'I' && cognome.charAt(i)== 'O' && cognome.charAt(i) == 'U')){

               carattere[conta] = cognome.charAt(conta);
               conta++;
           }
           //il conta serve per far capire se ci sono abbastanza consonanti nel cognome e per posizionare le lettere
           if (conta == 3)
               break;
       }
       //utilezzerò questo if se le consonanti non sono abbastanza(perchè le vocali vanno dopo le consonati)
       if (conta != 3) {
           for (int j=conta; j < cognome.length();j++){
               if (cognome.charAt(j) == 'A' || cognome.charAt(j) == 'E' || cognome.charAt(j) == 'I' || cognome.charAt(j) == 'O' || cognome.charAt(j) == 'U'){
                   carattere[conta] = cognome.charAt(conta);
                   conta++;
               }
           }
       }
       //questi if servono per mettere le X se il cognome dovesse essere troppo corto
       if (conta == 1) carattere[1] = 'X';
       if (conta == 2) carattere[2]= 'X';

       cognome=String.valueOf(carattere);
        return cognome;
   }
}
