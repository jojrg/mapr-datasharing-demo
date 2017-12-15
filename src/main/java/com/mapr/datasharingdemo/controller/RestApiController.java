package com.mapr.datasharingdemo.controller;

import com.mapr.datasharingdemo.model.DataAccessRule;
import com.mapr.datasharingdemo.service.ConfigService;
import com.mapr.datasharingdemo.service.DataAccessRuleService;
import com.mapr.datasharingdemo.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	DataAccessRuleService ruleService; //Service which will do all data retrieval/manipulation work

	@Autowired
	ConfigService configService;

	// -------------------Retrieve All Rules---------------------------------------------

	@RequestMapping(value = "/rule/", method = RequestMethod.GET)
	public ResponseEntity<List<DataAccessRule>> listAllRules() {
		logger.info("RuleController::listAllRules");
		List<DataAccessRule> rules = ruleService.findAllRules();
		if (rules.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<DataAccessRule>>(rules, HttpStatus.OK);
	}

	// -------------------Retrieve Single Rule------------------------------------------

	@RequestMapping(value = "/rule/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getRule(@PathVariable("id") long id) {
		logger.info("Fetching Rule with id {}", id);
		DataAccessRule rule = ruleService.findById(id);
		if (rule == null) {
			logger.error("Rule with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Rule with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DataAccessRule>(rule, HttpStatus.OK);
	}

	// -------------------Create a Rule-------------------------------------------

	@RequestMapping(value = "/rule/", method = RequestMethod.POST)
	public ResponseEntity<?> createRule(@RequestBody DataAccessRule rule, UriComponentsBuilder ucBuilder) throws Exception {
		logger.info("Creating Rule : {}", rule);

		//TODO check if rule with this name already exists
		/*
		if (ruleService.isRuleExisting(rule)) {
			logger.error("Unable to create. A Rule with name {} already exist", rule.getRuleName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Rule with name " +
			rule.getRuleName() + " already exist."), HttpStatus.CONFLICT);
		}
		*/

		ruleService.createRule(rule);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/rule/{id}").buildAndExpand(rule.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Rule ------------------------------------------------

	@RequestMapping(value = "/rule/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRule(@PathVariable("id") long id, @RequestBody DataAccessRule rule) {
		logger.info("Updating Rule with id {}", id);

		DataAccessRule currentRule = ruleService.findById(id);

		if (currentRule == null) {
			logger.error("Unable to update. Rule with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Rule with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}


		currentRule.setRuleName(rule.getRuleName());
		currentRule.setFieldName(rule.getFieldName());
		currentRule.setDescription(rule.getDescription());
		currentRule.setDataFilter(rule.getDataFilter());
		currentRule.setUserName(rule.getUserName());
		currentRule.setUserName(rule.getUserName());
		currentRule.setTablePath(rule.getTablePath());
        currentRule.setValidFrom(rule.getValidFrom());
        currentRule.setValidTo(rule.getValidTo());

		ruleService.updateRule(currentRule);
		return new ResponseEntity<DataAccessRule>(currentRule, HttpStatus.OK);
	}

	// ------------------- Delete a Rule-----------------------------------------

	@RequestMapping(value = "/rule/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRule(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Rule with id {}", id);

		DataAccessRule rule = ruleService.findById(id);
		if (rule == null) {
			logger.error("Unable to delete. Rule with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Rule with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		ruleService.deleteRule(id);
		return new ResponseEntity<DataAccessRule>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Rules-----------------------------

	@RequestMapping(value = "/rule/", method = RequestMethod.DELETE)
	public ResponseEntity<DataAccessRule> deleteAllRules() {
		logger.info("Deleting All Rules (not implemented yet)");
        //TODO implement delete all Rules
		//ruleService.deleteAllRules();
		return new ResponseEntity<DataAccessRule>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Retrieve DataFilters --------------------------

	@RequestMapping(value = "/config/datafilternames/", method = RequestMethod.GET)
	public ResponseEntity<List<String>> listDataFilterNames() {
		logger.info("RuleController::listDataFilterNames");
		List<String> filterNames = this.configService.getDataFilterNames();
		if (filterNames.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<String>>(filterNames, HttpStatus.OK);
	}


// ------------------- Retrieve fieldNames --------------------------------

	@RequestMapping(value = "/config/fieldnames/", method = RequestMethod.GET)
	public ResponseEntity<List<String>> listFieldNames() {
		logger.info("RuleController::listFieldNames");
		List<String> fieldNames = this.configService.getFieldNames();
		if (fieldNames.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<String>>(fieldNames, HttpStatus.OK);
	}



}