import java.io.FileInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;

public class Main
{
    public static final String MSG_INPUT_PERSONE = "Inserire percorso del file di input persone:";
    private static String nomefile = inputFile();
    private int numero_persone = letturaNumeroPersone(nomefile);
    private ArrayList<Persona> lista = new ArrayList<Persona>();

    public static void main(String[] args)
    {
    }

    private static String inputFile() // richiedo il percorso del file di input delle persone
    {
       return InputDati.leggiStringaNonVuota(MSG_INPUT_PERSONE);
    }

    private static int letturaNumeroPersone(String nomefile)
    {
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
            if(xmlr.isStartElement() && xmlr.getLocalName().equals("persone"))
            {
               return Integer.parseInt(xmlr.getAttributeValue(0));
            }
            else xmlr.next();
        }



    }
}