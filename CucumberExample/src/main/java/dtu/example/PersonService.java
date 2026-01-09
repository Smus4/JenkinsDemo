package dtu.example;

import dtu.Exceptions.PersonServiceException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.client.Entity;

public class PersonService {

    Client client = ClientBuilder.newClient();

    public PersonDTO getPerson() {

        Response response = ClientBuilder
                .newClient()
                .target("http://localhost:8080")
                .path("/person")
                .request()
                .get();

        if (response.getStatus() < 200 || response.getStatus() >= 300) {
            throw new PersonServiceException("Address cannot be -none-");
        }

        return response.readEntity(PersonDTO.class);

    }

    public PersonDTO putPerson(PersonDTO person) {

        Response response = ClientBuilder.newClient()
                .target("http://localhost:8080")
                .path("person")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(person, MediaType.APPLICATION_JSON));

        int status = response.getStatus();

        if (status == 400) {
            throw new PersonServiceException("Bad request: " + response.readEntity(String.class));
        }

        if (status < 200 || status >= 300) {
            throw new RuntimeException("HTTP " + status + ": " + response.readEntity(String.class));
        }

        return response.readEntity(PersonDTO.class);
    }
}
