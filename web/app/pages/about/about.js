var myApp = angular.module('contactsApp')

    .controller('AboutController', ['$scope', function ($scope) {
        $scope.pageTitle = 'About';
        $scope.about = 'About this app';
}]);