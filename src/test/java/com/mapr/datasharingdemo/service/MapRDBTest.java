package com.mapr.datasharingdemo.service;

import com.mapr.datasharingdemo.model.DataAccessRule;
import com.mapr.datasharingdemo.model.DataBean;
import com.mapr.datasharingdemo.model.TableConfiguration;
import com.mapr.datasharingdemo.model.TestDataBean;
import com.mapr.db.MapRDB;
import com.mapr.db.Table;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ojai.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapRDBTest {

    private static final Logger log = LoggerFactory.getLogger(MapRDBTest.class);
    @Autowired
    private MapRDBService dbService;

    @Value("${data.table.name}")
    private String dataTableName;



    @Test
    public void dummyTest() throws Exception {
        int i = 1;
        assertTrue("Test that i has value of 1",i == 1);
    }

    @Test
    public void testInsertDocument() throws Exception {

        TestDataBean testbean = new TestDataBean("210","peter");
        Document document = MapRDB.newDocument(testbean);

        // set "_id" as string value of id property in the document
        document.set("_id", testbean.getId());

        Table table = dbService.getOrCreateTable(dataTableName);

        // save document into the table
        table.insertOrReplace(document);
        table.flush();


    }


    public void testCreateRuleDB() throws Exception {

        DataAccessRule rule = new DataAccessRule(UUID.randomUUID().toString(),"Rule1","leon","Field1","Masked","Field1 is masked for Leon ","/foo/bar.csv","2017-12-24","2017-12-25");
        assertNotNull("Rule not null",rule);
        dbService.insert(rule);

    }

    //@Test
    public void testSelectAllDocuments() throws Exception {

        List<DataAccessRule> dataRules = dbService.selectAllDocuments();
        TestCase.assertNotNull("dataRules not null",dataRules);
        for (DataAccessRule rule:dataRules) {
            log.info(" -- -- -- -- new Bean");
            log.info("id: " + rule.getId());
            log.info("ruleName: " + rule.getRuleName());
            log.info("userName: " + rule.getUserName());
            log.info("dataFilter: " + rule.getDataFilter());
            log.info("description: " + rule.getDescription());

        }

    }

    //@Test
    public void testDeleteById() throws Exception {
        DataAccessRule rule = new DataAccessRule(null,"Rule1","leon","Field1","Masked","Field1 is masked for Leon","/foo/bar.csv","2017-12-24","2017-12-25");
        DataBean dataBean = dbService.insert(rule);
        String id = dataBean.getId();
        log.info("testDeleteById::created new document with Id:" + dataBean.getId());
        DataBean dataBean2 = dbService.selectById(id);
        assertNotNull("create document must exist",dataBean2);
        log.info("testDeleteById::delete document with Id:" + dataBean.getId());
        dbService.deleteById(id);
        log.info("testDeleteById::trying to select deleted document with Id:" + dataBean.getId());
        DataBean dataBean3 = dbService.selectById(id);
        assertNull("trying to select a deleted dataBean should return null",dataBean3);

    }



    @Test
    public void testReadConfigTable() throws Exception {

        TableConfiguration tableConfig = dbService.readConfigTable();
        assertNotNull("tableConfog is not null",tableConfig);


    }



}
