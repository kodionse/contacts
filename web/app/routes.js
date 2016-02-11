angular.module('contactsApp')
    .config(['$routeProvider', function($routeProvider) {

        $routeProvider

            .when('/', {
                templateUrl: 'pages/contact-list/contact-list.html',
                controller: 'ContactListController'
            })

            .when('/contact/:contactId', {
                templateUrl: 'pages/contact-details/contact-details.html',
                controller: 'ContactDetailsController'
            })

            .when('/about', {
                templateUrl: 'pages/about/about.html',
                controller: 'AboutController'
            })

            .when('/error', {
                templateUrl: 'pages/other/404.html'
            })

            .otherwise({redirectTo: '/error'});
}]);