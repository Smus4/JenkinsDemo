package dtu.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PersonServiceSteps {

    private PersonDTO inputPerson;
    private PersonDTO resultPerson;
    private Exception caughtException;

    private final PersonService service = new PersonService();

    @Before
    public void setup() {
        service.putPerson(new PersonDTO("Susan", "USA"));
    }

    @Given("the person name {string}")
    public void givenPersonName(String name) {
        if (inputPerson == null) {
            inputPerson = new PersonDTO();
        }
        inputPerson.setName(name);
    }

    @Given("the person address {string}")
    public void givenPersonAddress(String address) {
        if (inputPerson == null) {
            inputPerson = new PersonDTO();
        }
        inputPerson.setAddress(address);
    }

    @When("I call the person service get")
    public void iCallThePersonServiceGet() {
        resultPerson = service.getPerson();
    }

    @When("I call the person service put")
    public void whenICallPut() {
        try {
            resultPerson = service.putPerson(inputPerson);
        } catch (Exception e) {
            caughtException = e;
        }
    }

    @Then("the person name is {string}")
    public void thePersonNameIs(String string) {
        assertEquals(string, resultPerson.getName());
    }

    @Then("the person address is {string}")
    public void thePersonAddressIs(String string) {
        assertEquals(string, resultPerson.getAddress());
    }

    @Then("an error happends and message contains {string}")
    public void anErrorHappendsAndMessageContains(String expected) {
        assertEquals(expected, caughtException.getMessage());
    }

}
