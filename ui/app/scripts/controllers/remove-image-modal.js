"use strict";

/**
 * @ngdoc function
 * @name telApp.controller:AddImageModalCtrl
 * @description
 * # AddImageModalCtrl
 * Controller of the telApp
 */
angular.module("telApp")
    .controller("RemoveImageModalCtrl", ["$modalInstance", "$scope",
        function ($modalInstance, $scope) {

            $scope.ok = function () {
                $modalInstance.close();
            };

            $scope.cancel = function () {
                $modalInstance.dismiss();
            };
        }
    ]);
