import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public class Persona
{
    private String nome;
    private String cognome;
    private String data;
    private String dataNascita;
    private String comuneNascita;

    public String getCognome() {
        return cognome;
    }

    public String getComuneNascita() {
        return comuneNascita;
    }

    public String getData() {
        return data;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public String getNome() {
        return nome;
    }

    public Persona(String nomefile, int n)
    {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        xmlif = XMLInputFactory.newInstance();
        xmlr = xmlif.createXMLStreamReader(nomefile, new FileInputStream(nomefile));


    }
}
