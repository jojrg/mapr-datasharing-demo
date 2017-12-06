<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>MapR Demo</title>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,400italic">

  <link rel='stylesheet prefetch' href='https://cdn.gitcdn.link/cdn/angular/bower-material/v1.1.5/angular-material.css'>
  <link rel='stylesheet prefetch' href='https://material.angularjs.org/1.1.5/docs.css'>
  <link rel="stylesheet" href="css/mdstyle.css">


</head>

<body>
  <div ng-controller="RuleController as controller" layout="column" ng-cloak="" class="toolbardemoScrollShrink" ng-app="crudApp">

  <md-toolbar md-scroll-shrink="" ng-if="true" ng-controller="TitleController">
      <div class="md-toolbar-tools">
        <md-button>
     <img class="png-logo-icon" src="img/logo_mapr_red.svg" style="width: 140px; height: 45px;"></img>
       </md-button>
        <h3>
          <span>{{title}}</span>
        </h3>
      </div>
   </md-toolbar>

  <md-content layout-padding>


    <!--  List of Rule -->
    <md-card md-theme="{{ showDarkTheme ? 'dark-grey' : 'default' }}" md-theme-watch="">
       <md-card-title>
         <md-card-title-text>
           <span class="md-headline">List of Rule Definitions</span>
           <span class="md-subhead">[Rule Name, User Name, Field Name, Data Filter, Description]</span>
         </md-card-title-text>
       </md-card-title>

       <md-card-content>
         <md-list flex="">

              <md-list-item class="md-3-line" ng-repeat="rule in controller.getAllRules()" ng-click="null">
                <div class="md-list-item-text" layout="row">
                  <p class="tableDataField" flex="noshrink">{{ rule.ruleName }}</p>
                  <p class="tableDataField" flex="noshrink">{{ rule.userName }}</p>
                  <p class="tableDataField" flex="noshrink">{{ rule.fieldName }}</p>
                  <p class="tableDataField" flex="noshrink">{{ rule.dataFilter}}</p>
                  <p class="tableDataField" flex="40">{{ rule.description}}</p>
                </div>
                <md-button class="md-icon-button" ng-click="controller.editRule(rule.id)"><i class="material-icons grey">edit</i></md-button>
                <md-button class="md-icon-button" ng-click="controller.removeRule(rule.id)"><i class="material-icons grey">delete_forever</i></md-button>
                <md-divider ng-if="!$last"></md-divider>
              </md-list-item>
         </md-list>
       </md-card-content>
     </md-card>



    <!-- Detail Rule Form -->

    <md-card md-theme="{{ showDarkTheme ? 'dark-grey' : 'default' }}" md-theme-watch>
       <form name="ruleForm">
           <md-card-title>
             <md-card-title-text>
               <span class="md-headline">Data Rule Definition</span>
             </md-card-title-text>
           </md-card-title>

           <md-card-content>
               <input type="hidden" ng-model="controller.rule.id" />
               <div layout-gt-sm="row">
                   <md-input-container class="md-block" flex-gt-sm>
                     <label>Rule Name</label>
                     <input ng-model="controller.rule.ruleName">
                   </md-input-container>

                   <md-input-container class="md-block" flex-gt-sm>
                        <label>Description</label>
                        <input ng-model="controller.rule.description">
                    </md-input-container>
               </div>

               <div layout-gt-sm="row">
                 <md-input-container class="md-block" flex-gt-sm>
                     <label>User Name</label>
                     <input ng-model="controller.rule.userName">
                 </md-input-container>

                 <md-input-container class="md-block" flex-gt-sm>
                   <label>Field Name</label>
                   <md-select ng-model="controller.rule.fieldName">
                     <md-option ng-repeat="fieldNameObject in fieldNames" value="{{fieldNameObject.fieldName}}">
                      {{fieldNameObject.fieldName}}
                     </md-option>
                   </md-select>
                 </md-input-container>
               </div>

              <div layout-gt-sm="row">
                  <md-input-container class="md-block" flex-gt-sm>
                    <label>Data Filter</label>
                    <md-select ng-model="controller.rule.dataFilter">
                      <md-option ng-repeat="dataFilterObject in dataFilterNames" value="{{dataFilterObject.dataFilterName}}">
                       {{dataFilterObject.dataFilterName}}
                      </md-option>
                    </md-select>
                  </md-input-container>
              </div>


           </md-card-content>

           <md-card-actions layout="row" layout-align="end center">
             <md-button class="md-raised md-primary" ng-click="controller.submit()" ng-disabled="ruleForm.$invalid || ruleForm.$pristine">Save</md-button>
             <md-button class="md-raised" ng-click="controller.reset()" ng-disabled="!activeReset && (ruleForm.$invalid || ruleForm.$pristine)">Reset</md-button>
           </md-card-actions>
           </form>
         </md-card>
<br/>
<br/>
  </md-content>

</div>

<!--
Copyright 2016 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that can be foundin the LICENSE file at http://material.angularjs.org/HEAD/license.
-->
<script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.js'></script>
<script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-animate.min.js'></script>
<script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-route.min.js'></script>
<script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-aria.min.js'></script>
<script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-messages.min.js'></script>
<script src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/t-114/svg-assets-cache.js'></script>
<script src='https://cdn.gitcdn.link/cdn/angular/bower-material/v1.1.5/angular-material.js'></script>

<script src="js/lib/angular-ui-router.min.js" ></script>
<script src="js/lib/localforage.min.js" ></script>
<script src="js/lib/ngStorage.min.js"></script>


<script  src="js/app/mdApp.js"></script>
<script  src="js/app/mdRuleService.js"></script>
<script  src="js/app/mdRuleController.js"></script>


</body>
</html>
