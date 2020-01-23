package rest;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import jpa.Dbhandler;

@Path("/person")
public class Person {
	
	Dbhandler db = new Dbhandler();
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Collection<Person> getPersons() {
		return db.getObjects("person");
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public modeljpa.Person getPerson(@PathParam("id") int id) {
		return db.getPersonById(id);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response postPerson(modeljpa.Person person, @Context UriInfo uriInfo) {
		if(db.persistObject(person)) {
			return Response.status(Response.Status.CREATED).entity("person/" + person.getPID()).build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deletePersonById(@PathParam("id") int id) {
		if (db.deletePersonById(id)) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response putPerson(modeljpa.Person person, @Context UriInfo uriInfo, @PathParam("id") int id) {
		person.setPID(id);
		if (db.updatePerson(person)) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
}
