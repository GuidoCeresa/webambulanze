package webambulanze



class RefreshJob {
    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def mailService

    static triggers = {
        simple startDelay: 1000, repeatInterval: 1000 * 60 * 10 // execute job once in 10 minutes
    }

    def execute() {
        def logs
        try { // prova ad eseguire il codice
            logs = Logo.getAll()
            if (logs.size() > 0) {
                //--non fa nulla, ma ha comunque ''risvegliato'' l'applicazione
            } else {
              //  spedisceMail('Non ci sono logs')
            }// fine del blocco if-else
        } catch (Exception unErrore) { // intercetta l'errore
            spedisceMail('Qualcosa non funziona')
        }// fine del blocco try-catch
    }// fine del metodo execute


    private spedisceMail(String testo) {
        String adesso = new Date().toString()
        String mailTo = 'guidoceresa@me.com'
        String oggetto = 'Gestione ambulanze'
        String time = ' Eseguito alle ' + adesso

        testo += time
        mailService.sendMail {
            to mailTo
            subject oggetto
            body testo
        }// fine della closure
    }// fine del metodo execute

} // end of Job Class
