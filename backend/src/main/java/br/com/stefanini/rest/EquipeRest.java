package br.com.stefanini.rest;

import br.com.stefanini.exceptions.ErroNegocialException;
import br.com.stefanini.models.Equipe;
import br.com.stefanini.services.EquipeService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author sillas
 * @version 0.1.0
 * @email sillas.ap16@gmail.com
 * @created 03/10/2021 on 20:20
 */
@RequestScoped
@Path("/equipe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EquipeRest {
    @Inject
    EquipeService service;

    @POST
    @Operation(summary = "Insere uma equipe",
            description = "Com os dados faz a validação e insereção na base de dados")
    @APIResponse(
            responseCode = "201",
            description = "Equipe",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Equipe.class))})
    public Response inserirEquipe(Equipe equipe) throws Exception {
        service.inserir(equipe);
        return  Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path(value = "{id}")
    @Operation(summary = "Alterar equipe",
            description = "Altera os dados de uma equipe pelo seu id")
    @APIResponse(
            responseCode = "200",
            description = "Equipe",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Equipe.class))})
    public Response alterarEquipe(@PathParam(value = "id") int id, Equipe equipe) throws Exception {
        equipe.setId(id);
        service.alterar(equipe);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path(value = "{id}")
    @Operation(summary = "Deletar Equipe",
            description = "Deleta uma equipe pelo seu id")
    @APIResponse(
            responseCode = "200",
            description = "Equipe",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Equipe.class))})
    public Response deletarEquipe(@PathParam(value = "id") int id) throws Exception {
        service.deletar(id);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path(value = "{id}")
    @Operation(summary = "Buscar Equipe",
            description = "Busca os dados de uma equipe pelo seu id")
    @APIResponse(
            responseCode = "200",
            description = "Equipe",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Equipe.class))})
    public Response buscarEquipe(@PathParam(value = "id") int id) throws Exception {
        return Response.status(Response.Status.OK).entity(service.buscar(id)).build();
    }

    @GET
    @Operation(summary = "Listar Equipes",
            description = "Retorna uma lista de equipes sem a necessidade de parametros")
    @APIResponse(
            responseCode = "200",
            description = "Equipe",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Equipe.class))})
    public Response listarEquipe() throws ErroNegocialException {
        return  Response.status(Response.Status.OK).entity(service.listar()).build();
    }
}
