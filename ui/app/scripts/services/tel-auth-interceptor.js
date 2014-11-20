"use strict";

/**
 * @ngdoc service
 * @name telApp.telAuthInterceptor
 * @description
 * # telAuthInterceptor
 * Service in the telApp.
 */
angular.module("telApp").factory("telAuthInterceptor", ["$injector", "$q",
    function ($injector, $q) {
        return {
            "responseError": function(rejection) {
                var $state = $injector.get("$state");
                if (rejection.status === 401 && rejection.config.url.indexOf("/login") < 0 && $state.current.name != "signin") {
                    $state.go("signin");
                }
                return $q.reject(rejection);
            }
        }
    }
]);
