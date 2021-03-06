'use strict';

/**
 * @ngdoc overview
 * @name telApp
 * @description
 * # telApp
 *
 * Main module of the application.
 */
angular
    .module("telApp", [
        "ngMessages",
        "ngSanitize",
        "ngTouch",
        "ui.bootstrap",
        "ui.router",
        "ui.select",
        "ui.utils",
        "bmComponents",
        "pascalprecht.translate"
    ])
    .config(["$stateProvider", "$locationProvider", "$httpProvider", "bmApiUrlsProvider", "bmCookiesProvider",
        "$translateProvider",
        function ($stateProvider, $locationProvider, $httpProvider, bmApiUrlsProvider, bmCookiesProvider, $translateProvider) {
            $locationProvider.hashPrefix("!");
            $stateProvider
                .state("main", {
                    url: "/main",
                    templateUrl: "views/main.html",
                    controller: "MainCtrl"
                })
                .state("signin", {
                    url: "/",
                    templateUrl: "views/signin.html",
                    controller: "SignInCtrl"
                })
                .state("plans", {
                    url: "/plans",
                    templateUrl: "views/plans.html",
                    controller: "PlansCtrl"
                })
                .state("signup", {
                    url: "/signup",
                    templateUrl: "views/signup.html",
                    controller: "SignUpCtrl"
                })
                //.state("organization", {
                //    url: "/organization",
                //    templateUrl: "views/organization.html",
                //    controller: "OrganizationCtrl"
                //})
                //.state("datasets", {
                //    url: "/datasets",
                //    templateUrl: "views/datasets.html",
                //    controller: "DatasetsCtrl"
                //})
                .state("app", {
                    url: "/app",
                    abstract: true,
                    //template: "<ui-view/>"
                    templateUrl: "views/app.html",
                    controller: "PlansCtrl"
                })
                .state("app.administration", {
                    url: "/administration",
                    abstract: true,
                    template: "<div ui-view></div>"
                })
                .state("app.administration.organisation", {
                    url: "/organisation",
                    templateUrl: "views/organization.html",
                    controller: "OrganizationCtrl"
                })
                .state("app.administration.datasets", {
                    url: "/datasets",
                    templateUrl: "views/datasets.html",
                    controller: "DatasetsCtrl"
                });

            //bmApiUrlsProvider.setHostname("tel", "localhost");
            //bmApiUrlsProvider.setPort("tel", 3000);
            //bmApiUrlsProvider.setUrlGenerator("tel", function (hostname, port) {
            //    return hostname + ":" + port + "/hera";
            //});
            bmApiUrlsProvider.setUrlGenerator("tel", function () {
                return "/hera-rest-administration";
            });
            bmApiUrlsProvider.setUrls("tel", {
                "login": "/login",
                "providers": "/providers",
                "datasets": "/datasets"
            });

            $httpProvider.interceptors.push("telAuthInterceptor");

            bmCookiesProvider.cookiePrefix = "tel";

            $translateProvider.preferredLanguage("en");
        }
    ])
    .run(["$state", "$http", "bmCookies",
        function ($state, $http, bmCookies) {
            if (bmCookies.hasItem("Authorization")) {
                $http.defaults.headers.common["Authorization"] = bmCookies.getItem("Authorization");
                $state.go("app.administration.organisation");
            } else if (!$state.current.name) {
                $state.go("signin");
            }
        }
    ]);

angular.module("telApp").filter('propsFilter', function() {
    return function(items, props) {
        var out = [];

        if (angular.isArray(items)) {
            items.forEach(function(item) {
                var itemMatches = false;

                var keys = Object.keys(props);
                for (var i = 0; i < keys.length; i++) {
                    var prop = keys[i];
                    var text = props[prop].toLowerCase();
                    if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
                        itemMatches = true;
                        break;
                    }
                }

                if (itemMatches) {
                    out.push(item);
                }
            });
        } else {
            // Let the output be the input untouched
            out = items;
        }

        return out;
    };
});
