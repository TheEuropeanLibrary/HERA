"use strict";

angular.module("telApp")
    .controller("SignInCtrl", ["$scope", "Users",
        function ($scope, Users) {

            $scope.data = {};

            $scope.resetPasswordVisible = false;

            $scope.toggleResetPassword = function ($e) {
                $e.preventDefault();
                $e.stopPropagation();
                $scope.resetPasswordVisible = !$scope.resetPasswordVisible;
            };

            $scope.login = function () {
                Users.login($scope.data)
                    .then(function () {

                    }, function () {
                        $scope.invalidCredentials = true;
                    });
            };
        }
    ]);
