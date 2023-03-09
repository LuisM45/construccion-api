package edu.epn.web.b2022.g3.construccion.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import static MiscTools.StaticUtils.*;
import edu.epn.web.b2022.g3.construccion.modelo.Mascota;
import edu.epn.web.b2022.g3.construccion.modelo.MascotaDAO;
import java.util.stream.Stream;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/mascota")
public class MascotaAPI extends Application{
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMascota(
            @QueryParam("id") Integer id,
            @QueryParam("nombre") String nombre,
            @QueryParam("raza") String raza,
            @QueryParam("genero") Mascota.Genero genero
    ){
        Stream<Mascota> mascotas = MascotaDAO.get().getAll().stream()
            .filter(t->id==null||t.getId().equals(id))
            .filter(t->nombre==null||t.getNombre().equals(nombre))
            .filter(t->raza==null||t.getRaza().equals(raza))
            .filter(t->genero==null||t.getGenero().equals(genero));
        
        return Response.status(Response.Status.OK).entity(mascotas.toList()).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postMascota(
            @QueryParam("nombre") String nombre,
            @QueryParam("raza") String raza,
            @QueryParam("genero") Mascota.Genero genero
    ){
        if(anyNull(nombre,raza,genero))
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing parameters").build();
        
        Mascota mascota = new Mascota(nombre,raza, genero);
        mascota = MascotaDAO.get().create(mascota);
        
        return Response.status(Response.Status.OK).entity(mascota).build();
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response putMascota(
            @QueryParam("id") Integer id,
            @QueryParam("nombre") String nombre,
            @QueryParam("raza") String raza,
            @QueryParam("genero") Mascota.Genero genero
    ){
        Mascota mascota = tryParse(MascotaDAO.get()::get, id);
        if(mascota == null)
            return Response.status(Response.Status.BAD_REQUEST).entity("This mascota couldn't be found").build();
        
        mascota.setGenero(genero);
        mascota.setRaza(raza);
        mascota.setNombre(nombre);
        MascotaDAO.get().update(mascota);
        
        return Response.status(Response.Status.OK).entity(mascota).build();
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMascota(
            @QueryParam("id") Integer id
    ){
        Mascota mascota = tryParse(MascotaDAO.get()::get, id);
        if(mascota == null)
            return Response.status(Response.Status.BAD_REQUEST).entity("This mascota couldn't be found").build();
        
        MascotaDAO.get().delete(mascota);
        
        return Response.status(Response.Status.OK).entity("SUCCESS").build();
    }
}
