package webambulanze



import org.junit.*
import grails.test.mixin.*

@TestFor(VersioneController)
@Mock(Versione)
class VersioneControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/versione/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.versioneInstanceList.size() == 0
        assert model.versioneInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.versioneInstance != null
    }

    void testSave() {
        controller.save()

        assert model.versioneInstance != null
        assert view == '/versione/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/versione/show/1'
        assert controller.flash.message != null
        assert Versione.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/versione/list'

        populateValidParams(params)
        def versione = new Versione(params)

        assert versione.save() != null

        params.id = versione.id

        def model = controller.show()

        assert model.versioneInstance == versione
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/versione/list'

        populateValidParams(params)
        def versione = new Versione(params)

        assert versione.save() != null

        params.id = versione.id

        def model = controller.edit()

        assert model.versioneInstance == versione
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/versione/list'

        response.reset()

        populateValidParams(params)
        def versione = new Versione(params)

        assert versione.save() != null

        // test invalid parameters in update
        params.id = versione.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/versione/edit"
        assert model.versioneInstance != null

        versione.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/versione/show/$versione.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        versione.clearErrors()

        populateValidParams(params)
        params.id = versione.id
        params.version = -1
        controller.update()

        assert view == "/versione/edit"
        assert model.versioneInstance != null
        assert model.versioneInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/versione/list'

        response.reset()

        populateValidParams(params)
        def versione = new Versione(params)

        assert versione.save() != null
        assert Versione.count() == 1

        params.id = versione.id

        controller.delete()

        assert Versione.count() == 0
        assert Versione.get(versione.id) == null
        assert response.redirectedUrl == '/versione/list'
    }
}
