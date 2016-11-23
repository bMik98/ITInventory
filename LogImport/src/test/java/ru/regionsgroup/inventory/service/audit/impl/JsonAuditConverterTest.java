package ru.regionsgroup.inventory.service.audit.impl;

import org.junit.Before;
import org.junit.Test;
import ru.regionsgroup.inventory.service.audit.AuditConverter;
import ru.regionsgroup.inventory.service.audit.converter.JsonAuditConverter;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Mbedritskiy
 */
public class JsonAuditConverterTest {
    private AuditConverter converter;
    private String record1 = "[ {\"name\":\"Vasya\",\"sex\":\"true\"} ]";
    private String record2 = "[ {\"name\":\"Sveta\",\"sex\":\"false\"} ]";

    public class TestEntityConverter extends JsonAuditConverter<TestEntity> {
        public TestEntityConverter() {
            super(TestEntity.class);
        }
    }

    @Before
    public void SetUp() {
        converter = new TestEntityConverter();
    }

    @Test
    public void convertOneEntity() throws Exception {
        List<String> content = Arrays.asList(record1);
        System.out.println(content);
        List entities = converter.convert(content);
        assertEquals(content.size(), entities.size());
    }

    @Test
    public void convertTwoEntity() throws Exception {
        List<String> content = Arrays.asList(record1, record2);
        System.out.println(content);
        List<TestEntity> entities = converter.convert(content);
        assertEquals(content.size(), entities.size());
        assertEquals("Vasya", entities.get(0).getName());
        assertTrue(entities.get(0).isSex());
        assertEquals("Sveta", entities.get(1).getName());
        assertFalse(entities.get(1).isSex());
    }
}