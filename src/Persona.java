import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public class Persona
{
    private String nome;
    private String cognome;
    private String dataNascita;
    private String comuneNascita;
    private String sesso;
    private String codiceFiscale;

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getSesso() {
        return sesso;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getCognome() {
        return cognome;
    }

    public String getComuneNascita() {
        return comuneNascita;
    }


    public String getDataNascita() {
        return dataNascita;
    }

    public String getNome() {
        return nome;
    }


    /**
     *
     * @param nomefile
     *
     */

    public Persona(String nomefile, int i) throws XMLStreamException {
        // cerco i dati della persona i-esima
        // inizializzzazione lettore
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try
        {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nomefile, new FileInputStream(nomefile));
        }
        catch(Exception e)
        {
            System.out.println("Errore nell'inizializzazione del reader");
            System.out.println(e.getMessage());
        }

        while(xmlr.hasNext())
        {
            // cerco il tag "persona"
            if(xmlr.isStartElement() && xmlr.getLocalName().equalsIgnoreCase("persona"))
            {
                // cerco la i-esima persona
                if(xmlr.getAttributeValue(0).equalsIgnoreCase(Integer.toString(i)))
                {
                    // leggo il nome
                    for (int j = 0; j < 2; j++) xmlr.next();
                    this.nome=xmlr.getText();
                    // leggo il cognome
                    for (int j = 0; j < 3; j++) xmlr.next();
                    this.cognome=xmlr.getText();
                    // leggo il sesso
                    for (int j = 0; j < 3; j++) xmlr.next();
                    this.sesso=xmlr.getText();
                    // leggo il comune
                    for (int j = 0; j < 3; j++) xmlr.next();
                    this.comuneNascita=xmlr.getText();
                    // leggo la data di nascita
                    for (int j = 0; j < 3; j++) xmlr.next();
                    this.dataNascita=xmlr.getText();
                }
            }
        }



    }
}
