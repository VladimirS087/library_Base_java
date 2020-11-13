package service;

import model.Man;

import java.util.List;

public interface ManService {
    String post(Man man);

    List<Man> get();

    Man read(int id);

    Man readFirstName(String firstName);

    boolean deleteFirstName(String firstName);

    boolean deleteLastName(String LastName);

    boolean deleteMiddleName(String middleName);
}
