package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Dog;

public class DogValidator {
    public static List<String> validate(Dog d, Boolean codeDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String name_error = validateName(d.getDog_name());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        return errors;
    }

        private static String validateName(String dog_name) {
            if(dog_name == null || dog_name.equals("")) {
                return "登録するワンちゃんの名前を入力してください。";
            }

            return "";
        }

    }

