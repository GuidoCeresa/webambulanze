/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 21-1-13
 * Time: 09:26
 */


import grails.test.GrailsUnitTestCase
import webambulanze.RefreshJob

class RefTests extends GrailsUnitTestCase {

    def refreshJob
    def grailsApplication

    protected void setUp() {
        super.setUp()

        refreshJob = grailsApplication.mainContext.getBean(RefreshJob)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCleanNothing() {
        refreshJob.execute()
    }

}
