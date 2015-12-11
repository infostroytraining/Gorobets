package Task1.TextUtils;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
public class TextUtils {
    /**
     * Проверка орфографии
     * <p>
     * Некоторые люди не обращают внимание на орфографию. Например, не пишут новое
     * предложение с заглавной буквы. Или не ставят пробел после знаков препинания.
     * <p>
     * Ваша задача: исправить их ошибки.
     * <p>
     * Что нужно сделать:
     * <p>
     * 1. Каждое новое предложение должно начинаться с заглавной буквы.
     * 2. После знаков препинания (точка и запятая) должны быть пробелы.
     */


    public String correctText(String text) {


        StringBuilder sb = new StringBuilder(text);
        correctSymbols(sb);
        charactersToUpperCase(sb);


        return sb.toString();
    }

    public void charactersToUpperCase(StringBuilder sb) {

        if (!Character.isUpperCase(sb.charAt(0))) {
            sb.replace(0, 1, String.valueOf(sb.charAt(0)).toUpperCase());
        }
        for (int i = 0; i < sb.length() - 2; i++) {

            if (sb.charAt(i) == '.' && !Character.isUpperCase(sb.charAt(i + 2))) {

                sb.replace(i + 2, i + 3, String.valueOf(sb.charAt(i + 2)).toUpperCase());

            }
        }
    }

    public void correctSymbols(StringBuilder sb) {

        for (int i = 0; i < sb.length(); i++) {

            if ((sb.charAt(i) == '.' || sb.charAt(i) == ',') && (i < sb.length() - 1 && sb.charAt(i + 1) != ' ')) {
                sb.insert(i + 1, ' ');
            }
            if (i == sb.length() - 1 && sb.charAt(i) != '.' ) {
                sb.append('.');
            }
        }
    }
}

