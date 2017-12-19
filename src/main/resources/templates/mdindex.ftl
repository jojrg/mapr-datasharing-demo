<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>MapR Demo</title>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,400italic">

  <link rel='stylesheet prefetch' href='https://cdn.gitcdn.link/cdn/angular/bower-material/v1.1.5/angular-material.css'>
  <link rel='stylesheet prefetch' href='https://material.angularjs.org/1.1.5/docs.css'>
  <link rel="stylesheet" href="css/md-data-table.css">
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

  <md-content layout-padding md-theme="{{ showDarkTheme ? 'dark-grey' : 'default' }}">

     <md-nav-bar
       md-selected-nav-item="currentNavItem"
       nav-bar-aria-label="navigation links">
       <md-nav-item md-nav-click="none" name="pageHome">
         Home
       </md-nav-item>
       <md-nav-item md-nav-click="none" name="pageAbout">
         About
       </md-nav-item>
     </md-nav-bar>


      <!--  List of Rule Data Table -->
      <md-card ng-show="currentNavItem == 'pageHome'" md-theme="{{ showDarkTheme ? 'dark-grey' : 'default' }}" md-theme-watch="">
         <md-card-title>
           <md-card-title-text>
             <span class="md-headline">List of Rule Definitions</span>
           </md-card-title-text>
         </md-card-title>

         <md-card-content>

             <md-table-container>
               <table md-table>
                 <thead md-head>
                   <tr md-row>
                     <th md-column><span>Rule Name</span></th>
                     <th md-column><span>User Name</span></th>
                     <th md-column><span>Field Name</span></th>
                     <th md-column><span>Data Filter</span></th>
                     <th md-column><span>Description</span></th>
                     <th md-column><span>Valid From/To</span></th>
                     <th md-column><span></span></th>
                   </tr>
                 </thead>
                 <tbody md-body>
                   <tr md-row ng-repeat="rule in controller.getAllRules()">
                     <td md-cell>{{rule.ruleName}}</td>
                     <td md-cell>{{rule.userName}}</td>
                     <td md-cell>{{rule.fieldName}}</td>
                     <td md-cell>{{rule.dataFilter}}</td>
                     <td md-cell>{{rule.description}}</td>
                     <td md-cell>
                       <div layout="column">
                        <div>{{rule.validFrom | date:'dd.MM.yyyy'}}</div>
                        <div>{{rule.validTo | date:'dd.MM.yyyy' }}</div>
                     </td>
                     <td md-cell>
                      <div layout="row">
                       <md-button class="md-icon-button" ng-click="controller.editRule(rule.id)"><i class="material-icons grey">edit</i></md-button>
                       <md-button class="md-icon-button" ng-click="controller.removeRule(rule.id)"><i class="material-icons grey">delete_forever</i></md-button>
                     </div>
                     </td>

                   </tr>
                 </tbody>
               </table>
             </md-table-container>
          <br/><br/>
         </md-card-content>

       </md-card>


    <!-- Detail Rule Form -->

    <md-card ng-show="currentNavItem == 'pageHome'" md-theme="{{ showDarkTheme ? 'dark-grey' : 'default' }}" md-theme-watch>
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
                     <input ng-model="controller.rule.ruleName" required name="ruleName">
                     <div ng-messages="ruleForm.ruleName.$error">
                        <div ng-message="required">is required!</div>
                     </div>
                   </md-input-container>

                   <md-input-container class="md-block" flex-gt-sm>
                        <label>Description</label>
                        <input ng-model="controller.rule.description" name="description">
                    </md-input-container>
               </div>

               <div layout-gt-sm="row">
                 <md-input-container class="md-block" flex-gt-sm>
                     <label>User Name</label>
                     <input ng-model="controller.rule.userName">
                 </md-input-container>

                 <md-input-container class="md-block" flex-gt-sm>
                    <label>Data Filter</label>
                    <md-select ng-model="controller.rule.dataFilter">
                      <md-option ng-repeat="dataFilterObject in dataFilterNames" value="{{dataFilterObject.dataFilterName}}">
                       {{dataFilterObject.dataFilterName}}
                      </md-option>
                    </md-select>
                  </md-input-container>
               </div>

                <div layout-gt-sm="row">
                 <md-input-container class="md-block" flex-gt-sm>
                     <label>Table Path</label>
                     <input ng-model="controller.rule.tablePath">
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
                     <label>Valid From</label>
                     <md-datepicker ng-model="controller.rule.validFrom" ng-model-options="{timezone: 'UTC'}" name="validFrom" md-min-date=""
                       md-max-date="controller.rule.validTo"></md-datepicker>

                     <div ng-messages="ruleForm.validFrom.$error">
                       <div ng-message="valid">The entered value is not a date!</div>
                       <div ng-message="required">This date is required!</div>
                       <div ng-message="mindate">Date is too early!</div>
                       <div ng-message="maxdate">Date later than 'Valid To'!</div>
                     </div>
                </md-input-container>

                <md-input-container class="md-block" flex-gt-sm>
                     <label>Valid To</label>
                     <md-datepicker ng-model="controller.rule.validTo" ng-model-options="{timezone: 'UTC'}" name="validTo" md-min-date="controller.rule.validFrom"
                       md-max-date=""></md-datepicker>

                     <div ng-messages="ruleForm.validTo.$error">
                       <div ng-message="valid">The entered value is not a date!</div>
                       <div ng-message="required">This date is required!</div>
                       <div ng-message="mindate">Date must be later than 'Valid From' Date!</div>
                       <div ng-message="maxdate">Date is too late!</div>
                     </div>
                </md-input-container>

              </div>


           </md-card-content>

           <md-card-actions layout="row" layout-align="end center">
             <md-button class="md-raised md-primary" ng-click="controller.submit()" ng-disabled="ruleForm.$invalid || ruleForm.$pristine">Save</md-button>
             <md-button class="md-raised" ng-click="controller.reset()" ng-disabled="!activeReset && ruleForm.$pristine">Reset</md-button>
           </md-card-actions>
           </form>
         </md-card>


      <md-content ng-show="currentNavItem == 'pageAbout'" flex layout-padding>
      <h3>Sensitive Data Sharing Opportunity - Background</h3>
      <p>Organisations with functional entities in different sovereign jurisdictions need to share sensitive customer data.
      This can be both cross regional (e.g. between UK and France) and also inter regional (e.g. between business units).
      Such sharing is subject to legislation (for example GDPR) and internal governance.</p>
      <p>Today, organisations are implementing such data sharing arrangements manually which is both time consuming and error prone.
      This is both costly and exposes the organisation to potential fines due to unauthorised distribution of data.
      This is an issue for any organisation with multiple sub-entities.
      Two tier-1 accounts in the UK that have specifically mentioned this are Vodafone and HSBC.</p>

      <h4>MapR’s technology can be used to address these challenges</h4>
      <p>The solution will require an easy-to-use user interface to orchestrate and monitor the process. This will allow for the automation and monitoring of data sharing, extremely attractive both in terms of reducing the cost/effort involved and ensuring that shared data remains secure.</p>
      <ul>
       <li>Allow the data sharing agreements to be expressed as a set of rules</li>
       <li>Enforce the rules at data access time</li>
       <li>Augment the rules with additional features such as expiration time</li>
       <li>Ensure that access is fully audited</li>
      </ul>

       <br />
       <br />

    </md-content>





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
<script src="js/lib/md-data-table.js"></script>


<script  src="js/app/mdApp.js"></script>
<script  src="js/app/mdRuleService.js"></script>
<script  src="js/app/mdRuleController.js"></script>


</body>
</html>
