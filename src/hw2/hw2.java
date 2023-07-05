package hw2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class hw2 {



    /**
     * Формат сдачи: ссылка на подписанный git-проект.
     *
     * Задание
     *
     * 1) Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса, используя StringBuilder. Данные для фильтрации приведены ниже в виде json-строки.
     * Если значение null, то параметр не должен попадать в запрос.
     * Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
     *
     * 2) Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.
     *
     * Дополнительные задания
     *
     * 3) Дана json-строка (можно сохранить в файл и читать из файла)
     * [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
     * Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].
     * Пример вывода:
     * Студент Иванов получил 5 по предмету Математика.
     * Студент Петрова получил 4 по предмету Информатика.
     * Студент Краснов получил 5 по предмету Физика.
     *
     * 4) К калькулятору из предыдущего ДЗ добавить логирование.
     */

    public static void main(String[] args) throws IOException {
        System.out.println(task1("{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}"));
        int [] array = task2(new int[]{10, 13, 141, 165, 44});
        ///Нахожусь в командировке решать остальное некогда, но обязательно решу
    }

    public static String task1(String json){
        System.out.println("1) Дана строка sql-запроса \"select * from students where \". Сформируйте часть WHERE этого запроса, используя StringBuilder. Данные для фильтрации приведены ниже в виде json-строки.\n" +
                " * Если значение null, то параметр не должен попадать в запрос.\n" +
                " * Параметры для фильтрации: {\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}");
        StringBuilder query = new StringBuilder("select * from students where");
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject)jsonParser.parse(json);
            Object[] key = jsonObject.keySet().toArray();
            if(key.length<1){
                return null;
            };
            for (int i = 0; i < key.length; i++) {
                System.out.println(key[i]);
                if(jsonObject.get(key[i])!=null && !"null".equalsIgnoreCase(jsonObject.get(key[i]).toString())) {
                    System.out.println(jsonObject.get(key[i]));
                    if (i != 0) {
                        query.append(" and ");
                    }
                    query.append(" ")
                            .append(key[i].toString())
                            .append(" = '")
                            .append(jsonObject.get(key[i]).toString())
                            .append("'");
                }
            }
        } catch (ParseException e) {
            System.out.println("Don't correct json");
            return null;
        }
        return query.toString();
    }

    public static int [] task2(int [] array) throws IOException {
        File logFile = new File("log.txt");
        System.out.println("2) Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.");
        boolean sort = false;
        int buf;
        try (FileWriter fileWriter = new FileWriter(logFile, true)) {
            while (!sort) {
                sort = true;
                for (int i = 0; i < array.length - 1; i++) {
                    if (array[i] > array[i + 1]) {
                        sort = false;
                        buf = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = buf;
                    }
                }
                StringBuilder outArray = new StringBuilder("[");
                for (int j = 0; j < array.length; j++) {
                    outArray.append(array[j]).append(", ");
                }
                outArray.setLength(outArray.length()-2);
                outArray.append("]");
                System.out.println(outArray);
                fileWriter.write(outArray.toString() + "\r\n");
                fileWriter.flush();
            }
        }
        return array;
    }
}
