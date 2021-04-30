import java.io.FileInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;

public class Main
{


    private static ArrayList<Persona> lista = new ArrayList<>();



    public static void main(String[] args) throws XMLStreamException {
        creaPersone();
        System.out.println("creazione CF");
        creaCF();
        System.out.println("controlli");
        Controlli control = new Controlli(lista);
        System.out.println("scrittura");
        Scrittura script = new Scrittura(lista, control.getCF_Sbagliati(), control.getCF_Spaiati());
        System.out.println("file creato");

    }


    private static void creaCF() throws XMLStreamException {
        // for each elementi di lista, creo l'oggetto CF e lo setto nell'array
        for(Persona temp : lista)
        {
            System.out.println("nuovo CF");
            CodiceFiscale code = new CodiceFiscale(temp);
            System.out.println("nuovo oggetto CF");
            temp.setCodiceFiscale(code.getCodice());
            System.out.println("CF creato");
        }
    }



    private static void creaPersone()
    {
        // crea persone nella lista finché esistono i tag adeguati
        XMLInputFactory xmlif;
        XMLStreamReader xmlr = null;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader("src/inputPersone.xml", new FileInputStream("src/inputPersone.xml"));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }


        try
        {
            while(xmlr.hasNext())
            {
                // cerco il tag "persona"
                if (xmlr.isStartElement() && xmlr.getLocalName().equalsIgnoreCase("persona"))
                {
                    String nome = null, cognome = null, sesso = null, dataNascita = null, comuneNascita = null;

                    // leggo il nome
                    while(!(xmlr.isStartElement() && xmlr.getLocalName().equalsIgnoreCase("nome")))
                    {
                        xmlr.next();

                    }
                    xmlr.next();
                    nome = xmlr.getText();

                    // leggo il cognome
                    while(!(xmlr.isStartElement() && xmlr.getLocalName().equalsIgnoreCase("cognome")))
                    {
                        xmlr.next();

                    }
                    xmlr.next();
                    cognome = xmlr.getText();

                    // leggo il sesso
                    while(!(xmlr.isStartElement() && xmlr.getLocalName().equalsIgnoreCase("sesso")))
                    {
                        xmlr.next();

                    }
                    xmlr.next();
                    sesso = xmlr.getText();

                    // leggo il comune
                    while(!(xmlr.isStartElement() && xmlr.getLocalName().equalsIgnoreCase("comune_nascita")))
                    {
                        xmlr.next();

                    }
                    xmlr.next();
                    comuneNascita = xmlr.getText();

                    // leggo la data di nascita
                    while(!(xmlr.isStartElement() && xmlr.getLocalName().equalsIgnoreCase("data_nascita")))
                    {
                        xmlr.next();

                    }
                    xmlr.next();
                    dataNascita = xmlr.getText();

                    lista.add(new Persona(nome, cognome, sesso, comuneNascita, dataNascita));
                    System.out.println("Persona aggiunta");
                }
                else xmlr.next();
            }
        }
        catch (Exception e)
        {
            System.out.println("Errore: non esiste una nuova riga da leggere\n");
        }

    }


}