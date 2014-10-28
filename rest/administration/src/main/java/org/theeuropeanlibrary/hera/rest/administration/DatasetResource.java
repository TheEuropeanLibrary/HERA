package org.theeuropeanlibrary.hera.rest.administration;

import static org.theeuropeanlibrary.hera.rest.administration.ParamConstants.F_DESCRIPTION;
import static org.theeuropeanlibrary.hera.rest.administration.ParamConstants.P_DATASET;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.theeuropeanlibrary.hera.rest.administration.service.DatasetService;


/**
 * Resource to manage data sets.
 */
@Path("/datasets/{" + P_DATASET + "}")
@Component
@Scope("request")
public class DatasetResource {

	@Autowired(required = false)
    private DatasetService dataSetService;
    
    @Value("${numberOfElementsOnPage}")
    private int numberOfElementsOnPage;
    
    /**
     * Deletes data set.
     *
     * @throws DataSetNotExistsException data set not exists.
     */
    @DELETE
    public void deleteDataSet(@PathParam(P_DATASET) String dataSetId) {
    	
        dataSetService.deleteDataSet(dataSetId);
    }

    /**
     * Updates description of data set.
     *
     * @param description description of data set
     * @throws DataSetNotExistsException no such data set exists.
     * @statuscode 204 object has been updated.
     */
    @PUT
    public void updateDataSet(@PathParam(P_DATASET) String dataSetId,
    		@FormParam(F_DESCRIPTION) String description) {
    	
        dataSetService.updateDataSet(dataSetId, description);
    }
}
