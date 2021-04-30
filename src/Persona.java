/**
 * questa classe serve per identificare le variabile di Persona con i loro get e set
 */
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
     * @param _nome
     * @param _cognome
     * @param _sesso
     * @param _comuneNascita
     * @param _dataNascita
     */
    public Persona(String _nome,String _cognome,String _sesso,String _comuneNascita,String _dataNascita)
    {
        this.nome=_nome;
        this.cognome=_cognome;
        this.sesso=_sesso;
        this.dataNascita=_dataNascita;
        this.comuneNascita=_comuneNascita;
    }
}
