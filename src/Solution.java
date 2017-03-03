import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    private static final String pathname = "C:\\Users\\Sergej\\IdeaProjects\\Tlab1\\src\\res.txt";

    public static void main(String args[]) throws IOException {

/*        List<List<String>> listList = Files.lines(Paths.get(pathname)).
                map(p-> p.split(" ")).map(Arrays::asList).collect(Collectors.toList());
        */
        List<List<Integer>> listList = Files.lines(Paths.get(pathname)).
                map(p-> p.split(" ")).map(Arrays::asList).
                map(a-> a.stream().map(Integer::valueOf).collect(Collectors.toList())).collect(Collectors.toList());


        listList = rotateMatrix(listList);
        List<List<Integer>> firstLevel = createFirstLevel(listList);

        firstLevel.forEach(System.out::println);
        List<List<Integer>> lists = nextLevel(firstLevel);
        lists.forEach(System.out::println);

    }

    private static List<List<Integer>> nextLevel(List<List<Integer>> firstLevel) {
        firstLevel = rotateMatrix(firstLevel);

        List<Integer> index = new LinkedList<>();
        for (int i = 0; i <firstLevel.size(); i++) {
            if (firstLevel.get(i).stream().noneMatch(b-> b.equals(1)))
            {
                index.add(i);
            }
        }

        for (int i = 0; i < index.size(); i++) {
            firstLevel.remove(index.get(i));
        }
        firstLevel = rotateMatrix(firstLevel);

        for (int i = 0; i < index.size(); i++) {
            firstLevel.remove(index.get(i));
        }

        firstLevel = rotateMatrix(firstLevel);
        firstLevel = rotateMatrix(firstLevel);

        return firstLevel;//firstLevel.stream().filter(a-> a.stream().anyMatch(b-> b.equals(1))).collect(Collectors.toList());

    }

    private static List<List<Integer>> createFirstLevel(List<List<Integer>> listList) {

        List<List<Integer>> booleanList = new ArrayList<>();
        for (int i = 0; i < listList.size(); i++) {
            List<Integer> booleans = new ArrayList<>();
            for (int i1 = 0; i1 < listList.size(); i1++) {
                List<Integer> aListList = listList.get(i1);
                Integer answer = 0;
                if (i!=i1) {
                    answer = Compare(listList.get(i), aListList);
                }
                booleans.add(answer);
            }
            booleanList.add(booleans);
        }
        return booleanList;
    }

    private static Integer Compare(List<Integer> list, List<Integer> aListList) {

        System.out.println();
        System.out.println("Сравниваемый список");
        list.forEach(System.out::println);
        System.out.println("Второй список");
        aListList.forEach(System.out::println);
        System.out.println();

        Integer flag = 1;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) < aListList.get(i))
            {
                flag = 0;
            }
        }

        System.out.println("Итог");
        System.out.println(flag);
        return flag;
    }

    private static <T> List<List<T>> rotateMatrix(List<List<T>> matrix){

        List<List<T>> matrix2 = new ArrayList<>();

        for (int j = 0; j < matrix.get(0).size(); j++) {
            List<T> list1 = new ArrayList<>();
            for (int i = 0; i != matrix.size(); i++) {
                list1.add(matrix.get(i).get(j));
            }
            matrix2.add(list1);
        }


        return matrix2;
    }

}
