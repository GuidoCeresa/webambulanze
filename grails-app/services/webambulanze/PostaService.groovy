package webambulanze

import org.springframework.scheduling.annotation.Async

class PostaService {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def mailService

    //--log postale
    @Async
    public void sendLogoMail(Logo logo) {
        boolean usaPosta = true
        String mailTo = 'guidoceresa@me.com'
        String oggetto = ''
        String testo = ''

        if (logo && logo.livello == Livello.warn) {
            oggetto = 'Gestione ambulanze ' + logo.croceLogo
            testo += 'Alle ore '
            testo += logo.time
            testo += ", il milite "
            testo += logo.utente
            testo += ' ha '
            testo += logo.evento
            testo += ' di '
            testo += logo.tipoTurno.descrizione
            testo += ' del '
            testo += logo.giorno
        }// fine del blocco if

        if (usaPosta && testo) {
            mailService.sendMail {
                to mailTo
                subject oggetto
                body testo
            }// fine della closure
        }// fine del blocco if

    }// fine del metodo

} // end of Service Class
