package com.mapr.datasharingdemo.service;

import java.util.List;

/**
 * Created by jojrg on 05.12.17.
 * Interface for providing confiuration data used by the front end
 * List of FieldNames
 * List of DataFilterNames
 */
public interface ConfigService {

    public List<String> getFieldNames();

    public List<String> getDataFilterNames();


}
