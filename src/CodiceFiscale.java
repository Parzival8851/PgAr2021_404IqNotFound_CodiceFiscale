import javax.management.BadAttributeValueExpException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.Locale;

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
    private String calcolaData (String data, String sesso) {
        int anno, giorno;
        String mese;
        anno = data.charAt(2) + data.charAt(3); //calcolo l'anno che corrisponde alle cifre in posizione 2 e 3
        giorno = data.charAt(8) + data.charAt(9);//calcolo il giorno che corrisponde alle cifre in posizione 8 e 9

        if (sesso.equalsIgnoreCase("F")){//se si tratta di una donna aggiungo 40 al giorno calcolato sopra
            giorno = giorno + 40;
        }
        mese=String.valueOf(data.charAt(5)) + String.valueOf(data.charAt(6));/*Trasformo il mese in formato stringa
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


    /**
     *
     * @param comune
     * @return
     * @throws XMLStreamException
     */
    private String calcolaComune(String comune, String nomefile) throws XMLStreamException {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try
        {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nomefile, new FileInputStream(nomefile));
        }
        catch (Exception e)
        {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        while(xmlr.hasNext())
        {
            if(xmlr.hasText() && xmlr.getText().equalsIgnoreCase(comune))
            {
                // leggo finché non sono in un testo e il testo è il comune che mi interessa
                for (int i = 0; i < 3; i++)
                {
                    // mi sposto all'evento dove è contenuto il codice del comune
                    xmlr.next();
                }
                return xmlr.getText();
            }
        }

        // nel caso non trovi il comune ritorna un errore
        return "-1";
    }

    /**
     *Per il calcolo del codice di controllo sommiamo i valori a cui corrispondono
     * i caratteri alfanumerici che si trovano in posizione dispari
     * e da un'altra quelli che si trovano in posizione pari
     * attraverso le tabelle che sono contenute negli switch
     * @param codice
     * @return
     */
    public String calcolaCodiceControllo(String codice)
    {
        // sommo i valori a cui corrispondono i valori in posizione pari
        int sommaPari=0;
        for (int i=0;i<=14;i+=2) {
            switch (codice.charAt(i)) {
                case '0': sommaPari+=0;break;
                case '1': sommaPari+=1;break;
                case '2': sommaPari+=2;break;
                case '3': sommaPari+=3;break;
                case '4': sommaPari+=4;break;
                case '5': sommaPari+=5;break;
                case '6': sommaPari+=6;break;
                case '7': sommaPari+=7;break;
                case '8': sommaPari+=8;break;
                case '9': sommaPari+=9;break;
                case 'A': sommaPari+=0;break;
                case 'B': sommaPari+=1;break;
                case 'C': sommaPari+=2;break;
                case 'D': sommaPari+=3;break;
                case 'E': sommaPari+=4;break;
                case 'F': sommaPari+=5;break;
                case 'G': sommaPari+=6;break;
                case 'H': sommaPari+=7;break;
                case 'I': sommaPari+=8;break;
                case 'J': sommaPari+=9;break;
                case 'K': sommaPari+=10;break;
                case 'L': sommaPari+=11;break;
                case 'M': sommaPari+=12;break;
                case 'N': sommaPari+=13;break;
                case 'O': sommaPari+=14;break;
                case 'P': sommaPari+=15;break;
                case 'Q': sommaPari+=16;break;
                case 'R': sommaPari+=17;break;
                case 'S': sommaPari+=18;break;
                case 'T': sommaPari+=19;break;
                case 'U': sommaPari+=20;break;
                case 'V': sommaPari+=21;break;
                case 'W': sommaPari+=22;break;
                case 'X': sommaPari+=23;break;
                case 'Y': sommaPari+=24;break;
                case 'Z': sommaPari+=25;break;
            }
        }
        // sommo i valori a cui corrispondono i valori in posizione dipari
        int sommaDispari=0;
        for (int i=1;i<=15;i+=2) {
            switch (codice.charAt(i)) {
                case '0': sommaDispari+=1;break;
                case '1': sommaDispari+=0;break;
                case '2': sommaDispari+=5;break;
                case '3': sommaDispari+=7;break;
                case '4': sommaDispari+=9;break;
                case '5': sommaDispari+=13;break;
                case '6': sommaDispari+=15;break;
                case '7': sommaDispari+=17;break;
                case '8': sommaDispari+=19;break;
                case '9': sommaDispari+=21;break;
                case 'A': sommaDispari+=1;break;
                case 'B': sommaDispari+=0;break;
                case 'C': sommaDispari+=5;break;
                case 'D': sommaDispari+=7;break;
                case 'E': sommaDispari+=9;break;
                case 'F': sommaDispari+=13;break;
                case 'G': sommaDispari+=15;break;
                case 'H': sommaDispari+=17;break;
                case 'I': sommaDispari+=19;break;
                case 'J': sommaDispari+=21;break;
                case 'K': sommaDispari+=2;break;
                case 'L': sommaDispari+=4;break;
                case 'M': sommaDispari+=18;break;
                case 'N': sommaDispari+=20;break;
                case 'O': sommaDispari+=11;break;
                case 'P': sommaDispari+=3;break;
                case 'Q': sommaDispari+=6;break;
                case 'R': sommaDispari+=8;break;
                case 'S': sommaDispari+=12;break;
                case 'T': sommaDispari+=14;break;
                case 'U': sommaDispari+=16;break;
                case 'V': sommaDispari+=10;break;
                case 'W': sommaDispari+=22;break;
                case 'X': sommaDispari+=25;break;
                case 'Y': sommaDispari+=24;break;
                case 'Z': sommaDispari+=23;break;
            }
        }
        // calcolo il codice finale con il resto della divisione tra la somma dei valori diviso 26
        int interoControllo = (sommaPari+sommaDispari)%26;
        String carattereControllo="";
        switch (interoControllo) {
            case 0:carattereControllo="A";break;
            case 1: carattereControllo="B";break;
            case 2: carattereControllo="C";break;
            case 3: carattereControllo="D";break;
            case 4: carattereControllo="E";break;
            case 5: carattereControllo="F";break;
            case 6: carattereControllo="G";break;
            case 7: carattereControllo="H";break;
            case 8: carattereControllo="I";break;
            case 9: carattereControllo="J";break;
            case 10: carattereControllo="K";break;
            case 11: carattereControllo="L";break;
            case 12: carattereControllo="M";break;
            case 13: carattereControllo="N";break;
            case 14: carattereControllo="O";break;
            case 15: carattereControllo="P";break;
            case 16: carattereControllo="Q";break;
            case 17: carattereControllo="R";break;
            case 18: carattereControllo="S";break;
            case 19: carattereControllo="T";break;
            case 20: carattereControllo="U";break;
            case 21: carattereControllo="V";break;
            case 22: carattereControllo="W";break;
            case 23: carattereControllo="X";break;
            case 24: carattereControllo="Y";break;
            case 25: carattereControllo="Z";break;
        }

        return carattereControllo;
    }

    public CodiceFiscale(Persona pers, String nomefile) throws XMLStreamException {
        // creo il codice fino alla penultima lettera
        String codice=calcolaCognome(pers.getCognome())+calcolaCognome(pers.getNome())+calcolaData(pers.getDataNascita(), pers.getSesso())+calcolaComune(pers.getComuneNascita(), nomefile);
        // aggiungo il codice di controllo;
        this.codice= codice+calcolaCodiceControllo(codice);
    }




    /**
        * metodo per deciciedere le tre lettere che andranno nel codice fiscale
        * @param cognome
        */
   private String calcolaCognome (String cognome) { // questo metodo lo chiamo sia per nome che per cognome
       char[] carattere = new char[3];
       int conta=0;
       //il for serve per prelevare le consonanti del cognome e se si trovano 3 consonanti il ciclo si interrompe
       for (int i = 0; i < cognome.length(); i++) {
           if (!(cognome.charAt(i) == 'A' && cognome.charAt(i) == 'E' && cognome.charAt(i) == 'I' && cognome.charAt(i)== 'O' && cognome.charAt(i) == 'U')){

               carattere[conta] = cognome.charAt(conta);
               conta++;
           }
           //il conta serve per far capire se ci sono abbastanza consonanti nel cognome e per posizionare le lettere
           if (conta == 2)
               break;
       }
       //utilezzerò questo if se le consonanti non sono abbastanza(perchè le vocali vanno dopo le consonati)
       if (conta != 2) {
           for (int j=conta; j < cognome.length();j++){
               if (cognome.charAt(j) == 'A' || cognome.charAt(j) == 'E' || cognome.charAt(j) == 'I' || cognome.charAt(j) == 'O' || cognome.charAt(j) == 'U'){
                   carattere[conta] = cognome.charAt(conta);
                   conta++;
               }
           }
       }
       //questo if serve per mettere le X se il cognome dovesse essere troppo corto

       if (conta == 1) carattere[2]= 'X';

       cognome=String.valueOf(carattere);//trasformo i caratteri salvati in una stringa per comodità
        return cognome;
   }
}
