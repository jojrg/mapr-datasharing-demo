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


    public DataAccessRule(){
        id= new Long(0);
    }


    public DataAccessRule(Long id, String ruleName, String userName, String fieldName, String dataFilter, String description) {
        this.id = id;
        this.ruleName = ruleName;
        this.userName = userName;
        this.fieldName = fieldName;
        this.dataFilter = dataFilter;
        this.description = description;
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


    public static DataAccessRule generateFromDBDocument(Document ruleDoc) {
        Long id = new Long(ruleDoc.getId().getString());
        return new DataAccessRule(
                id,
                ruleDoc.getString("ruleName"),
                ruleDoc.getString("userName"),
                ruleDoc.getString("fieldName"),
                ruleDoc.getString("dataFilter"),
                ruleDoc.getString("description")
        );


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataAccessRule that = (DataAccessRule) o;

        if (!id.equals(that.id)) return false;
        if (!userName.equals(that.userName)) return false;
        if (!fieldName.equals(that.fieldName)) return false;
        if (!dataFilter.equals(that.dataFilter)) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + fieldName.hashCode();
        result = 31 * result + dataFilter.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
