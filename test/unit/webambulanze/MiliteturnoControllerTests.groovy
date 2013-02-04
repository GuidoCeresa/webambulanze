package webambulanze



import org.junit.*
import grails.test.mixin.*

@TestFor(MiliteturnoController)
@Mock(Militeturno)
class MiliteturnoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/militeturno/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.militeturnoInstanceList.size() == 0
        assert model.militeturnoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.militeturnoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.militeturnoInstance != null
        assert view == '/militeturno/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/militeturno/show/1'
        assert controller.flash.message != null
        assert Militeturno.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/militeturno/list'

        populateValidParams(params)
        def militeturno = new Militeturno(params)

        assert militeturno.save() != null

        params.id = militeturno.id

        def model = controller.show()

        assert model.militeturnoInstance == militeturno
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/militeturno/list'

        populateValidParams(params)
        def militeturno = new Militeturno(params)

        assert militeturno.save() != null

        params.id = militeturno.id

        def model = controller.edit()

        assert model.militeturnoInstance == militeturno
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/militeturno/list'

        response.reset()

        populateValidParams(params)
        def militeturno = new Militeturno(params)

        assert militeturno.save() != null

        // test invalid parameters in update
        params.id = militeturno.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/militeturno/edit"
        assert model.militeturnoInstance != null

        militeturno.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/militeturno/show/$militeturno.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        militeturno.clearErrors()

        populateValidParams(params)
        params.id = militeturno.id
        params.version = -1
        controller.update()

        assert view == "/militeturno/edit"
        assert model.militeturnoInstance != null
        assert model.militeturnoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/militeturno/list'

        response.reset()

        populateValidParams(params)
        def militeturno = new Militeturno(params)

        assert militeturno.save() != null
        assert Militeturno.count() == 1

        params.id = militeturno.id

        controller.delete()

        assert Militeturno.count() == 0
        assert Militeturno.get(militeturno.id) == null
        assert response.redirectedUrl == '/militeturno/list'
    }
}
