angular.module('contactsApp')
    .controller('ContactDetailsController', ['$scope', '$routeParams', 'ContactService', function($scope, $routeParams, contactService) {
        console.log('ContactDetailsController');
        $scope.pageTitle = "Contact details";
        $scope.contact = contactService.get({ contactId: $routeParams.contactId });
}]);
