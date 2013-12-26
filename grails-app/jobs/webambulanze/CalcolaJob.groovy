package webambulanze



class CalcolaJob {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def militeturnoService
    def mailService
    def utenteService

    static boolean USA_MAIL = true

    static triggers = {
        cron name: 'myTrigger', cronExpression: "0 0 3 * * ?"     // alle 3 di notte di ogni giorno
    }

    def execute() {
        militeturnoService.calcola()
        utenteService.regolaAbilitazioni()

        if (USA_MAIL) {
            spedisceMailDiControllo('Ricalcolo effettuato per tutte le croci abilitate')
        }// fine del blocco if-else
    }// fine del metodo execute

    //--provvisorio all'inizio
    private spedisceMailDiControllo(String testo) {
        String adesso = new Date().toString()
        String mailTo = 'gac@algos.it'
        String oggetto = 'Gestione ambulanze-calcolo notturno'
        String time = ' Eseguito alle ' + adesso

        testo += time
        mailService.sendMail {
            to mailTo
            subject oggetto
            body testo
        }// fine della closure
    }// fine del metodo execute

} // end of Job Class
