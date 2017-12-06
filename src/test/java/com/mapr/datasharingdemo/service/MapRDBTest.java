package com.mapr.datasharingdemo.service;

import com.mapr.datasharingdemo.model.DataAccessRule;
import com.mapr.datasharingdemo.model.DataBean;
import com.mapr.datasharingdemo.model.TableConfiguration;
import com.mapr.datasharingdemo.model.User;
import com.mapr.db.MapRDB;
import org.junit.Assert;
import org.ojai.Document;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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


    @Test
    public void dummyTest() throws Exception {
        int i = 1;
        assertTrue("Test that i has value of 1",i == 1);
    }




    public void testCreateRuleDB() throws Exception {

        DataAccessRule rule = new DataAccessRule(new Long(4563),"Rule1","leon","Field1","Masked","Field1 is masked for Leon ");
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
        DataAccessRule rule = new DataAccessRule(null,"Rule1","leon","Field1","Masked","Field1 is masked for Leon ");
        DataBean dataBean = dbService.insert(rule);
        Long id = dataBean.getId();
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
