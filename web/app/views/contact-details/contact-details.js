app.controller('ContactDetailsController', ['$scope', '$routeParams', 'ContactService', function($scope, $routeParams, contactService) {

    $scope.pageTitle = "Contact details";
    $scope.contact = contactService.get({ contactId: $routeParams.contactId });

}]);
