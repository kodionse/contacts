angular.module('contactsApp')
    .controller('ContactListController', ['$scope', '$routeParams', 'ContactService', function ($scope, $routeParams, contactService) {
        $scope.pageTitle = "Contact list";
        $scope.contacts = contactService.query()
}]);
