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
        "ngAnimate",
        "ngMessages",
        "ngSanitize",
        "ngTouch",
        "ui.bootstrap",
        "ui.router",
        "ui.select",
        "ui.utils",
        "bmComponents"
    ])
    .config(["$stateProvider", "$locationProvider", "$httpProvider", "bmApiUrlsProvider", "bmCookiesProvider",
        function ($stateProvider, $locationProvider, $httpProvider, bmApiUrlsProvider, bmCookiesProvider) {
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
                });

            bmApiUrlsProvider.setHostname("tel", "localhost");
            bmApiUrlsProvider.setPort("tel", 3000);
            bmApiUrlsProvider.setUrlGenerator("tel", function (hostname, port) {
                return hostname + ":" + port + "/hera";
            });
            bmApiUrlsProvider.setUrls("tel", {
                "login": "/login"
            });

            $httpProvider.interceptors.push("telAuthInterceptor");

            bmCookiesProvider.prefix = "tel";
        }
    ])
    .run(["$state", "$http", "bmCookies",
        function ($state, $http, bmCookies) {
            if (bmCookies.hasItem("Authorization")) {
                $http.defaults.headers.common["Authorization"] = bmCookies.getItem("Authorization");
                $state.go("organizations");
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
