"use strict";

/**
 * @ngdoc directive
 * @name telApp.directive:telMainMenuToggle
 * @description
 * # telMainMenu
 */
angular.module("telApp")
    .directive("telMainMenuToggle", ["$rootScope",
        function ($rootScope) {
            return {
                restrict: "A",
                link: function postLink(scope, element, attrs) {
                    element.on("click", function () {
                        $rootScope.$broadcast("tel:toggleMenu");
                        element.toggleClass("open-menu");
                        element.next().find(".navbar-header").toggleClass("open-menu");
                    });
                }
            }
        }
    ]);
