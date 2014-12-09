package org.theeuropeanlibrary.hera.rest.administration.utils;

import org.theeuropeanlibrary.maia.common.definitions.Dataset;
import org.theeuropeanlibrary.maia.common.definitions.Provider;
import org.theeuropeanlibrary.maia.tel.model.common.Coordinate;
import org.theeuropeanlibrary.maia.tel.model.common.qualifier.Country;
import org.theeuropeanlibrary.maia.tel.model.common.qualifier.Language;
import org.theeuropeanlibrary.maia.tel.model.common.qualifier.NameType;
import org.theeuropeanlibrary.maia.tel.model.dataset.DatasetKeys;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.Agreement;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.AgreementStatus;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.DataType;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.DatasetType;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.DigitisationStatus;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.DistributionFormat;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.IngestionStatus;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.License;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.LicenseType;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.Restriction;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.descriptions.CollectionDescription;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.descriptions.Discipline;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.descriptions.ItemType;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.descriptions.SpatialCoverage;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.descriptions.Subject;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.descriptions.TimeCoverage;
import org.theeuropeanlibrary.maia.tel.model.provider.ProviderKeys;
import org.theeuropeanlibrary.maia.tel.model.provider.definitions.Address;
import org.theeuropeanlibrary.maia.tel.model.provider.definitions.ConsortiumType;
import org.theeuropeanlibrary.maia.tel.model.provider.definitions.ContactRelation;
import org.theeuropeanlibrary.maia.tel.model.provider.definitions.DatasetRelation;
import org.theeuropeanlibrary.maia.tel.model.provider.definitions.EntityRelation;
import org.theeuropeanlibrary.maia.tel.model.provider.definitions.LibraryOrganization;
import org.theeuropeanlibrary.maia.tel.model.provider.definitions.LinkType;
import org.theeuropeanlibrary.maia.tel.model.provider.definitions.MembershipType;
import org.theeuropeanlibrary.maia.tel.model.provider.definitions.PortalStatus;
import org.theeuropeanlibrary.maia.tel.model.provider.definitions.ProviderType;

public class HardcodedExamplesUtil {

    public static Dataset<String> createDatasets() {
        Dataset<String> dataset = new Dataset<>();
        dataset.setId("a0037");

        dataset.addValue(DatasetKeys.IDENTIFIER, "a0037");
        dataset.addValue(DatasetKeys.NAME, "British Library integrated catalogue - Online catalogues of printed and electronic resources", NameType.MAIN, Language.ENG);
        dataset.addValue(DatasetKeys.LANGUAGE, Language.ENG);
        dataset.addValue(DatasetKeys.COUNTRY, Country.GB);
        dataset.addValue(DatasetKeys.INGESTION_STATUS, IngestionStatus.PUBLISH);
        dataset.addValue(DatasetKeys.DATASET_TYPE, DatasetType.CATALOGUE);

        Agreement agreement = new Agreement();
        agreement.setStatus(AgreementStatus.PUBLISH);
        agreement.setSigned("01-01-2014");
        agreement.setAppendix("01-01-2014");
        dataset.addValue(DatasetKeys.AGREEMENT, agreement);

        Restriction restriction = new Restriction();
        restriction.setFormat(DistributionFormat.OTHER);
        restriction.setOtherFormat("BLA");
        restriction.setTerms("test");
        restriction.setTime("01-01-2019");
        restriction.setDuration("01-01-2021");
        dataset.addValue(DatasetKeys.RESTRICTION, restriction);

        License license = new License();
        license.setSource(LicenseType.OTHER);
        license.setOtherSource("LICENSE");
        license.setDistribution(LicenseType.BY);
        license.setFurtherInformation("BLA");
        dataset.addValue(DatasetKeys.LICENSE, license);

        dataset.addValue(DatasetKeys.DIGITISATION_STATUS, DigitisationStatus.PARTIALLY_DIGITISED);
        dataset.addValue(DatasetKeys.EXPECTED_RECORDS, "13000000");
        dataset.addValue(DatasetKeys.EXPECTED_DIGITAL_OBJECTS, "1000000");
        dataset.addValue(DatasetKeys.DATA_FORMAT, "LCSH", DataType.AUTHORITY);

        dataset.addValue(DatasetKeys.DISCIPLINE, Discipline.HUMANITIES);
        dataset.addValue(DatasetKeys.LANGUAGE, Language.ENG);
        dataset.addValue(DatasetKeys.SUBJECT, Subject.MEDIEVAL_HISTORY);
        dataset.addValue(DatasetKeys.TIME_COVERAGE, TimeCoverage.RENAISSANCE);
        dataset.addValue(DatasetKeys.SPATIAL_COVERAGE, SpatialCoverage.PARIS);
        dataset.addValue(DatasetKeys.ITEM_TYPE, ItemType.BLUE_PRINT);
        dataset.addValue(DatasetKeys.COLLECTION_DESCRIPTION, new CollectionDescription("Title", "KatNUK je glavni katalog naše knjižnice in je hkrati"
        		+ " največji katalog posamezne knjižnice v Sloveniji. Obsega več kot 900.000 bibliografskih zapisov, ki predstavljajo: "
        		+ "gradivo, ki ga knjižnica tekoče pridobiva od leta 1988; za obdobje 1774-1948 samo popis knjig in vse gradivo,"
        		+ " ki ga je knjižnica pridobila v obdobju od 1948-1987. V katalogu so popisane monografske publikacije,"
        		+ " novejše periodične publikacije in članki iz slovenskih strokovnih časnikov in revij"
        		+ " (tekoče se popisujejo od leta 1990)"), Language.ENG);
   
        return dataset;
    }
    
