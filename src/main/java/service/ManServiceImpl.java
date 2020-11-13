package service;

import model.Book;
import model.Man;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ManServiceImpl implements  ManService{
    private static final Map<Integer, Man> MAN_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicInteger MAN_ID_HOLDER = new AtomicInteger();

    @Override
    public String post(Man man) {
        final int manId = MAN_ID_HOLDER.incrementAndGet();
        man.setId(manId);
        MAN_REPOSITORY_MAP.put(manId, man);
        return MAN_REPOSITORY_MAP.toString();
    }

    @Override
    public List<Man> get() {
        return new ArrayList<>(MAN_REPOSITORY_MAP.values());
    }

    @Override
    public Man read(int id) {
        return MAN_REPOSITORY_MAP.get(id);
    }

    @Override
    public Man readFirstName(String firstName) {
        return MAN_REPOSITORY_MAP.get(firstName);
    }

    @Override
    public boolean deleteFirstName(String firstName) {
        return MAN_REPOSITORY_MAP.remove(firstName) != null;
    }

    @Override
    public boolean deleteLastName(String lastName) {
        return MAN_REPOSITORY_MAP.remove(lastName) != null;
    }

    @Override
    public boolean deleteMiddleName(String middleName) {
        return MAN_REPOSITORY_MAP.remove(middleName) != null;
    }
}
