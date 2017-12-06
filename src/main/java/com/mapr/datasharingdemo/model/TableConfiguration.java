package com.mapr.datasharingdemo.model;

import com.mapr.datasharingdemo.service.MapRDBService;
import org.ojai.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by jojrg on 05.12.17.
 */
public class TableConfiguration {

    private static final Logger log = LoggerFactory.getLogger(TableConfiguration.class);

    private List<String> fieldNames;
    private List<String> dataFilters;


    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public List<String> getDataFilters() {
        return dataFilters;
    }

    public void setDataFilters(List<String> dataFilters) {
        this.dataFilters = dataFilters;
    }


    public static TableConfiguration generateFromDBDocument(Document dbDocument) {

        TableConfiguration tableConfig = new TableConfiguration();
        List<String> fieldName = (List) dbDocument.getValue("fieldNames");
        List<String> dataFilters = (List) dbDocument.getValue("dataFilters");
        tableConfig.setDataFilters(dataFilters);
        tableConfig.setFieldNames(fieldName);
        return tableConfig;

    }
}




