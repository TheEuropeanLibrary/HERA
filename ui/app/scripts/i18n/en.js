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
                        NAME_TYPE: "Name type"
                    }
                });
        }
    ]);
