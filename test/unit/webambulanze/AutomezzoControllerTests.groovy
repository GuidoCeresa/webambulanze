package webambulanze



import org.junit.*
import grails.test.mixin.*

@TestFor(AutomezzoController)
@Mock(Automezzo)
class AutomezzoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/automezzo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.automezzoInstanceList.size() == 0
        assert model.automezzoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.automezzoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.automezzoInstance != null
        assert view == '/automezzo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/automezzo/show/1'
        assert controller.flash.message != null
        assert Automezzo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/automezzo/list'

        populateValidParams(params)
        def automezzo = new Automezzo(params)

        assert automezzo.save() != null

        params.id = automezzo.id

        def model = controller.show()

        assert model.automezzoInstance == automezzo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/automezzo/list'

        populateValidParams(params)
        def automezzo = new Automezzo(params)

        assert automezzo.save() != null

        params.id = automezzo.id

        def model = controller.edit()

        assert model.automezzoInstance == automezzo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/automezzo/list'

        response.reset()

        populateValidParams(params)
        def automezzo = new Automezzo(params)

        assert automezzo.save() != null

        // test invalid parameters in update
        params.id = automezzo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/automezzo/edit"
        assert model.automezzoInstance != null

        automezzo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/automezzo/show/$automezzo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        automezzo.clearErrors()

        populateValidParams(params)
        params.id = automezzo.id
        params.version = -1
        controller.update()

        assert view == "/automezzo/edit"
        assert model.automezzoInstance != null
        assert model.automezzoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/automezzo/list'

        response.reset()

        populateValidParams(params)
        def automezzo = new Automezzo(params)

        assert automezzo.save() != null
        assert Automezzo.count() == 1

        params.id = automezzo.id

        controller.delete()

        assert Automezzo.count() == 0
        assert Automezzo.get(automezzo.id) == null
        assert response.redirectedUrl == '/automezzo/list'
    }
}
