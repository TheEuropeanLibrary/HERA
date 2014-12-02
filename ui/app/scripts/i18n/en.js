angular
    .module("telApp")
    .config(["$translateProvider",
        function ($translateProvider) {
            $translateProvider
                .translations("en", {
                    HOME: "Home",
                    SAVE: "Save",
                    CANCEL: "Cancel",
                    EDIT: "Edit",
                    ORGANISATIONS: {
                        ORGANISATIONS_LABEL: "Organisations",
                        GENERAL: "General",
                        ADDRESS: "Address",
                        MEMBERSHIP: "Membership",
                        PORTAL: "Portal",
                        DATASETS: "Datasets",
                        CONTACTS: "Contacts",
                        IMAGES: "Images",
                        TICKETS: "Tickets",
                        TASKS: "Tasks",
                        IDENTIFIER: "Identifier",
                        TYPE: "Organisation type",
                        COUNTRY: "Country",
                        LANGUAGE: "Language",
                        NAME: "Name",
                        NAME_TYPE: "Name type",
                        ADD_DATASET: "Add dataset",
                        VIEW_IN_PORTAL: "View in portal",
                        ROLE: "Role",
                        EMAIL: "E-mail",
                        STATUS: "Status",
                        PORTAL_STATUS: "Portal status",
                        COORDINATES: "Coordinates",
                        LINK_TYPE: "Link type",
                        URL: "Url",
                        ADD_IMAGE: "Add image",
                        STREET: "Street",
                        POSTCODE: "Postcode",
                        CITY: "City",
                        FAX: "Fax",
                        PHONE: "Phone",
                        WEBSITE: "Website",
                        MEMBER: "Member",
                        DEA: "DEA",
                        EOD: "EOD",
                        LIBRARY_ORGANISATION: "Library organisation",
                        AGGREGATOR: "Aggregator",
                        CONSORTIUM_TYPE: "Consortium type",
                        NOTES: "Notes",
                        MEMBERSHIP_TYPE: "Membership type"
                    },
                    ADD_IMAGE: {
                        IMAGE_URL: "Image url",
                        ADD_IMAGE: "Add image"
                    },
                    ERRORS: {
                        INVALID_FAX: "Invalid fax",
                        INVALID_PHONE: "Invalid phone",
                        INVALID_EMAIL: "Invalid e-mail"
                    }
                });
        }
    ]);
