package webambulanze

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.core.GrantedAuthority

class LogoService {

    // la variabile/proprietà viene iniettata automaticamente
    def grailsApplication

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def springSecurityService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def postaService

    //--registra un evento
    private String setEventoMilite(Livello livello, Evento evento, Milite milite, String dettaglio) {
        String testoFlash

        testoFlash = setBase(livello, evento, milite, null, null, null, dettaglio)
        if (milite) {
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
        return setBase(Livello.warn, evento, null, null, tipoTurno, giorno, '')
    }// fine del metodo

    //--registra un evento
    public String setInfo(Evento evento, Turno turno) {
        return setBase(Livello.info, evento, turno)
    }// fine del metodo

    //--registra un evento generico (molto generico)
    public String setInfo() {
        return setInfo(Evento.generico)
    }// fine del metodo

    //--registra un evento generico (molto generico)
    public String setInfo(Evento evento) {
        return setBase(Livello.info, evento)
    }// fine del metodo

    //--registra un evento generico (molto generico)
    public String setBase(Livello livello, Evento evento) {
        return setBase(Livello.info, evento, (Turno) null)
    }// fine del metodo

    //--registra un evento generico (molto generico)
    public String setBase(Livello livello, Evento evento, Turno turno) {
        String testoFlash = ''
        TipoTurno tipoTurno
        Date giorno

        //--turno e tipoTurno e giorno
        if (turno) {
            tipoTurno = turno.tipoTurno
            giorno = turno.giorno
            testoFlash = setBase(livello, evento, null, turno, tipoTurno, giorno, '')
        }// fine del blocco if

        return testoFlash
    }// fine del metodo

    //--registra un evento generico (molto generico)
    public String setBase(
            Livello livello,
            Evento evento,
            Milite milite,
            Turno turno,
            TipoTurno tipoTurno,
            Date giorno,
            String dettaglio) {
        String testoFlash = evento.avviso
        Logo logo = new Logo()
        Croce croce = null
        def logged
        GrailsUser user
        def currUser
        Utente utente = null
        Set ruoli
        String siglaRuolo
        Ruolo ruolo = null
        GrantedAuthority auth

        //--variabile globale
        if (grailsApplication.mainContext.servletContext.croce) {
            croce = grailsApplication.mainContext.servletContext.croce
        }// fine del blocco if

        //--user della classe mia
        currUser = springSecurityService.getCurrentUser()
        if (currUser instanceof Utente) {
            utente = (Utente) currUser
        }// fine del blocco if

        //--dal plagin
        logged = springSecurityService.getPrincipal()

        if (logged instanceof GrailsUser) {
            user = (GrailsUser) logged
        }// fine del blocco if

        //--ruolo principale
        if (user) {
            ruoli = user.authorities
            if (ruoli) {
                auth = (GrantedAuthority) ruoli.toArray().first()
                siglaRuolo = auth.authority
                ruolo = Ruolo.findByAuthority(siglaRuolo)
            }// fine del blocco if
        }// fine del blocco if

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
