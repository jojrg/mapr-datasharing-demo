'use strict';

angular.module('crudApp').factory('RuleService',
    ['$localStorage', '$http', '$q','$location',
        function ($localStorage, $http, $q,$location) {

            console.log("$location.absUrl() value: " + $location.absUrl());

            var origUrl = $location.absUrl();
            var baseValue =  origUrl.substring(0,origUrl.length-2);
            var userServiceApiValue = baseValue + "api/user/";
            var ruleServiceAPIValue = baseValue + "api/rule/";
            var fieldNamesServiceApiValue = baseValue + "api/config/fieldnames/";
            var datafilternamesServiceApiValue = baseValue + "api/config/datafilternames/";

            var urls =
            {
               BASE: baseValue,
               USER_SERVICE_API : userServiceApiValue,
               RULE_SERVICE_API : ruleServiceAPIValue,
               FIELDNAMES_SERVICE_API : fieldNamesServiceApiValue,
               DATAFILTERNAMES_SERVICE_API : datafilternamesServiceApiValue
            }


            var factory = {
                loadAllRules: loadAllRules,
                getAllRules: getAllRules,
                getRule: getRule,
                createRule: createRule,
                updateRule: updateRule,
                removeRule: removeRule,
                sayHello: sayHello,
                loadFieldNames:loadFieldNames,
                loadDataFilterNames:loadDataFilterNames
            };

            return factory;


            function sayHello() {
              console.log("RuleService::sayshello");
            }


            function loadAllRules() {
                console.log('Fetching all rules');
                var deferred = $q.defer();
                $http.get(urls.RULE_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all rules');
                            $localStorage.rules = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading rules');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllRules(){
                return $localStorage.rules;
            }

            function getRule(id) {
                console.log('Fetching Rule with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.RULE_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Rule with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Rule with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createRule(rule) {
                console.log('Creating Rule');
                var deferred = $q.defer();
                $http.post(urls.RULE_SERVICE_API, rule)
                    .then(
                        function (response) {
                            loadAllRules();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Rule : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateRule(rule, id) {
                console.log('Updating Rule with id '+id);
                var deferred = $q.defer();
                $http.put(urls.RULE_SERVICE_API + id, rule)
                    .then(
                        function (response) {
                            loadAllRules();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Rule with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeRule(id) {
                console.log('Removing Rule with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.RULE_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllRules();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Rule with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

           function loadFieldNames() {
                console.log('mdRuleService::loadFieldNames');
                var deferred = $q.defer();
                $http.get(urls.FIELDNAMES_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully fieldname');
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading fieldnames');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

           function loadDataFilterNames() {
                console.log('Fetching dataFilterNames');
                var deferred = $q.defer();
                $http.get(urls.DATAFILTERNAMES_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully dataFilterNames');
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading dataFilterNames');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }



        }
    ]);