	/**
	 * @return Provider by markus
	 */
	public static Provider<String> createProviders() {
		
        Provider<String> provider = new Provider<>();
        provider.setId("1");
        provider.addValue(ProviderKeys.IDENTIFIER, "P01264");
        provider.addValue(ProviderKeys.NAME, "The British Library", NameType.MAIN, Language.ENG);
        provider.addValue(ProviderKeys.NAME, "NL United Kingdom", NameType.INTERNAL, Language.ENG);
        provider.addValue(ProviderKeys.NAME, "BL", NameType.ACRONYM, Language.ENG);
        provider.addValue(ProviderKeys.LANGUAGE, Language.ENG);
        provider.addValue(ProviderKeys.COUNTRY, Country.GB);
        provider.addValue(ProviderKeys.PROVIDER_TYPE, ProviderType.NATIONAL_LIBRARY);

        provider.addValue(ProviderKeys.ADDRESS, new Address("Street xy", "1234", "London"));
        provider.addValue(ProviderKeys.PHONE, "555-12345678");
        provider.addValue(ProviderKeys.FAX, "555-567800");
        provider.addValue(ProviderKeys.EMAIL, "john.smith@bl.uk");

        provider.addValue(ProviderKeys.DEA, true);
        provider.addValue(ProviderKeys.EOD, true);
        provider.addValue(ProviderKeys.LIBRARY_ORGANIZATION, LibraryOrganization.CENL);
        provider.addValue(ProviderKeys.CONSORTIUM_TYPE, ConsortiumType.AGGREGATION);
        provider.addValue(ProviderKeys.NOTE, "bla bla bla");

        provider.addValue(ProviderKeys.MEMBER, true);
        provider.addValue(ProviderKeys.MEMBERSHIP_TYPE, MembershipType.MEMBERS);

        provider.addValue(ProviderKeys.PORTAL_STATUS, PortalStatus.LIVE);
        provider.addValue(ProviderKeys.COORDINATE, new Coordinate(51.5294, -0.1269));

        provider.addValue(ProviderKeys.LINK, "http://www.bl.uk/images/bl_logo_100.gif", LinkType.LOGO);
        provider.addValue(ProviderKeys.LINK, "www.bl.uk/", LinkType.WEBSITE);
        provider.addValue(ProviderKeys.LINK, "http://www.bl.uk/aboutus/contact/index.html", LinkType.CONTACTS);
        provider.addValue(ProviderKeys.LINK, "http://www.bl.uk/aboutus/quickinfo/loc/stp/opening/index.html#reading", LinkType.OPENING);
        provider.addValue(ProviderKeys.LINK, "http://en.wikipedia.org/wiki/British_Library", LinkType.WIKIPEDIA_ENGLISH);
        provider.addValue(ProviderKeys.LINK, "http://en.wikipedia.org/wiki/British_Library", LinkType.WIKIPEDIA_NATIVE);

        provider.addValue(ProviderKeys.IMAGE, "http://upload.wikimedia.org/wikipedia/commons/thumb/2/22/British_library_london.jpg/230px-British_library_london.jpg");
        provider.addValue(ProviderKeys.IMAGE, "http://www.theeuropeanlibrary.org/exhibition/buildings/images/pictures/uk_l04.jpg");
        provider.addValue(ProviderKeys.IMAGE, "http://search.theeuropeanlibrary.org/images/treasure");

        provider.addValue(ProviderKeys.CONTACT, new ContactRelation("11", "Roly Keating", "Director or Deputy Director", "", ""));
        provider.addValue(ProviderKeys.CONTACT, new ContactRelation("12", "Janet Zmroczek", "Collections Contact", "", ""));
        provider.addValue(ProviderKeys.CONTACT, new ContactRelation("13", "Corine Deliot", "Technical contact", "", ""));
        provider.addValue(ProviderKeys.CONTACT, new ContactRelation("14", "Rossitza Atanassova", "Marketing Contact", "", ""));
        provider.addValue(ProviderKeys.CONTACT, new ContactRelation("15", "Library Coordinator Group", "Janet Zmroczek", "", ""));

        provider.addValue(ProviderKeys.DATASET, new DatasetRelation("21", "British Library integrated catalogue - Online catalogues of printed and electronic resources", "a0037", "Live"));
        provider.addValue(ProviderKeys.DATASET, new DatasetRelation("22", "EC1914 BL-Printed Literary Sources", "a0554", "Internal"));

        provider.addValue(ProviderKeys.PROJECT, new EntityRelation("31", "EC1914"));

        provider.addValue(ProviderKeys.TASK, new EntityRelation("132", "Update Information"));
        
        provider.addValue(ProviderKeys.TICKET, new EntityRelation("132", "Synchronization on portal doesn't work!"));
        return provider;
	}
}
