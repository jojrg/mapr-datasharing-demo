<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Data Access Rule</span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="rulectrl.successMessage">{{rulectrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="rulectrl.errorMessage">{{rulectrl.errorMessage}}</div>
	            <form ng-submit="rulectrl.submit()" name="myForm" class="form-horizontal">

	                <input type="hidden" ng-model="rulectrl.rule.id" />

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Rule Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="rulectrl.rule.ruleName" id="rulename" class="username form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>


	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">User Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="rulectrl.rule.userName" id="username" class="username form-control input-sm" placeholder="Enter Username" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Field Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="rulectrl.rule.fieldName" id="fieldname" class="username form-control input-sm" placeholder="Enter Username" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Data Filter</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="rulectrl.rule.dataFilter" id="datafilter" class="username form-control input-sm" placeholder="Select Data Filter" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="uname">Description</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="rulectrl.rule.description" id="description" class="username form-control input-sm" placeholder="Description" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>



	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!rulectrl.user.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="rulectrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Rules</span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>id</th>
		                <th>RuleName</th>
		                <th>UserName</th>
		                <th>FieldName</th>
		                <th>DataFilter</th>
		                <th>Description</th>
		                <th width="70"></th>
		                <th width="70"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="r in rulectrl.getAllRules()">
		                <td>{{r.id}}</td>
		                <td>{{r.ruleName}}</td>
		                <td>{{r.userName}}</td>
		                <td>{{r.fieldName}}</td>
		                <td>{{r.dataFilter}}</td>
		                <td>{{r.description}}</td>

		                <td><button type="button" ng-click="rulectrl.editRule(r.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="rulectrl.removeRule(r.id)" class="btn btn-danger custom-width">Remove</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>