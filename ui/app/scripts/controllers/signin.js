"use strict";

angular.module("telApp")
    .controller("SignInCtrl", ["$scope", "Users", "$state",
        function ($scope, Users, $state) {

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
                        $state.go("app.administration.organisation");
                    }, function () {
                        $scope.invalidCredentials = true;
                    });
            };
        }
    ]);
