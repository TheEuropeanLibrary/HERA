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
                        GENERAL_INFORMATION: "General information",
                        CONTACT_INFORMATION: "Contact information",
                        ADDITIONAL_INFORMATION: "Additional information",
                        PORTAL_INFORMATION: "Portal information",
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
                        ADD_IMAGE: "Add image"
                    },
                    ADD_IMAGE: {
                        IMAGE_URL: "Image url",
                        ADD_IMAGE: "Add image"
                    }
                });
        }
    ]);
