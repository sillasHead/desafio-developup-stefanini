package br.com.stefanini.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.stefanini.dto.EquipeDto;
import br.com.stefanini.exceptions.ErroNegocialException;
import br.com.stefanini.services.EquipeService;

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
                    schema = @Schema(implementation = EquipeDto.class))})
    public Response inserirEquipe(@Valid EquipeDto equipe) throws Exception {
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
                    schema = @Schema(implementation = EquipeDto.class))})
    public Response alterarEquipe(@PathParam(value = "id") int id, @Valid EquipeDto equipe) throws Exception {
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
                    schema = @Schema(implementation = EquipeDto.class))})
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
                    schema = @Schema(implementation = EquipeDto.class))})
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
                    schema = @Schema(implementation = EquipeDto.class))})
    public Response listarEquipe() throws ErroNegocialException {
        return  Response.status(Response.Status.OK).entity(service.listar()).build();
    }
}
