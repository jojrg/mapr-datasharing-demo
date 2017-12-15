package com.mapr.datasharingdemo.service;

import com.mapr.datasharingdemo.model.DataAccessRule;
import com.mapr.datasharingdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


//TODO  interface relation might not be necessary

@Service("dataAccessRuleService")
public class DataAccessRuleServiceImpl implements DataAccessRuleService{

    private static final AtomicLong counter = new AtomicLong(10);


    @Autowired
    private MapRDBService dbService;




    @Override
    public List<DataAccessRule> findAllRules() {

        //return rules;
        return dbService.selectAllDocuments();

    }

    @Override
    public DataAccessRule findById(long id) {

        return dbService.selectById(id);

    }




    @Override
    public void createRule(DataAccessRule rule)  {
        dbService.insert(rule);
    }

    @Override
    public void updateRule(DataAccessRule rule) {
        dbService.update(rule);
    }

    @Override
    public void deleteRule(long id) {

        dbService.deleteById(id);

    }




}
