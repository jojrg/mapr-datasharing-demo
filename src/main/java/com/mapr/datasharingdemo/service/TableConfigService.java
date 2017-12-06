package com.mapr.datasharingdemo.service;

import com.mapr.datasharingdemo.model.DataAccessRule;
import com.mapr.datasharingdemo.model.TableConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


//TODO  interface relation might not be necessary

@Service("tableConfigService")
public class TableConfigService implements ConfigService {


    @Autowired
    private MapRDBService dbService;

    private List<String> fieldNames;

    private List<String> filterNames;

    @PostConstruct
    public void init() {
        TableConfiguration tableConfig = dbService.readConfigTable();
        fieldNames = tableConfig.getFieldNames();
        filterNames = tableConfig.getDataFilters();

    }

    public List<String> getFieldNames() {
        return fieldNames;
    }


    public List<String> getDataFilterNames() {
        return filterNames;
    }

}
