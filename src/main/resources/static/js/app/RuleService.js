'use strict';

angular.module('crudApp').factory('RuleService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllRules: loadAllRules,
                getAllRules: getAllRules,
                getRule: getRule,
                createRule: createRule,
                updateRule: updateRule,
                removeRule: removeRule
            };

            return factory;

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

        }
    ]);