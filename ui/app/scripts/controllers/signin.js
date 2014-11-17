"use strict";

angular.module("telApp")
    .controller("SignInCtrl", function ($scope) {

        $scope.resetPasswordVisible = false;

        $scope.toggleResetPassword = function ($e) {
            $e.preventDefault();
            $e.stopPropagation();
            $scope.resetPasswordVisible = !$scope.resetPasswordVisible;
        }
    });
