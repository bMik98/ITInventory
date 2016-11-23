package ru.regionsgroup.inventory.service.audit.converter;

import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import ru.regionsgroup.inventory.service.audit.AuditConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
public abstract class JsonAuditConverter<T> implements AuditConverter<T> {
    private Class<T> entityClass;

    public JsonAuditConverter(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<T> convert(final List<String> content) {
        List<T> result = new ArrayList<>();
        for (String entry : content) {
            result.addAll(decodeArray(entry));
        }
        return result;
    }

    private T decode(final String content) {
        return Json.decodeValue(content, entityClass);
    }

    private List<T> decodeArray(final String content) {
        List<T> result = new ArrayList<>();
        try {
            JsonArray jsonArray = new JsonArray(content);
            for (int i = 0; i < jsonArray.size(); i++) {
                T entity = decode(jsonArray.getValue(i).toString());
                if (entity != null) {
                    result.add(entity);
                }
            }
        } catch (DecodeException e) {
            e.printStackTrace();
        }
        return result;
    }
}
