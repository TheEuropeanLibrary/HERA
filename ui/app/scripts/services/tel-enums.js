"use strict";

/**
 * @ngdoc service
 * @name telApp.Users
 * @description
 * # Users
 * Service in the telApp.
 */
angular
    .module("telApp")
    .factory("telEnums", ["$q", "$translate",
        function ($q, $translate) {
            var countries = [
                "AD",
                "AE",
                "AF",
                "AG",
                "AI",
                "AL",
                "AM",
                "AN",
                "AO",
                "AQ",
                "AR",
                "AS",
                "AT",
                "AU",
                "AW",
                "AX",
                "AZ",
                "BA",
                "BB",
                "BD",
                "BE",
                "BF",
                "BG",
                "BH",
                "BI",
                "BJ",
                "BL",
                "BM",
                "BN",
                "BO",
                "BR",
                "BS",
                "BT",
                "BV",
                "BW",
                "BY",
                "BZ",
                "CA",
                "CC",
                "CD",
                "CF",
                "CG",
                "CH",
                "CI",
                "CK",
                "CL",
                "CM",
                "CN",
                "CO",
                "CR",
                "CS",
                "CU",
                "CV",
                "CX",
                "CY",
                "CZ",
                "DE",
                "DJ",
                "DK",
                "DM",
                "DO",
                "DZ",
                "EC",
                "EE",
                "EG",
                "EH",
                "ER",
                "ES",
                "ET",
                "FI",
                "FJ",
                "FK",
                "FM",
                "FO",
                "FR",
                "GA",
                "GB",
                "GD",
                "GE",
                "GF",
                "GG",
                "GH",
                "GI",
                "GL",
                "GM",
                "GN",
                "GP",
                "GQ",
                "GR",
                "GS",
                "GT",
                "GU",
                "GW",
                "GY",
                "HK",
                "HM",
                "HN",
                "HR",
                "HT",
                "HU",
                "ID",
                "IE",
                "IL",
                "IM",
                "IN",
                "IO",
                "IQ",
                "IR",
                "IS",
                "IT",
                "JE",
                "JM",
                "JO",
                "JP",
                "KE",
                "KG",
                "KH",
                "KI",
                "KM",
                "KN",
                "KP",
                "KR",
                "KW",
                "KY",
                "KZ",
                "LA",
                "LB",
                "LC",
                "LI",
                "LK",
                "LR",
                "LS",
                "LT",
                "LU",
                "LV",
                "LY",
                "MA",
                "MC",
                "MD",
                "ME",
                "MF",
                "MG",
                "MH",
                "MK",
                "ML",
                "MM",
                "MN",
                "MO",
                "MP",
                "MQ",
                "MR",
                "MS",
                "MT",
                "MU",
                "MV",
                "MW",
                "MX",
                "MY",
                "MZ",
                "NA",
                "NC",
                "NE",
                "NF",
                "NG",
                "NI",
                "NL",
                "NO",
                "NP",
                "NR",
                "NU",
                "NZ",
                "OM",
                "PA",
                "PE",
                "PF",
                "PG",
                "PH",
                "PK",
                "PL",
                "PM",
                "PN",
                "PR",
                "PS",
                "PT",
                "PW",
                "PY",
                "QA",
                "RE",
                "RO",
                "RS",
                "RU",
                "RW",
                "SA",
                "SB",
                "SC",
                "SD",
                "SE",
                "SG",
                "SH",
                "SI",
                "SJ",
                "SK",
                "SL",
                "SM",
                "SN",
                "SO",
                "SR",
                "SS",
                "ST",
                "SV",
                "SY",
                "SZ",
                "TC",
                "TD",
                "TF",
                "TG",
                "TH",
                "TJ",
                "TK",
                "TL",
                "TM",
                "TN",
                "TO",
                "TR",
                "TT",
                "TV",
                "TW",
                "TZ",
                "UA",
                "UG",
                "UM",
                "US",
                "UY",
                "UZ",
                "VA",
                "VC",
                "VE",
                "VG",
                "VI",
                "VN",
                "VU",
                "WF",
                "WS",
                "YE",
                "YT",
                "ZA",
                "ZM",
                "ZW",
                "XX"
            ];

            var languages = [
                "UND",
                "MUL",
                "ZXX",
                "AFR",
                "ALB",
                "AMH",
                "ARA",
                "ARM",
                "AZE",
                "BEL",
                "BOS",
                "BRA",
                "BUL",
                "CAT",
                "CHI",
                "CZE",
                "DAN",
                "DUT",
                "ENG",
                "EST",
                "EPO",
                "FIN",
                "FRE",
                "GER",
                "GEO",
                "GLE",
                "GLG",
                "GRE",
                "GRC",
                "GSW",
                "HEB",
                "HIN",
                "HRV",
                "HUN",
                "ICE",
                "IND",
                "ITA",
                "JPN",
                "LAT",
                "LAV",
                "LTG",
                "LIT",
                "LTZ",
                "MAC",
                "MAR",
                "MLT",
                "MSA",
                "NOR",
                "PER",
                "POL",
                "POR",
                "ROH",
                "RUM",
                "RUS",
                "SLO",
                "SLV",
                "SPA",
                "SRP",
                "SWE",
                "TUR",
                "UKR",
                "VIE",
                "WEL",
                "YID",
                "AAR",
                "ABK",
                "ACE",
                "ACH",
                "ADA",
                "ADY",
                "AFA",
                "AFH",
                "AIN",
                "AKA",
                "AKK",
                "ALE",
                "ALG",
                "ALT",
                "ANG",
                "ANP",
                "APA",
                "ARC",
                "ARG",
                "ARN",
                "ARP",
                "ART",
                "ARW",
                "ASM",
                "AST",
                "ATH",
                "AUS",
                "AVA",
                "AVE",
                "AWA",
                "AYM",
                "BAD",
                "BAI",
                "BAK",
                "BAL",
                "BAM",
                "BAN",
                "BAS",
                "BAT",
                "BEJ",
                "BEM",
                "BEN",
                "BER",
                "BHO",
                "BIH",
                "BIK",
                "BIN",
                "BIS",
                "BLA",
                "BNT",
                "BRE",
                "BTK",
                "BUA",
                "BUG",
                "BYN",
                "CAD",
                "CAI",
                "CAR",
                "CAU",
                "CEB",
                "CEL",
                "CHA",
                "CHB",
                "CHE",
                "CHG",
                "CHK",
                "CHM",
                "CHN",
                "CHO",
                "CHP",
                "CHR",
                "CHU",
                "CHV",
                "CHY",
                "CMC",
                "COP",
                "COR",
                "COS",
                "CPE",
                "CPF",
                "CPP",
                "CRE",
                "CRH",
                "CRP",
                "CSB",
                "CUS",
                "DAK",
                "DAR",
                "DAY",
                "DEL",
                "DEN",
                "DGR",
                "DIN",
                "DIV",
                "DOI",
                "DRA",
                "DSB",
                "DUA",
                "DUM",
                "DYU",
                "DZO",
                "EFI",
                "EGY",
                "ARZ",
                "SRI",
                "WLM",
                "PES",
                "AXM",
                "PAN",
                "SRM",
                "PSP",
                "AEN",
                "EKA",
                "ELX",
                "ENM",
                "BAQ",
                "EWE",
                "EWO",
                "FAN",
                "FAO",
                "FAT",
                "FIJ",
                "FIL",
                "FIU",
                "FON",
                "FRM",
                "FRO",
                "FRR",
                "FRS",
                "FRY",
                "FUL",
                "FUR",
                "GAA",
                "GAY",
                "GBA",
                "GEM",
                "GEZ",
                "GIL",
                "GLA",
                "GLV",
                "GMH",
                "GOH",
                "GON",
                "GOR",
                "GOT",
                "GRB",
                "GRN",
                "GUJ",
                "GWI",
                "HAI",
                "HAT",
                "HAU",
                "HAW",
                "HER",
                "HIL",
                "HIM",
                "HIT",
                "HMN",
                "HMO",
                "HSB",
                "HUP",
                "IBA",
                "IBO",
                "IDO",
                "III",
                "IJO",
                "IKU",
                "ILE",
                "ILO",
                "INA",
                "INC",
                "INE",
                "INH",
                "IPK",
                "IRA",
                "IRO",
                "JAV",
                "JBO",
                "JPR",
                "JRB",
                "KAA",
                "KAB",
                "KAC",
                "KAL",
                "KAM",
                "KAN",
                "KAR",
                "KAS",
                "KAU",
                "KAW",
                "KAZ",
                "KBD",
                "KHA",
                "KHI",
                "KHM",
                "KHO",
                "KIK",
                "KIN",
                "KIR",
                "KMB",
                "KOK",
                "KOM",
                "KON",
                "KOR",
                "KOS",
                "KPE",
                "KRC",
                "KRL",
                "KRO",
                "KRU",
                "KUA",
                "KUM",
                "KUR",
                "KUT",
                "LAD",
                "LAH",
                "LAM",
                "LAO",
                "LEZ",
                "LIM",
                "LIN",
                "LOL",
                "LOZ",
                "LUA",
                "LUB",
                "LUG",
                "LUI",
                "LUN",
                "LUO",
                "LUS",
                "MAD",
                "MAG",
                "MAH",
                "MAI",
                "MAK",
                "MAL",
                "MAN",
                "MAP",
                "MAS",
                "MDF",
                "MDR",
                "MEN",
                "MGA",
                "MIC",
                "MIN",
                "MIS",
                "MKH",
                "MLG",
                "MNC",
                "MNI",
                "MNO",
                "MOH",
                "MON",
                "MOS",
                "MAO",
                "MUN",
                "MUS",
                "MWL",
                "MWR",
                "BUR",
                "MYN",
                "MYV",
                "NAH",
                "NAI",
                "NAP",
                "NAU",
                "NAV",
                "NBL",
                "NDE",
                "NDO",
                "NDS",
                "NEP",
                "NEW",
                "NIA",
                "NIC",
                "NIU",
                "NNO",
                "NOB",
                "NOG",
                "NON",
                "NQO",
                "NSO",
                "NUB",
                "NWC",
                "NYA",
                "NYM",
                "NYN",
                "NYO",
                "NZI",
                "OCI",
                "OJI",
                "ORI",
                "ORM",
                "OSA",
                "OSS",
                "OTA",
                "OTO",
                "PAA",
                "PAG",
                "PAL",
                "PAM",
                "PAP",
                "PAU",
                "PEO",
                "PHI",
                "PHN",
                "PLI",
                "PON",
                "PRA",
                "PRO",
                "PUS",
                "QAA",
                "QUE",
                "RAJ",
                "RAP",
                "RAR",
                "ROA",
                "ROM",
                "RUN",
                "RUP",
                "SAD",
                "SAG",
                "SAH",
                "SAI",
                "SAL",
                "SAM",
                "SAN",
                "SAS",
                "SAT",
                "SCN",
                "SCO",
                "SEL",
                "SCR",
                "SEM",
                "SGA",
                "SGN",
                "SHN",
                "SID",
                "SIN",
                "SIO",
                "SIT",
                "SLA",
                "SMA",
                "SME",
                "SMI",
                "SMJ",
                "SMN",
                "SMO",
                "SMS",
                "SNA",
                "SND",
                "SNK",
                "SOG",
                "SOM",
                "SON",
                "SOT",
                "SRD",
                "SRN",
                "SRR",
                "SSA",
                "SSW",
                "SUK",
                "SUN",
                "SUS",
                "SUX",
                "SWA",
                "SYC",
                "SYR",
                "TAH",
                "TAI",
                "TAM",
                "TAT",
                "TEL",
                "TEM",
                "TER",
                "TET",
                "TGK",
                "TGL",
                "THA",
                "TIB",
                "TIG",
                "TIR",
                "TIV",
                "TKL",
                "TLH",
                "TLI",
                "TMH",
                "TOG",
                "TON",
                "TPI",
                "TSI",
                "TSN",
                "TSO",
                "TUK",
                "TUM",
                "TUP",
                "TUT",
                "TVL",
                "TWI",
                "TYV",
                "UDM",
                "UGA",
                "UIG",
                "UMB",
                "URD",
                "UZB",
                "VAI",
                "VEN",
                "VOL",
                "VOT",
                "WAK",
                "WAL",
                "WAR",
                "WAS",
                "WEN",
                "WLN",
                "WOL",
                "XAL",
                "XHO",
                "YAO",
                "YAP",
                "YOR",
                "YPK",
                "ZAP",
                "ZBL",
                "ZEN",
                "ZHA",
                "ZND",
                "ZUL",
                "ZUN",
                "ZZA"
            ];

            var nameTypes = [
                "NATIVE",
                "MAIN",
                "INTERNAL",
                "ACRONYM"
            ];

            var providerTypes = ["NATIONAL_LIBRARY", "RESEARCH_LIBRARY"];

            var portalStatuses = ["PREPARATION", "INTERNAL", "ACCEPTANCE", "SUSPENDED", "LIVE"];

            var linkTypes = ["LOGO", "WEBSITE", "CONTACTS", "OPENING", "WIKIPEDIA_ENGLISH", "WIKIPEDIA_NATIVE"];

            var membershipTypes = ["MEMBERS", "LIBRARY_PROSPECT", "INSTITUTION_PROSPECT", "CONTRIBUTOR"];

            var libraryOrganisations = ["CERL", "CENL", "LIBER", "OTHER"];

            var consortiumTypes = ["AGGREGATION", "PURCHASING"];

            var datasetTypes = ["BIBLIOGRAPHY", "CATALOGUE", "COLLECTION", "DATABASE", "DATASET", "FINDING_AIDS", "PASSTHROUGH", "PERSONAL_COLLECTION", "REPOSITORY", "VIRUTAL_EXHIBITION"];

            var ingestionStatuses = ["ACCEPTANCE", "PUBLISH"];

            var agreementStatuses = ["NONE", "READY", "INTERNAL_READY", "PUBLISH"];

            var distributionFormats = ["MARC", "EDM_LOD", "DCMI", "DC", "EAD", "EDM", "MODS", "OTHER"];

            var sourceLicenses = ["BY", "BY_ND", "BY_NC", "BY_NC_ND", "BY_NC_SA", "BY_SA", "FRANCE", "OTHER"];

            var distributionLicenses = ["BY", "BY_ND", "BY_NC", "BY_NC_ND", "BY_NC_SA", "BY_SA", "FRANCE", "OTHER"];

            var digitisationStatuses = ["DIGITISED", "NON_DIGITISED", "PARTIALLY_DIGITISED"];

            var dataTypes = ["FULLTEXT", "CONTROLLED_VOCABULARY", "CONTENT", "AUTHORITY", "DIGITAL"];

            var datasetLinkType = ["ACCESS_RIGHTS", "PROVENANCE", "BIBLIOGRAPHY", "SOURCE"];

            var noteTypes = ["ACCESS_RIGHTS", "PROVENANCE", "BIBLIOGRAPHY", "SOURCE"];

            var disciplines = ["HUMANITIES"];

            var spatialCoverages = ["PARIS"];

            var timeCoverages = ["RENAISSANCE"];

            var itemTypes = ["BLUE_PRINT"];

            var subjects = ["MEDIEVAL_HISTORY"];

            return {
                getCountries: function () {
                    return $q.when(countries.map(function (country) {
                        return {
                            label: $translate.instant("COUNTRIES." + country),
                            value: country
                        };
                    }));
                },
                getLanguages: function () {
                    return $q.when(languages.map(function (language) {
                        return {
                            label: $translate.instant("LANGUAGES." + language),
                            value: language
                        };
                    }));
                },
                getNameTypes: function () {
                    return $q.when(nameTypes.map(function (nameType) {
                        return {
                            label: "NAME_TYPE." + nameType,
                            value: nameType
                        };
                    }));
                },
                getProviderTypes: function () {
                    return $q.when(providerTypes.map(function (providerType) {
                        return {
                            label: "PROVIDER_TYPE." + providerType,
                            value: providerType
                        };
                    }));
                },
                getPortalStatuses: function () {
                    return $q.when(portalStatuses.map(function (portalStatus) {
                        return {
                            label: "PORTAL_STATUS." + portalStatus,
                            value: portalStatus
                        };
                    }));
                },
                getLinkTypes: function () {
                    return $q.when(linkTypes.map(function (linkType) {
                        return {
                            label: "LINK_TYPE." + linkType,
                            value: linkType
                        };
                    }));
                },
                getMembershipTypes: function () {
                    return $q.when(membershipTypes.map(function (m) {
                        return {
                            label: m,
                            value: m
                        };
                    }));
                },
                getLibraryOrganisations: function () {
                    return $q.when(libraryOrganisations.map(function (l) {
                        return {
                            label: $translate.instant("LIBRARY_ORGANISATION." + l),
                            value: l
                        };
                    }));
                },
                getConsortiumTypes: function () {
                    return $q.when(consortiumTypes.map(function (c) {
                        return {
                            label: "CONSORTIUM_TYPE." + c,
                            value: c
                        };
                    }));
                },
                getDatasetTypes: function () {
                    return $q.when(datasetTypes.map(function (d) {
                        return {
                            label: "DATASET_TYPE." + d,
                            value: d
                        };
                    }));
                },
                getIngestionStatuses: function () {
                    return $q.when(ingestionStatuses.map(function (i) {
                        return {
                            label: "INGESTION_STATUS." + i,
                            value: i
                        };
                    }));
                },
                getAgreementStatuses: function () {
                    return $q.when(agreementStatuses.map(function (a) {
                        return {
                            label: "AGREEMENT_STATUS." + a,
                            value: a
                        };
                    }));
                },
                getDistributionFormats: function () {
                    return $q.when(distributionFormats.map(function (d) {
                        return {
                            label: "DISTRIBUTION_FORMAT." + d,
                            value: d
                        };
                    }));
                },
                getSourceLicenses: function () {
                    return $q.when(sourceLicenses.map(function (s) {
                        return {
                            label: "SOURCE_LICENSE." + s,
                            value: s
                        };
                    }));
                },
                getDistributionLicenses: function () {
                    return $q.when(distributionLicenses.map(function (d) {
                        return {
                            label: "DISTRIBUTION_LICENSE." + d,
                            value: d
                        };
                    }));
                },
                getDigitisationStatuses: function () {
                    return $q.when(digitisationStatuses.map(function (d) {
                        return {
                            label: "DIGITISATION_STATUS." + d,
                            value: d
                        };
                    }));
                },
                getDataTypes: function () {
                    return $q.when(dataTypes.map(function (d) {
                        return {
                            label: "DATA_TYPE." + d,
                            value: d
                        };
                    }));
                },
                getDatasetLinkTypes: function () {
                    return $q.when(datasetLinkType.map(function (d) {
                        return {
                            label: "DATASET_LINK_TYPE." + d,
                            value: d
                        };
                    }));
                },
                getNoteTypes: function () {
                    return $q.when(noteTypes.map(function (n) {
                        return {
                            label: "NOTE_TYPE." + n,
                            value: n
                        };
                    }));
                },
                getDisciplines: function () {
                    return $q.when(disciplines.map(function (d) {
                        return {
                            label: "DISCIPLINE." + d,
                            value: d
                        };
                    }));
                },
                getSpatialCoverages: function () {
                    return $q.when(spatialCoverages.map(function (s) {
                        return {
                            label: "SPATIAL_COVERAGE." + s,
                            value: s
                        };
                    }));
                },
                getTimeCoverages: function () {
                    return $q.when(timeCoverages.map(function (t) {
                        return {
                            label: "TIME_COVERAGE." + t,
                            value: t
                        };
                    }));
                },
                getItemTypes: function () {
                    return $q.when(itemTypes.map(function (t) {
                        return {
                            label: "ITEM_TYPE." + t,
                            value: t
                        };
                    }));
                },
                getSubjects: function () {
                    return $q.when(subjects.map(function (s) {
                        return {
                            label: "SUBJECT." + s,
                            value: s
                        };
                    }));
                }
            }
        }
    ]);
