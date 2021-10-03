package br.com.stefanini.rest;

import br.com.stefanini.exceptions.ErroNegocialException;
import br.com.stefanini.models.Pessoa;
import br.com.stefanini.services.PessoaService;
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
 * @author danilo
 * @version 0.1.0
 * @email maratona@stefanini.com
 * @created 21/09/2021 on 07:10
 */
@RequestScoped
@Path("/pessoa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaRest {
    @Inject
    PessoaService service;

    @POST
    @Operation(summary = "Insere uma pessoa",
            description = "Com os dados faz a validação e insereção na base de dados")
    @APIResponse(
            responseCode = "201",
            description = "Pessoa",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pessoa.class))})
    public Response inserirPessoa(Pessoa pessoa) throws Exception {
        service.inserir(pessoa);
        return  Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path(value = "{id}")
    @Operation(summary = "Alterar pessoa",
            description = "Altera os dados de uma pessoa pelo seu id")
    @APIResponse(
            responseCode = "200",
            description = "Pessoa",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pessoa.class))})
    public Response alterarPessoa(@PathParam(value = "id") int id, Pessoa pessoa) throws Exception {
        pessoa.setId(id);
        service.alterar(pessoa);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path(value = "{id}")
    @Operation(summary = "Deletar Pessoa",
            description = "Deleta uma pessoa pelo seu id")
    @APIResponse(
            responseCode = "200",
            description = "Pessoa",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pessoa.class))})
    public Response deletarPessoa(@PathParam(value = "id") int id) throws Exception {
        service.deletar(id);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path(value = "{id}")
    @Operation(summary = "Buscar Pessoa",
            description = "Busca os dados de uma pessoa pelo seu id")
    @APIResponse(
            responseCode = "200",
            description = "Pessoa",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pessoa.class))})
    public Response buscarPessoa(@PathParam(value = "id") int id) throws Exception {
        return Response.status(Response.Status.OK).entity(service.buscar(id)).build();
    }

    @GET
    @Operation(summary = "Listar Pessoas",
            description = "Retorna uma lista de pessoas sem a necessidade de parametros")
    @APIResponse(
            responseCode = "200",
            description = "Pessoa",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pessoa.class))})
    public Response listarPessoa() throws ErroNegocialException {
        return  Response.status(Response.Status.OK).entity(service.listar()).build();
    }
}
