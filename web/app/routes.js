app.config(['$routeProvider', function($routeProvider) {
    $routeProvider

        .when('/', {
            templateUrl: 'views/contact-list/contact-list.html',
            controller: 'ContactListController'
        })

        .when('/contact/:contactId', {
            templateUrl: 'views/contact-details/contact-details.html',
            controller: 'ContactDetailsController'
        })

        .when('/error', {
            templateUrl: 'views/other/404.html'
        })

        .otherwise({redirectTo: '/error'});
}]);