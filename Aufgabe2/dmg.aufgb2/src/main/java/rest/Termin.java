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

@Path("/termin")
public class Termin {
	
	Dbhandler db = new Dbhandler();
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Collection<Termin> getTermin() {
		return db.getObjects("termin");
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public modeljpa.Termin gettermin(@PathParam("id") int id) {
		return db.getTerminById(id);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response posttermin(modeljpa.Termin termin, @Context UriInfo uriInfo) {
		if(db.persistObject(termin)) {
			return Response.status(Response.Status.CREATED).entity("termin/" + termin.getTID()).build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deletePersonById(@PathParam("id") int id) {
		if (db.deleteTerminById(id)) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response puttermin(modeljpa.Termin termin, @Context UriInfo uriInfo, @PathParam("id") int id) {
		termin.setTID(id);
		if (db.updateTermin(termin)) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

}
