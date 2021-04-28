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
    private static String nomefile = inputFile();
    private int numero_persone = letturaNumeroPersone(nomefile);
    private ArrayList<Persona> lista = new ArrayList<Persona>();

    public Main() throws XMLStreamException {
    }

    public static void main(String[] args)
    {
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
    private static int letturaNumeroPersone(String nomefile) throws XMLStreamException {
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
            if(xmlr.isStartElement() && xmlr.getLocalName().equals("persone"))
            {
               return Integer.parseInt(xmlr.getAttributeValue(0));
            }
            else xmlr.next();
        }
        return -1;
    }

    private void creaPersoneCF(int n, String nomefile, ArrayList<Persona> pers)
    {
        Persona temp;
        for (int i = 0; i < n; i++)
        {
            temp = new Persona()
            pers.add();
        }
    }
}