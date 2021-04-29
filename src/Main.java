import java.io.FileInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;

public class Main
{


    private static ArrayList<Persona> lista = new ArrayList<Persona>();



    public static void main(String[] args) throws XMLStreamException {
        creaPersone();
        creaCF();
        Controlli control = new Controlli(lista);
        Scrittura script = new Scrittura(lista, control.getCF_Sbagliati(), control.getCF_Spaiati());

    }


    private static void creaCF() throws XMLStreamException {
        // for each elementi di lista, creo l'oggetto CF e lo setto nell'array
        for(Persona temp : lista)
        {
            CodiceFiscale code = new CodiceFiscale(temp);
            temp.setCodiceFiscale(code.getCodice());
        }
    }



    private static void creaPersone() throws XMLStreamException { // crea persone nella lista finché esistono i tag adeguati
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(".../inputPersone.xml", new FileInputStream(".../inputPersone.xml"));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        while(xmlr.hasNext())
        {
            // cerco il tag "persona"
            if(xmlr.isStartElement() && xmlr.getLocalName().equalsIgnoreCase("persona"))
            {
                // leggo il nome
                for (int j = 0; j < 2; j++) xmlr.next();
                String nome=xmlr.getText();
                // leggo il cognome
                for (int j = 0; j < 3; j++) xmlr.next();
                String cognome=xmlr.getText();
                // leggo il sesso
                for (int j = 0; j < 3; j++) xmlr.next();
                String sesso=xmlr.getText();
                // leggo il comune
                for (int j = 0; j < 3; j++) xmlr.next();
                String comuneNascita=xmlr.getText();
                // leggo la data di nascita
                for (int j = 0; j < 3; j++) xmlr.next();
                String dataNascita=xmlr.getText();

                //Persona temp = new Persona();
                lista.add(new Persona(nome, cognome, sesso, comuneNascita, dataNascita));
            }
            else xmlr.next();
        }
    }


}