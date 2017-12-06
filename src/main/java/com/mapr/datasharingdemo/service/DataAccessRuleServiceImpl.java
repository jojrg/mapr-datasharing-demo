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

    private static List<DataAccessRule> rules;

    @Autowired
    private MapRDBService dbService;


    static{
        rules = populateDummyRules();
    }


    @Override
    public List<DataAccessRule> findAllRules() {

        //return rules;
        return dbService.selectAllDocuments();

    }

    @Override
    public DataAccessRule findById(long id) {

        return dbService.selectById(id);

    }


    public DataAccessRule findByName(String name) {
        for(DataAccessRule rule : rules){
            if(rule.getRuleName().equalsIgnoreCase(name)){
                return rule;
            }
        }
        return null;
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

    @Override
    public boolean isRuleExisting(DataAccessRule rule) {
        return findByName(rule.getRuleName())!=null;
    }

    @Override
    public void deleteAllRules(){
        rules.clear();
    }

    private static List<DataAccessRule> populateDummyRules(){
        List<DataAccessRule> rules = new ArrayList<DataAccessRule>();
        rules.add(new DataAccessRule(counter.incrementAndGet(),"Rule1","leon","Field1","Masked","Field1 is masked for Leon "));
        rules.add(new DataAccessRule(counter.incrementAndGet(),"Rule2","lawrence","Field1","Hidden","Field1 is hidden for Lawrence"));
        rules.add(new DataAccessRule(counter.incrementAndGet(),"Rule3","jochen","Field3","MaskCreditCardUDF","Field3 has is be Masked for Jochen"));
        return rules;
    }

}
