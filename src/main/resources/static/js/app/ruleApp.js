var app = angular.module('crudApp',['ui.router','ngStorage']);

console.log("app.js::crudApp");
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
                templateUrl: 'partials/listrule',
                controller:'RuleController',
                controllerAs:'rulectrl',
                resolve: {
                    rules: function ($q, RuleService) {
                        console.log('Load all Rules');
                        var deferred = $q.defer();
                        RuleService.loadAllRules().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });

        $urlRouterProvider.otherwise('/');
    }]);

