package com.example.codesharing.auxiliary;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CodeDTO {
    private static int i = 0;

    public static int inc() {
        i++;
        return i;
    }

    public static int getInc() {
        return i;
    }


    public static void setCode(String code) {
        CodeDTO.code = code;
    }

    public static String getCode() {
        return code;
    }

    private static String code = "public static String getCode() {\n" +
                                 "        return code;\n" +
                                 "    }";
}

