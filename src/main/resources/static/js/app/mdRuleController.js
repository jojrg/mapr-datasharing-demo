'use strict';

angular.module('crudApp')
.controller('TitleController', function($scope) {
       $scope.title = 'MapR Data Sharing Demo';
     })

.controller('RuleController',
    ['RuleService', '$scope',  function( RuleService, $scope) {

        var self = this;
        self.rule = {};


        self.submit = submit;
        self.getAllRules = getAllRules;
        self.createRule = createRule;
        self.updateRule = updateRule;
        self.removeRule = removeRule;
        self.editRule = editRule;
        self.reset = reset;
        self.getFieldNames = getFieldNames,
        self.getDataFilterNames = getDataFilterNames;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        $scope.activeReset = false;
        function sayHello() {
         console.log("RuleCtrl says hello");
         RuleService.sayHello();
        }


        var init = function () {

          console.log("RuleController::init");
          console.log("calling RuleService::loadAllRules in context of initialization");
          RuleService.loadAllRules();
          console.log("RuleController::init::calling controller method getFieldNames()");
          getFieldNames();
          getDataFilterNames();


        }

        init();

        function submit() {
            console.log('Submitting');
            if (self.rule.id === undefined || self.rule.id === null) {
                console.log('Saving New rule', self.rule);
                createRule(self.rule);
            } else {
               updateRule(self.rule, self.rule.id);
               console.log('Rule updated with id ', self.rule.id);
            }
        }

        function createRule(rule) {
            console.log('About to create rule');
            RuleService.createRule(rule)
                .then(
                    function (response) {
                        console.log('Rule created successfully');
                        self.successMessage = 'Rule created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.rule={};
                        $scope.ruleForm.$setPristine();
                        $scope.ruleForm.$setUntouched();
                        $scope.activeReset = false;
                    },
                    function (errResponse) {
                        console.error('Error while creating Rule');
                        self.errorMessage = 'Error while creating Rule: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    });

        }


        function updateRule(rule, id){
            console.log('About to update rule');
            RuleService.updateRule(rule, id)
                .then(
                    function (response){
                        console.log('Rule updated successfully');
                        self.successMessage='Rule updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.rule={};
                        $scope.ruleForm.$setPristine();
                        $scope.ruleForm.$setUntouched();
                        $scope.activeReset = false;
                    },
                    function(errResponse){
                        console.error('Error while updating Rule');
                        self.errorMessage='Error while updating Rule '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeRule(id){
            console.log('About to remove Rule with id '+id);
            RuleService.removeRule(id)
                .then(
                    function(){
                        console.log('Rule '+id + ' removed successfully');
                        // clear form values if there are any
                        self.rule={};
                        $scope.ruleForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while removing rule '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllRules(){
            //console.log('mdRuleController:getAllRules');
            return RuleService.getAllRules();
        }

        function editRule(id) {
            self.successMessage='';
            self.errorMessage='';
            RuleService.getRule(id).then(
                function (rule) {
                    self.rule = rule;
                    $scope.activeReset = true;
                },
                function (errResponse) {
                    console.error('Error while removing rule ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            $scope.activeReset = false;
            self.successMessage='';
            self.errorMessage='';
            self.rule={};
            $scope.ruleForm.$setPristine(); //reset Form
            $scope.ruleForm.$setUntouched(); //reset Form

            angular.forEach($scope.ruleForm, function(ctrl, name) {
              // ignore angular fields and functions
              if (name.indexOf('$') != 0) {
                // iterate over all $errors for each field
                angular.forEach(ctrl.$error, function(value, name) {
                  // reset validity
                  ctrl.$setValidity(name, null);
                });
              }
            });

        }


       function getFieldNames() {
            self.successMessage='';
            self.errorMessage='';
            RuleService.loadFieldNames().then(
                function (fieldNames) {
                    console.log("mdRuleController::getFieldNames::completed");
                    var fieldNameObjects;
                    if (fieldNames && fieldNames.length > 0) {
                       fieldNameObjects = fieldNames.map(function(fieldValue) {
                         return {fieldName:fieldValue}
                       });
                       fieldNameObjects.push({fieldName:""});
                       $scope.fieldNames = fieldNameObjects;
                    }
                    else {
                       $scope.fieldNames = [];
                    }

                },
                function (errResponse) {
                    console.error('Error while retreiving fieldNames ' + errResponse.data);
                }
            );
        }


       function getDataFilterNames() {
            self.successMessage='';
            self.errorMessage='';
            RuleService.loadDataFilterNames().then(
                function (filterNames) {
                    console.log("mdRuleController::getDataFilterNames::completed");
                    var dataFilterObjects;
                    if (filterNames && filterNames.length > 0) {
                       dataFilterObjects = filterNames.map(function(filterValue) {
                         return {dataFilterName:filterValue}
                       });
                       dataFilterObjects.push({dataFilterName:""});
                       $scope.dataFilterNames = dataFilterObjects;
                    }
                    else {
                       $scope.dataFilterNames = [];
                    }

                },
                function (errResponse) {
                    console.error('Error while retreiving fieldNames ' + errResponse.data);
                }
            );
        }



    }
]);

