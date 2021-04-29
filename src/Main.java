import java.io.FileInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;

public class Main
{
    public static final String MSG_INPUT_PERSONE = "Inserire percorso del file di input persone:";
    public static final String PERSONE = "persone";
    private static String nomefile = inputFile();
    private static int numero_persone = letturaNumeroCiclo(nomefile, PERSONE);


    private static ArrayList<Persona> lista = new ArrayList<Persona>();



    public static void main(String[] args) throws XMLStreamException {
        creaPersone(numero_persone, nomefile, lista);
        creaCF(lista, nomefile);
        Controlli control = new Controlli(lista);

    }


    private static void creaCF(ArrayList<Persona> pers, String nomefile) throws XMLStreamException {
        // for each elementi di lista, creo l'oggetto CF e lo setto nell'array
        for(Persona temp : pers)
        {
            CodiceFiscale code = new CodiceFiscale(temp, nomefile);
            temp.setCodiceFiscale(code.getCodice());
        }
    }

    /**
     *  richiedo il percorso del file di input delle persone
     *
     * @return
     */
    private static String inputFile()
    {
       return InputDati.leggiStringaNonVuota(MSG_INPUT_PERSONE);
    }


    /**
     *
     * @param nomefile
     * @return
     * @throws XMLStreamException
     */
    private static int letturaNumeroCiclo(String nomefile, String nomeCiclo) throws XMLStreamException {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nomefile, new FileInputStream(nomefile));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        while(xmlr.hasNext())
        {
            //leggo il tag "persone" e prendo il valore convertito in int del suo attributo
            if(xmlr.isStartElement() && xmlr.getLocalName().equals(nomeCiclo))
            {
               return Integer.parseInt(xmlr.getAttributeValue(0));
            }
            else xmlr.next();
        }
        return -1;
    }

    private static void creaPersone(int n, String nomefile, ArrayList<Persona> pers) throws XMLStreamException {
        // creo n persone nell'arraylist
        Persona temp;
        for (int i = 0; i < n; i++) {
            temp = new Persona(nomefile, i);
            pers.add(temp);
        }
    }

}