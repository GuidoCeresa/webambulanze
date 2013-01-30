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
    public void setWarn(Evento evento, Turno turno) {
        setBase(Livello.warn, evento, turno)
    }// fine del metodo

    //--registra un evento
    public void setWarn(Evento evento, TipoTurno tipoTurno, Date giorno) {
        setBase(Livello.warn, evento, null, null, tipoTurno, giorno)
    }// fine del metodo

    //--registra un evento
    public void setInfo(Evento evento, Turno turno) {
        setBase(Livello.info, evento, turno)
    }// fine del metodo

    //--registra un evento generico (molto generico)
    public void setInfo() {
        setInfo(Evento.generico)
    }// fine del metodo

    //--registra un evento generico (molto generico)
    public void setInfo(Evento evento) {
        setBase(Livello.info, evento)
    }// fine del metodo

    //--registra un evento generico (molto generico)
    public void setBase(Livello livello, Evento evento) {
        setBase(Livello.info, evento, (Turno) null)
    }// fine del metodo

    //--registra un evento generico (molto generico)
    public void setBase(Livello livello, Evento evento, Turno turno) {
        TipoTurno tipoTurno = null
        Date giorno = null

        //--turno e tipoTurno e giorno
        if (turno) {
            tipoTurno = turno.tipoTurno
            giorno = turno.giorno
            setBase(livello, evento, null, turno, tipoTurno, giorno)
        }// fine del blocco if

    }// fine del metodo

    //--registra un evento generico (molto generico)
    public void setBase(Livello livello, Evento evento, Milite milite, Turno turno, TipoTurno tipoTurno, Date giorno) {
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
        logo.dettaglio = ''
        logo.save(flush: true)

        postaService.sendLogoMail(logo)
    }// fine del metodo

} // end of Service Class
