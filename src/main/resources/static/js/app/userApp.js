var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:9000/SpringBootCRUDApp',
    USER_SERVICE_API : 'http://localhost:9000/SpringBootCRUDApp/api/user/',
    RULE_SERVICE_API : 'http://localhost:9000/SpringBootCRUDApp/api/rule/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'UserController',
                controllerAs:'ctrl',
                resolve: {
                    users: function ($q, UserService) {
                        console.log('Load all Users');
                        var deferred = $q.defer();
                        UserService.loadAllUsers().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            })
            .state('rule', {
                url: '/rule',
                templateUrl: 'partials/ruleList',
                controller:'RuleController',
                controllerAs:'rulectrl',
                resolve: {
                    rules: function ($q, UserService) {
                        console.log('Load all Rules');
                        var deferred = $q.defer();
                        RuleService.loadAllRules().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });

        $urlRouterProvider.otherwise('/');
    }]);

