package org.theeuropeanlibrary.hera.rest.administration;

//TODO: move to HERA-COMMON
/**
 * PathConstants
 *
 */
public interface ParamConstants {

    // resource paths
    static final String PROVIDERS = "data-providers";

    static final String DATASETS = "data-sets";

    // path parameter names
    static final String P_VER = "VERSION";

    static final String P_PROVIDER = "PROVIDER";

    static final String P_DATASET = "DATASET";

    static final String P_FILENAME = "FILENAME";

    // query parameter names
    static final String Q_START_FROM = "startFrom";

    // form parameter names
    static final String F_DATASET = "dataSetId";

    static final String F_DATASET_PROVIDER_ID = "dataSetProviderId";

    static final String F_DESCRIPTION = "description";

    static final String F_PROVIDER = "providerId";

    static final String F_CLOUDID = "cloudId";

    static final String F_REPRESENTATIONNAME = "representationName";

    static final String F_VER = "version";

    static final String F_FILE_DATA = "data";

    static final String F_FILE_MIME = "mimeType";

    static final String F_FILE_NAME = "fileName";

    static final String F_DATE_FROM = "creationDateFrom";

    static final String F_PERSISTENT = "persistent";

    static final String F_DATE_UNTIL = "creationDateUntil";

    static final String F_REPRESENTATION = "representation";

    static final String F_DATASETS = "dataSets";

    //header parameter names
    static final String H_RANGE = "Range";
}
