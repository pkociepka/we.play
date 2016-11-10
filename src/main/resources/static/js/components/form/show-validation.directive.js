(function() {
    'use strict';

    angular
        .module('weplay')
        .directive('showValidation', showValidation);

    function showValidation () {
        return {
            restrict: 'A',
            require: 'form',
            link: linkFunc
        };

        function linkFunc (scope, element) {
            element.find('.form-group').each(function() {
                var $formGroup = angular.element(this);
                var $inputs = $formGroup.find('input[ng-model],textarea[ng-model],select[ng-model]');

                if ($inputs.length > 0) {
                    $inputs.each(function() {
                        var $input = angular.element(this);
                        scope.$watch(function() {
                            return $input.hasClass('ng-invalid') && $input.hasClass('ng-dirty');
                        }, function(isInvalid) {
                            $formGroup.toggleClass('has-error', isInvalid);
                        });
                    });
                }
            });
        }
    }
})();
