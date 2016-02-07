package se.kodion.contacts.rest;

import se.kodion.contacts.Contact;
import se.kodion.contacts.dao.ContactDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON)
public class ContactsResource {

    private final ContactDAO dao;

    public ContactsResource(ContactDAO dao) {
        this.dao = dao;
    }

    @GET
    @Path("/{id}")
    public Contact getContact(@PathParam("id") long id) {
        return dao.findContactById(id);
    }

    @GET
    public Iterator<Contact> getAllContacts() {
        return dao.getAllContacts();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createContact(Contact contact) {
        dao.insert(contact.getFirstName(), contact.getLastName());
        return Response.created(URI.create("http://localhost:8080/contacts/" + contact.getId())).build();
    }
}
