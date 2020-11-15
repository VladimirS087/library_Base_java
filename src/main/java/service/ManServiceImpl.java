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
    private static final List<Man> MAN_REPOSITORY_LIST = new ArrayList<>();

    @Override
    public List <Man> post(Man man) {
        MAN_REPOSITORY_LIST.add(man);
        return MAN_REPOSITORY_LIST;
    }

    @Override
    public List<Man> get() {
        return MAN_REPOSITORY_LIST;
    }

    @Override
    public List <Man> getFirstName (String firstName) {
        List <Man> mansThisFirstName = new ArrayList<>();
        for (Man man: MAN_REPOSITORY_LIST) {
            if (firstName.equals(man.getFirstName())) {
                mansThisFirstName.add(man);
            }
        }
        return mansThisFirstName;
    }

    @Override
    public void deleteFullName (String firstName, String lastName, String middleName) {
        for (Man man: MAN_REPOSITORY_LIST) {
            if ((firstName.equals(man.getFirstName())) &&
                    (lastName.equals(man.getLastName())) &&
                    (middleName.equals(man.getMiddleName()))) {
                MAN_REPOSITORY_LIST.remove(MAN_REPOSITORY_LIST.indexOf(man));
            }
        }
    }
}
