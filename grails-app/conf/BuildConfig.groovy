/* Created by Algos s.r.l. */
/* Date: mag 2012 */
/* Il plugin AlgosBase ha inserito (solo la prima volta) questo header per controllare */
/* le successive release (tramite il flag di controllo aggiunto) */
/* Tipicamente NON verrà più sovrascritto dalle successive release del plugin */
/* in quanto POTREBBE essere personalizzato in questa applicazione */
/* Se vuoi che le prossime release del plugin sovrascrivano questo file, */
/* perdendo tutte le modifiche effettuate qui, */
/* regola a true il flag di controllo flagOverwrite© */
/* flagOverwrite = false */

grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()

        // enable remote dependency resolution from public Algos repositories
        mavenRepo "http://77.43.32.198:8080/artifactory/plugins-release-local/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
        runtime 'mysql:mysql-connector-java:5.1.20'
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.47"
//        build ":release:2.2.0"

        // plugins for the compile step
        compile ":scaffolding:2.0.1"
        compile ':cache:1.1.1'
        compile ":quartz:1.0.1"

        // plugins needed at runtime but not for compilation
        runtime ":hibernate:3.6.10.6" // or ":hibernate4:4.1.11.6"
        runtime ":database-migration:1.3.8"
        runtime ":jquery:1.10.2.2"
        runtime ":jquery-ui:1.10.3"
        runtime ":resources:1.2.1"

        //--altro
        compile ":mail:1.0.1" {
            excludes 'spring-test'
        }

        compile ":famfamfam:1.0.1"
        compile ":spring-security-core:1.2.7.1"
        compile ":spring-security-ui:0.2"

        // runtime ":algosbase:latest.integration"
        // runtime ":algospref:latest.integration"
    }

    grails.project.repos.algosRepo.url = "http://77.43.32.198:8080/artifactory/plugins-release-local/"
    grails.project.repos.algosRepo.type = "maven"
    grails.project.repos.algosRepo.username = "admin"
    grails.project.repos.algosRepo.password = "password"

}

// This closure is passed the command line arguments
// used to start the war process.
grails.war.copyToWebApp = { args ->
    fileset(dir: "web-app") {
        include(name: "css/**")
        include(name: "images/**")
        include(name: "js/**")
        include(name: "WEB-INF/**")
    }// fine della closure fileset
}// fine di grails.war.copyToWebApp
