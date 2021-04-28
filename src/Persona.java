import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
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
     * @param n
     */

    public Persona(String nomefile)
    {
        String i = Integer.toString(n);
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
        int find=0;
        while(find==0)
        {
            if(xmlr.hasNext())
            {
                switch(xmlr.getEventType())
                {
                    case XMLStreamConstants.START_ELEMENT: //sono nella persona giusta, la n-esima
                        if (xmlr.getLocalName().equals("persona") && xmlr.getAttributeValue(0).equals(i))
                        {
                            xmlr.next();
                            this.nome=xmlr.getText();
                            xmlr.next();
                            this.cognome=xmlr.getText();
                            xmlr.next();
                            this.sesso=xmlr.getText();
                            xmlr.next();
                            this.comuneNascita=xmlr.getText();
                            xmlr.next();
                            this.dataNascita=xmlr.getText();
                        }
                }
            }
        }

    }
}
