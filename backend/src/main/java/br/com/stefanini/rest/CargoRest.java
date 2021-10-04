package br.com.stefanini.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.stefanini.exceptions.ErroNegocialException;
import br.com.stefanini.models.CargoEnum;
import br.com.stefanini.services.CargoService;

/**
 * @author sillas
 * @version 0.1.0
 * @email sillas.ap16@gmail.com
 * @created 03/10/2021 on 20:20
 */
@RequestScoped
@Path("/cargo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CargoRest {
    // @Inject
    // CargoService service;
    
    // @GET
    // @Operation(summary = "Listar Cargos",
    //         description = "Retorna uma lista de cargos sem a necessidade de parametros")
    // @APIResponse(
    //         responseCode = "200",
    //         description = "Cargo",
    //         content = { @Content(mediaType = "application/json",
    //                 schema = @Schema(implementation = CargoEnum.class))})
    // public Response listarCargo() throws ErroNegocialException {
    //     var test = service.listar();
    //     return  Response.status(Response.Status.OK).entity(service.listar()).build();
    // }
}
