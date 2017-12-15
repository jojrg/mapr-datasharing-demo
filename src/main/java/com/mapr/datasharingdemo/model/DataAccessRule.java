package com.mapr.datasharingdemo.model;

import org.ojai.Document;

/**
 * Created by jojrg on 30.11.17.
 */
public class DataAccessRule implements DataBean {


    private Long id = null;


    private String ruleName = "";
    private String userName = "";
    private String fieldName = "";
    private String dataFilter = "";
    private String description = "";
    private String tablePath = "";
    private String validFrom = "";
    private String validTo = "";


    public DataAccessRule() {
        id = new Long(0);
    }


    public DataAccessRule(Long id, String ruleName, String userName, String fieldName, String dataFilter,
                          String description, String tablePath, String validFrom, String validTo) {

        //TODO check how this can be generated automatically using bean methods
        this.id = id;
        this.ruleName = ruleName;
        this.userName = userName;
        this.fieldName = fieldName;
        this.dataFilter = dataFilter;
        this.description = description;
        this.tablePath = tablePath;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDataFilter() {
        return dataFilter;
    }

    public void setDataFilter(String dataFilter) {
        this.dataFilter = dataFilter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTablePath() {
        return tablePath;
    }

    public void setTablePath(String tablePath) {
        this.tablePath = tablePath;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataAccessRule rule = (DataAccessRule) o;

        if (!id.equals(rule.id)) return false;
        if (!ruleName.equals(rule.ruleName)) return false;
        if (!fieldName.equals(rule.fieldName)) return false;
        if (!dataFilter.equals(rule.dataFilter)) return false;
        return tablePath.equals(rule.tablePath);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + ruleName.hashCode();
        result = 31 * result + fieldName.hashCode();
        result = 31 * result + dataFilter.hashCode();
        return result;
    }


    public static DataAccessRule generateFromDBDocument(Document ruleDoc) {
        Long id = new Long(ruleDoc.getId().getString());
        return new DataAccessRule(
                id,
                ruleDoc.getString("ruleName"),
                ruleDoc.getString("userName"),
                ruleDoc.getString("fieldName"),
                ruleDoc.getString("dataFilter"),
                ruleDoc.getString("description"),
                ruleDoc.getString("tablePath"),
                ruleDoc.getString("validFrom"),
                ruleDoc.getString("validTo")
        );


    }


}
