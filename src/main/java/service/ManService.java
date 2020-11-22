package service;

import model.Man;

import java.util.List;

public interface ManService {
    List post(Man man);

    List<Man> get();

    List<Man> getFirstName(String firstName);

    void deleteFullName (String firstName, String lastName, String middleName);
}
