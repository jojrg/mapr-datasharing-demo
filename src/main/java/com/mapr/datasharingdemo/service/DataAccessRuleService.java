package com.mapr.datasharingdemo.service;


import com.mapr.datasharingdemo.model.DataAccessRule;


import java.util.List;

public interface DataAccessRuleService {

    DataAccessRule findById(String id);

    void createRule(DataAccessRule das) throws Exception;

    void updateRule(DataAccessRule das);

    void deleteRule(String id);

    List<DataAccessRule> findAllRules();


}
