package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidator {
    public static List<String> validate(User u, Boolean codeDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String code_error = validateCode(u.getCode(), codeDuplicateCheckFlag);
        if(!code_error.equals("")) {
            errors.add(code_error);
        }

        String name_error = validateName(u.getDog_name());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String password_error = validatePassword(u.getPassword(), passwordCheckFlag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        String dog_type_error = validateType(u.getDog_type());
        if(!dog_type_error.equals("")) {
            errors.add(dog_type_error);
        }

        String age_error = validateAge(u.getAge());
        if(!age_error.equals("")) {
            errors.add(age_error);
        }

        return errors;

    }

    private static String validateCode(String code, Boolean codeDuplicateCheckFlag) {
        // 必須入力チェック
        if(code == null || code.equals("")) {
            return "ログインIDを入力して下さい。";
        }

        // すでに登録されている社員番号との重複チェック
        if(codeDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long employees_count = (long)em.createNamedQuery("checkRegisteredCode", Long.class).setParameter("code", code).getSingleResult();
            em.close();
            if(employees_count > 0) {
                return "入力されたログインIDはすでに存在しています。";
            }
        }

        return "";
    }

    private static String validateName(String dog_name) {
        if(dog_name == null || dog_name.equals("")) {
            return "登録するワンちゃんの名前を入力してください。";
        }

        return "";
    }

    private static String validateType(String dog_type) {
        if(dog_type == null || dog_type.equals("")) {
            return "犬種を入力してください。";
        }

        return "";
    }

    private static String validatePassword(String password, Boolean passwordCheckFlag) {
        if(passwordCheckFlag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }

    private static String validateAge(String age) {
        if(age == null || age.equals("")) {
            return "ワンちゃん年齢を入力してください。";
        }

        return "";
    }


}
