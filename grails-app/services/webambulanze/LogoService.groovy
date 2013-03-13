package webambulanze

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.core.GrantedAuthority

class LogoService {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def springSecurityService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    //--registra un evento
    private String setEventoMilite(Livello livello, Evento evento, Milite milite, String dettaglio) {
        String testoFlash = ''
        Croce croce

        if (milite) {
            croce = milite.croce
            testoFlash = setBase(croce, livello, evento, milite, null, null, null, dettaglio)
            testoFlash += ' milite ' + milite.nome + ' ' + milite.cognome
        }// fine del blocco if

        return testoFlash
    }// fine del metodo

    //--registra un evento
    public String setInfo(Evento evento, Milite milite) {
        return setInfo(evento, milite, '')
    }// fine del metodo

    //--registra un evento
    public String setInfo(Evento evento, Milite milite, String dettaglio) {
        return setEventoMilite(Livello.info, evento, milite, dettaglio)
    }// fine del metodo

    //--registra un evento
    public String setWarn(Evento evento, Milite milite) {
        return setWarn(evento, milite, '')
    }// fine del metodo

    //--registra un evento
    public String setWarn(Evento evento, Milite milite, String dettaglio) {
        return setEventoMilite(Livello.warn, evento, milite, dettaglio)
    }// fine del metodo

    //--registra un evento
    public String setWarn(Evento evento, Turno turno) {
        return setBase(Livello.warn, evento, turno)
    }// fine del metodo

    //--registra un evento
    public String setWarn(Evento evento, TipoTurno tipoTurno, Date giorno) {
        Croce croce

        if (tipoTurno) {
            croce = tipoTurno.croce
            return setBase(croce, Livello.warn, evento, null, null, tipoTurno, giorno, '')
        } else {
            return ''
        }// fine del blocco if-else

    }// fine del metodo

    //--registra un evento
    public String setInfo(Evento evento, Turno turno) {
        return setBase(Livello.info, evento, turno)
    }// fine del metodo

    //--registra un evento generico (molto generico)
    public String setInfo(def request, Evento evento) {
        return setBase(croceService.getCroce(request), Livello.info, evento)
    }// fine del metodo

    //--registra un evento generico (molto generico)
    public String setInfo(Croce croce, Evento evento) {
        return setBase(croce, Livello.info, evento)
    }// fine del metodo

    //--registra un evento generico (molto generico)
    public String setBase(Croce croce, Livello livello, Evento evento) {
        return setBase(croce, livello, evento, (Milite) null, (Turno) null, (TipoTurno) null, (Date) null, '')
    }// fine del metodo

    //--registra un evento generico (molto generico)
    public String setBase(Livello livello, Evento evento, Turno turno) {
        String testoFlash = ''
        Croce croce
        TipoTurno tipoTurno
        Date giorno

        //--turno e tipoTurno e giorno
        if (turno) {
            croce = turno.croce
            tipoTurno = turno.tipoTurno
            giorno = turno.giorno
            testoFlash = setBase(croce, livello, evento, null, turno, tipoTurno, giorno, '')
        }// fine del blocco if

        return testoFlash
    }// fine del metodo

    //--registra un evento generico (molto generico)
    public String setBase(
            Croce croce,
            Livello livello,
            Evento evento,
            Milite milite,
            Turno turno,
            TipoTurno tipoTurno,
            Date giorno,
            String dettaglio) {
        String testoFlash = evento.avviso
        Logo logo = new Logo()
        def logged
        GrailsUser user
        def currUser
        Utente utente = null
        Set ruoli
        String siglaRuolo
        Ruolo ruolo = null
        GrantedAuthority auth

        //--user della classe mia
        currUser = springSecurityService.getCurrentUser()
        if (currUser instanceof Utente) {
            utente = (Utente) currUser
        }// fine del blocco if

        //--ruolo principale
        ruolo = croceService.getMaxRuolo()

        logo.croceLogo = croce
        logo.time = new Date().toTimestamp()
        logo.utente = utente
        logo.ruolo = ruolo
        logo.evento = evento
        logo.livello = livello
        logo.milite = milite
        logo.tipoTurno = tipoTurno
        logo.turno = turno
        logo.giorno = giorno
        logo.dettaglio = dettaglio
        logo.save(flush: true)

        //       postaService.sendLogoMail(logo)

        return testoFlash
    }// fine del metodo

} // end of Service Class
