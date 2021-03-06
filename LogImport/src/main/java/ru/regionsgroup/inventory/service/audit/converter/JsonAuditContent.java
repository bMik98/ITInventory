package ru.regionsgroup.inventory.service.audit.converter;

import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import ru.regionsgroup.inventory.service.audit.AuditContent;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
@SuppressWarnings("unchecked")
public class JsonAuditContent<T> implements AuditContent<T> {
    @Override
    public T decode(final String content) {
        Class<T> entityType = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return Json.decodeValue(content, entityType);
    }

    public List<T> decodeArray(final String content) {
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

    @Override
    public List<T> decodeArrays(final List<String> contents) {
        List<T> result = new ArrayList<>();
        for (String content : contents) {
            result.addAll(decodeArray(content));
        }
        return result;
    }
}
