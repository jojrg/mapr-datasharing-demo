'use strict';

angular.module('crudApp')
.controller('TitleController', function($scope) {
       $scope.title = 'MapR Data Sharing Demo';
     })

.controller('RuleController',
    ['RuleService', '$scope',  function( RuleService, $scope) {

        var self = this;
        self.rule = {};
        self.rules=[];

        $scope.fieldNames =["field1","field2","field3","field4"];
        $scope.ruleNames =["Hidden","Masked","UDF1","UDF2"];

        self.submit = submit;
        self.getAllRules = getAllRules;
        self.createRule = createRule;
        self.updateRule = updateRule;
        self.removeRule = removeRule;
        self.editRule = editRule;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.rule.id === undefined || self.rule.id === null) {
                console.log('Saving New rule', self.rule);
                createRule(self.rule);
            } else {
                updateRule(self.rule, self.rule.id);
                console.log('User updated with id ', self.rule.id);
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
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Rule');
                        self.errorMessage = 'Error while creating Rule: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
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
                        $scope.myForm.$setPristine();
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
                    },
                    function(errResponse){
                        console.error('Error while removing rule '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllRules(){
            return RuleService.getAllRules();
        }

        function editRule(id) {
            self.successMessage='';
            self.errorMessage='';
            RuleService.getRule(id).then(
                function (rule) {
                    self.rule = rule;
                },
                function (errResponse) {
                    console.error('Error while removing rule ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.rule={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ])
.controller('AppCtrl', function($scope) {
   $scope.rule = {
       username: '',
       fieldname: '',
       name: 'Rule1'
   };

   $scope.fields = ('field1 field2 field3 field4').split(' ').map(function(field) {
      return {name: field};
   });
 });
