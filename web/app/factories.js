angular.module('contactsApp')
    .factory('ContactService', ['$resource', function($resource) {
        return $resource('http://localhost:8080/contacts/:contactId');
}]);