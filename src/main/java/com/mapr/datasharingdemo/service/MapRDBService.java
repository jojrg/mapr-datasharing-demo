package com.mapr.datasharingdemo.service;

import com.mapr.datasharingdemo.model.DataAccessRule;
import com.mapr.datasharingdemo.model.DataBean;
import com.mapr.datasharingdemo.model.TableConfiguration;
import com.mapr.db.MapRDB;
import com.mapr.db.Table;
import org.ojai.Document;
import org.ojai.DocumentStream;
import org.ojai.store.DocumentMutation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class MapRDBService {

    private static final Logger log = LoggerFactory.getLogger(MapRDBService.class);


    @Value("${data.table.name}")
    private String dataTableName;

    @Value("${config.table.name}")
    private String configTableName;

    private AtomicLong idCounter = null;


    @PostConstruct
    public void init() {

        // init current id with highest id of thr stored objects
        DocumentStream docStream = null;
        Table table = this.getOrCreateTable(dataTableName);
        Long id = new Long(0);

        try {
            docStream = table.find();
            Iterator<Document> itrs = docStream.iterator();
            Document dbDocument;
            while (itrs.hasNext()) {
                dbDocument = itrs.next();
                Long currentId = new Long(dbDocument.getId().getString());
                if (currentId > id) {
                    id = currentId;
                }
            }
            idCounter = new AtomicLong(id);
            log.info("current value of idCounter: " + idCounter);
        } catch (Throwable ex) {
            throw new DataAccessException(ex);
        } finally {
            docStream.close();
        }

    }


    public Table getOrCreateTable(String tableName) {

        Table table;

        if (!MapRDB.tableExists(tableName)) {
            table = MapRDB.createTable(tableName); // Create the table if not already present
        } else {
            table = MapRDB.getTable(tableName); // get the table
        }
        return table;
    }


    public DataBean insert(DataBean dataBean) throws DataAccessException {

        // increment idCounter and set it as id in the dataBean
        dataBean.setId(idCounter.incrementAndGet());
        Document document = MapRDB.newDocument(dataBean);

        // set "_id" as string value of id property in the document
        document.set("_id", dataBean.getId().toString());

        Table table = this.getOrCreateTable(dataTableName);

        // save document into the table
        table.insertOrReplace(document);
        table.flush();
        return dataBean;
    }


    public List<DataAccessRule> selectAllDocuments() throws DataAccessException {

        DocumentStream docStream = null;
        try {
            Table table = this.getOrCreateTable(dataTableName);
            List<DataAccessRule> dataBeanList = new ArrayList<>();
            docStream = table.find();
            Iterator<Document> itrs = docStream.iterator();
            Document dbDocument;
            while (itrs.hasNext()) {
                dbDocument = itrs.next();
                DataAccessRule ruleBean = DataAccessRule.generateFromDBDocument(dbDocument);
                dataBeanList.add(ruleBean);
            }
            return dataBeanList;
        } catch (Throwable ex) {
            throw new DataAccessException(ex);
        } finally {
            if (docStream != null) { docStream.close();}
        }
    }


    public TableConfiguration readConfigTable() throws DataAccessException {

        DocumentStream docStream = null;
        TableConfiguration tableConfigBean = null;
        try {
            Table table = this.getOrCreateTable(configTableName);
            docStream = table.find();
            Iterator<Document> iter = docStream.iterator();
            Document dbDocument;
            if (iter.hasNext()) {
                dbDocument = iter.next();
                tableConfigBean = TableConfiguration.generateFromDBDocument(dbDocument);
            }
            return tableConfigBean;
        } catch (Throwable ex) {
            throw new DataAccessException(ex);
        } finally {
            if (docStream != null) { docStream.close();}
        }
    }

    public DataAccessRule selectById(Long id) throws DataAccessException {

        Table table = null;

        if (id == null) {
            return null;
        }

        try {
            table = this.getOrCreateTable(dataTableName);
            Document document = table.findById(id.toString());
            if (document == null) { return null;}
            return DataAccessRule.generateFromDBDocument(document);
        }
        catch (Throwable ex) {
            throw new DataAccessException(ex);
        }
        finally {
           if (table != null) { table.close();}
        }
    }



    public void deleteById(Long id) throws DataAccessException {

        Table table = null;

        if (id == null) {
            throw new DataAccessException("DeleteBy ID, id is null");
        }

        try {
            table = this.getOrCreateTable(dataTableName);
            table.delete(id.toString());
        }
        catch (Throwable ex) {
            throw new DataAccessException(ex);
        }
        finally {
            if (table != null) { table.close();}
        }
    }



    public void update(DataAccessRule ruleBean) throws DataAccessException {


        Table table = null;

        if (ruleBean == null) {
            throw new DataAccessException("Update:: ID doesn't exist");
        }

        try {
            table = this.getOrCreateTable(dataTableName);
            DocumentMutation mutation = MapRDB.newMutation()
                    .set("ruleName", ruleBean.getRuleName())
                    .set("userName", ruleBean.getUserName())
                    .set("fieldName", ruleBean.getFieldName())
                    .set("dataFilter", ruleBean.getDataFilter())
                    .set("description", ruleBean.getDescription())
                    .set("tablePath", ruleBean.getTablePath())
                    .set("validFrom", ruleBean.getValidFrom())
                    .set("validTo", ruleBean.getValidTo())
                    ;
            table.update(ruleBean.getId().toString(), mutation);
            table.flush();
        }
        catch (Throwable ex) {
            throw new DataAccessException(ex);
        }
        finally {
            if (table != null) { table.close();}
        }

    }



}
