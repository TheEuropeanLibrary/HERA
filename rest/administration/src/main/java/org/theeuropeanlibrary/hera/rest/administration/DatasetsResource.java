package org.theeuropeanlibrary.hera.rest.administration;

import static org.theeuropeanlibrary.hera.rest.administration.ParamConstants.F_DATASET;
import static org.theeuropeanlibrary.hera.rest.administration.ParamConstants.F_DESCRIPTION;
import static org.theeuropeanlibrary.hera.rest.administration.ParamConstants.Q_START_FROM;

import java.net.URI;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.theeuropeanlibrary.hera.rest.administration.service.DatasetService;
import org.theeuropeanlibrary.maia.common.definitions.Dataset;

/**
 * Resource to get and create data set.
 */
@Path("/datasets")
@Component
@Scope("request")
public class DatasetsResource {

    @Autowired(required = false)
    private DatasetService datasetService;

    @Value("${numberOfElementsOnPage}")
    private int numberOfDatasets;

    /**
     * Returns all data sets. Result is returned in slices.
     *
     * @param startFrom reference to next slice of result. If not provided,
     * first slice of result will be returned.
     * @return slice of data sets for given provider.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ResultSlice<Dataset> getDataSets(@QueryParam(Q_START_FROM) String startFrom) {
        ResultSlice<Dataset> dList = new ResultSlice<>();
        dList.setResults(datasetService.getDataSets(startFrom, numberOfDatasets));
        return dList;
    }

    /**
     * Creates new data set.
     *
     * @param dataSetId identifier of data set (required).
     * @param description description of data set.
     * @return URI to newly created data set in content-location.
     *
     * @statuscode 201 object has been created.
     */
    @POST
    public Response createDataSet(
            @Context UriInfo uriInfo,
            @FormParam(F_DATASET) String dataSetId,
            @FormParam(F_DESCRIPTION) String description) {

        ParamUtil.require(F_DATASET, dataSetId);

        Dataset dataSet = datasetService.createDataSet(dataSetId, description);
        final URI datasetURI = null;
        final Response response = Response.created(datasetURI).build();

        String creatorName = SpringUserUtils.getUsername();
        return response;
    }
}
