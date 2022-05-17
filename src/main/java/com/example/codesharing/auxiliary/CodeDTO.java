package com.example.codesharing.auxiliary;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CodeDTO {


    public static void setCode(String code) {
        CodeDTO.code = code;
    }

    public static String getCode() {
        return code;
    }

    private static String code =
            " \n" +
            "import lombok.Data;\n" +
            "import lombok.NoArgsConstructor;\n" +
            "import org.jetbrains.annotations.NotNull;\n" +
            "\n" +
            "@Data\n" +
            "@NoArgsConstructor\n" +
            "public class CodeDTO {\n" +
            "    public static void setCode(@NotNull String code) {\n" +
            "        CodeDTO.code = code;\n" +
            "    }\n" +
            "\n" +
            "    public static @NotNull String getCode() {\n" +
            "        return code;\n" +
            "    }\n" +
            "\n" +
            "    @NotNull\n" +
            "    private static String code = \"Class hi() {}\";\n" +
            "}\n" +
            "\n";
}

