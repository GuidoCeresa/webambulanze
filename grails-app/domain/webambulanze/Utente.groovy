package webambulanze

class Utente {

    transient springSecurityService

    //--croce di riferimento
    Croce croce

    //--milite di riferimento
    Milite milite

    String username
    String password
    String pass
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false

    static constraints = {
        croce(nullable: false, blank: false, display: false)
        milite(nullable: true, blank: true)
        username blank: false, unique: true
        password blank: false
        pass()
        enabled()
        accountExpired()
        accountLocked()
        passwordExpired()
    }

    static mapping = {
        password column: '`password`'
    }

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        username
    } // end of toString

    Set<Ruolo> getAuthorities() {
        UtenteRuolo.findAllByUtente(this).collect { it.ruolo } as Set
    }

    def beforeInsert() {
        pass = password
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
        if (isDirty('pass')) {
            password = pass
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }
}
