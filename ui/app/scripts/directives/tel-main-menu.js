"use strict";

/**
 * @ngdoc directive
 * @name telApp.directive:telMainMenu
 * @description
 * # telMainMenu
 */
angular.module("telApp")
    .directive("telMainMenu", function () {
        return {
            restrict: "A",
            link: function postLink(scope, element, attrs) {
                angular.element(".tel-dropdown > a", element)
                    .on("click", function (e) {
                        var $this = $(this);
                        e.preventDefault();
                        $this.parent().toggleClass("tel-dropdown-open");
                        if (element.hasClass("small-menu")) {
                            if ($this.parent().hasClass("tel-dropdown-open")) {
                                $this.next().show().animate({
                                    opacity: 1
                                    //left: 56
                                });
                            } else {
                                $this.next().animate({
                                        opacity: 0
                                        //left: 0
                                    }, function () {
                                        $this.next().hide();
                                    });
                            }
                        } else {
                            if ($this.parent().hasClass("tel-dropdown-open")) {
                                $this.next().slideDown();
                            } else {
                                $this.next().slideUp();
                            }
                        }
                    });

                angular.element(".tel-dropdown", element)
                    .on("mouseenter", function () {
                        if (element.hasClass("small-menu")) {
                            var $this = $(this);
                            var $menu = $this.children("ul");
                            $this.toggleClass("tel-dropdown-open");
                            $menu.show().animate({
                                opacity: 1
                            });
                        }
                    })
                    .on("mouseleave", function () {
                        if (element.hasClass("small-menu")) {
                            var $this = $(this);
                            var $menu = $this.children("ul");
                            $this.toggleClass("tel-dropdown-open");
                            $menu.animate({
                                    opacity: 0
                                    //left: 0
                                }, function () {
                                    $menu.hide();
                                });
                        }
                    });

                scope.$on("tel:toggleMenu", function () {
                    element.toggleClass("small-menu");
                    element.parent().toggleClass("small-menu");
                    angular.element(".tel-app-wrapper").toggleClass("menu-open");
                });
            }
        };
    });
