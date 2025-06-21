package org.cacanhdaden.quanlythuoc.util;

import org.cacanhdaden.quanlythuoc.model.model.Users;

public class GenderPassingUtil {
    public static String UserEnumToString(Users.GenderEnum inp) {
        String result = null;
        if (inp == Users.GenderEnum.MALE) {
            result = new String("Nam");
        } else if (inp == Users.GenderEnum.FEMALE) {
            result = new String("Nữ");
        }
        return result;
    }

    public static Users.GenderEnum StringToUserEnum(String inp) {
        Users.GenderEnum result = null;
        if (inp.equals("Nam")) {
            result = Users.GenderEnum.MALE;
        } else if (inp.equals("Nữ")) {
            result = Users.GenderEnum.FEMALE;
        }
        return result;
    }

    public static String UserEnumToLoadData(Users.GenderEnum inp) {
        String result = null;
        if (inp == Users.GenderEnum.MALE) {
            result = new String("male");
        } else if (inp == Users.GenderEnum.FEMALE) {
            result = new String("female");
        }
        return result;
    }

    public static Users.GenderEnum LoadDataToUserEnum(String inp) {
        Users.GenderEnum result = null;
        if (inp.equals("male")) {
            result = Users.GenderEnum.MALE;
        } else if (inp.equals("female")) {
            result = Users.GenderEnum.FEMALE;
        }
        return result;
    }
}
