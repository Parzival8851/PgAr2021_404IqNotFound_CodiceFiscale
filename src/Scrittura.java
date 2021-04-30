import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;


/**
 * classe per scrivere il file di output
 */
public class Scrittura {

    public static final String UTF = "utf-8";
    public static final String VERSION = "1.0";
    private static XMLStreamWriter xmlw = null;
    private static XMLOutputFactory outputter = null;

    /**
     *metodo che scrive i tag di apertura e chiusura con i loro dati
     * @param pers
     * @param sbagliati
     * @param spaiati
     */
    public Scrittura(ArrayList<Persona> pers, ArrayList<String> sbagliati, ArrayList<String> spaiati)
    {
        System.out.println("scrittura");
        try
        {
            outputter = XMLOutputFactory.newInstance();
            xmlw = outputter.createXMLStreamWriter(new FileOutputStream("codiciPersone"), UTF);

            xmlw.writeStartDocument(UTF, VERSION);
            xmlw.writeStartElement("output");//scrivo tag radice "output"
            aCapo();
            tab();
            //xmlw.writeComment("Inizio Lista Codici");//scrivo commento
            xmlw.writeStartElement("persone");//scrittura tag persone
            xmlw.writeAttribute("numero", Integer.toString(pers.size()));
            aCapo();
            tab();
            for(int i = 0; i< pers.size();i++)
            {
                xmlw.writeStartElement("persona");//scrittura tag persone
                xmlw.writeAttribute("id", Integer.toString(i));
                aCapo();
                tab();

                xmlw.writeStartElement("nome");//scrittura tag nome
                xmlw.writeCharacters(pers.get(i).getNome());
                xmlw.writeEndElement();
                aCapo();

                xmlw.writeStartElement("cognome");//scrittura tag cognome
                xmlw.writeCharacters(pers.get(i).getCognome());
                xmlw.writeEndElement();
                aCapo();

                xmlw.writeStartElement("sesso");//scrittura tag sesso
                xmlw.writeCharacters(pers.get(i).getSesso());
                xmlw.writeEndElement();
                aCapo();

                xmlw.writeStartElement("comune_nascita");//scrittura tag comune nascita
                xmlw.writeCharacters(pers.get(i).getComuneNascita());
                xmlw.writeEndElement();
                aCapo();

                xmlw.writeStartElement("data_nascita");//scrittura tag data nascita
                xmlw.writeCharacters(pers.get(i).getDataNascita());
                xmlw.writeEndElement();
                aCapo();

                xmlw.writeStartElement("codice_fiscale");//scrittura tag codice fiscale
                xmlw.writeCharacters(pers.get(i).getCodiceFiscale());
                xmlw.writeEndElement();
                aCapo();

                xmlw.writeEndElement(); //chiusura persona
            }

            xmlw.writeEndElement();//chiusura persone

            xmlw.writeStartElement("codici"); // apro tag codici
            aCapo();
            tab();
            scritturaCodice("invalidi", sbagliati, xmlw);
            aCapo();
            tab();
            scritturaCodice("spaiati", spaiati, xmlw);
            aCapo();
            xmlw.writeEndElement(); // chiudo tag codici

            xmlw.writeEndElement();//chiusura output
            xmlw.writeEndDocument();//scrittura fine documento
            xmlw.flush(); //svuota buffer e procede alla scrittura
            xmlw.close(); //chiusura del documento e delle risorse utilizzate

        }catch(Exception e){
            System.out.println("Errore!");
        }

    }

    /**
     * risparmio qualche riga di codice visto che per scrivere i codici spaiati e sbagliati
     * riuso qusi tutto tranne i parametri che passo
     * @param lista
     * @param code
     */
    private static void scritturaCodice(String lista, ArrayList<String> code, XMLStreamWriter xmlw) throws XMLStreamException
    {
        xmlw.writeStartElement(lista); // apro tag lista
        // scrivo attributo "numero" a lista di valore size dell'arraylist
        xmlw.writeAttribute("numero", Integer.toString(code.size()));
        for (String temp : code) // ciclo con tutti i CF sbagliati
        {
            xmlw.writeStartElement("codice"); // apro tag codice

            xmlw.writeCharacters(temp); // scrivo il CF sbagliato
            xmlw.writeEndElement(); // chiudo tag codice
        }
        xmlw.writeEndElement(); // chiudo tag lista
    }

    private void tab(){//metodo per fare \t
        System.out.print("\t");
    }

    private void aCapo(){//metodo per andare a capo
        System.out.println();
    }
}





