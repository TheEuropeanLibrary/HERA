"use strict";

/**
 * @ngdoc service
 * @name telApp.Users
 * @description
 * # Users
 * Service in the telApp.
 */
angular.module("telApp")
    .service("Users", ["$http", "$location", "Base64", "bmApiUrls", "bmCookies", "$rootScope",
        function ($http, $location, Base64, bmApiUrls, bmCookies, $rootScope) {
            return {
                login: function (credentials) {
                    return $http
                        .get(bmApiUrls.getUrl("tel", "login"), {
                            headers: {
                                Authorization: "Basic " + Base64.encode(credentials.username + ':' + credentials.password)
                            }
                        })
                        .then(function (data) {
                            var expirationDate = new Date(),
                                isChrome = (/Chrome\/[.0-9]/gi).test(navigator.userAgent),
                                domain = isChrome && $location.host().indexOf(".") < 0 ? "" : $location.host();

                            expirationDate.setDate(expirationDate.getDate() + 1);
                            bmCookies.setItem("Authorization", "Basic " + Base64.encode(credentials.username + ':' + credentials.password), expirationDate, "/", domain);
                            $http.defaults.headers.common["Authorization"] = "Basic " + Base64.encode(credentials.username + ':' + credentials.password);
                            return data;
                        });
                }
            }
        }
    ]);
