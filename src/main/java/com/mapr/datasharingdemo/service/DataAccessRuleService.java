package com.mapr.datasharingdemo.service;


import com.mapr.datasharingdemo.model.DataAccessRule;


import java.util.List;

public interface DataAccessRuleService {

    DataAccessRule findById(long id);

    void createRule(DataAccessRule das) throws Exception;

    void updateRule(DataAccessRule das);

    void deleteRule(long id);

    List<DataAccessRule> findAllRules();

    void deleteAllRules();

    public boolean isRuleExisting(DataAccessRule das);

}
