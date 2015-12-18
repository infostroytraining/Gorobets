package com.dao.memory;

import java.util.List;
import java.util.Map;

import com.entity.LogEvent;
import com.dao.LogEventDAO;
import com.dao.storage.LogEventStorage;

public class MemoryLogEventDAO implements LogEventDAO {

    private LogEventStorage storage;

    public MemoryLogEventDAO(LogEventStorage storage) {
        this.storage = storage;
    }

    @Override
    public LogEvent create(LogEvent logEvent) {
        return storage.add(logEvent);
    }

    @Override
    public LogEvent get(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove(int id) {
        // TODO Auto-generated method stub

    }

    @Override
    public LogEvent getLastLogEvent() {
        List<LogEvent> list = (List<LogEvent>) storage.all().values();
        LogEvent logEvent = list.get(list.size() - 1);
        return logEvent;
    }

    @Override
    public Map<Integer,LogEvent> getAll()
    {
        return storage.all();
    }





}
