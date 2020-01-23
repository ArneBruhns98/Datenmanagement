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

@Path("/adresse")
public class Adresse {

	Dbhandler db = new Dbhandler();
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Collection<Adresse> getAdresses() {
		return db.getObjects("adresses");
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public modeljpa.Adresse getAdresse(@PathParam("id") int id) {
		return db.getAdresseById(id);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response postAdresse(modeljpa.Adresse adresse, @Context UriInfo uriInfo) {
		if(db.persistObject(adresse)) {
			return Response.status(Response.Status.CREATED).entity("adresse/" + adresse.getAID()).build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deletePersonById(@PathParam("id") int id) {
		if (db.deleteAdresseById(id)) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response putAdresse(modeljpa.Adresse adresse, @Context UriInfo uriInfo, @PathParam("id") int id) {
		adresse.setAID(id);
		if (db.updateAdresse(adresse)) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
}
