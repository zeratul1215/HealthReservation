package leetcode;

import java.lang.reflect.Array;
import java.util.*;


class Leetcode {
    int solution(int[] numbers) {
        HashMap<Integer, ArrayList<Integer>> m = new HashMap<>();
        int[] times = {1000000000,
                100000000,
                10000000,
                1000000,
                100000,
                10000,
                1000,
                100,
                10,
                1};
        for(int i = 1 ; i <= 10 ; i++){
            ArrayList<Integer> l = new ArrayList<>();
            m.put(i, l);
        }
        for(int i : numbers){
            for(int j = 0 ; j < times.length ; j++){
                if(i / times[j] > 0){
                    ArrayList l = m.get(10 - j);
                    l.add(i);
                    m.put(10 - j, l);
                    break;
                }
            }
        }
        int ret = 0;
        for(int i = 1 ; i <= 10 ; i++){
            if(i == 1){
                ArrayList<Integer> l = m.get(1);
                if(l.size() >= 2){
                    ret += l.size() * (l.size() - 1) / 2;
                }
            }
            else{
                ArrayList<Integer> l = m.get(i);
                if(l.size() >= 2){
                    for(int j = 0 ; j < l.size() ; j++){
                        for(int k = j ; k < l.size() ; k++){
                            if(judge(l.get(j), l.get(k), i)){
                                ret++;
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    boolean judge(int i1, int i2, int n){
        int count = 0;
        int time = 1;
        for(int i = 1 ; i < n ; i++){
            time *= 10;
        }
        while(time > 0){
            int digit1 = i1/time;
            int digit2 = i2/time;
            if(digit1 != digit2){
                count++;
            }
            if(count > 1){
                return false;
            }
            i1 = i1%time;
            i2 = i2%time;
            time /= 10;
        }
        return count == 1;
    }

    int[] solution1(int[] numbers) {
        int n = numbers.length;
        if(n == 2){
            return numbers;
        }
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        arr1.add(numbers[0]);
        arr2.add(numbers[1]);
        for(int i = 2 ; i < n ; i++){
            int curr = numbers[i];
            double avg1 = 0;
            for(int i1 : arr1){
                avg1 += Math.abs(i1 - curr);
            }
            avg1 /= (double)arr1.size();
            double avg2 = 0;
            for(int i2 : arr2){
                avg2 += Math.abs(i2 - curr);
            }
            avg2 /= arr2.size();
            if(avg1 > avg2){
                arr1.add(curr);
            }
            else{
                arr2.add(curr);
            }
        }
        int[] ret = new int[n];
        int id = 0;
        for(int i = 0 ; i < arr1.size() ; i++){
            ret[id++] = arr1.get(i);
        }
        for(int i = 0 ; i < arr2.size() ; i++){
            ret[id++] = arr2.get(i);
        }
        return ret;
    }


    int[] solution2(int[] memory, int[][] queries) {
        int n = memory.length;
        int[] ret = new int[queries.length];
        HashMap<Integer,int[]> m = new HashMap<>();
        for(int i = 0 ; i < queries.length ; i++){
            if(queries[i][0] == 0){
                int length = queries[i][1];
                int id = 0;
                while(id <= n - length){
                    int right = id;
                    int currLength = 0;
                    while(right < n && memory[right] == 0 && cur){
                        right++;
                        currLength++;
                    }
                    if(currLength == length){
                        for(int start = id ; start < right ; start++){
                            memory[start] = 1;
                        }
                        ret[i] = id;
                        int[] bound = new int[2];
                        bound[0] = id;
                        bound[1] = right;
                        m.put(i, bound);
                    }
                    else{
                        id = right + 1;
                        ret[i] = -1;
                    }
                }
            }
            else{
                if(m.containsKey(queries[i][1])){
                    int[] bound = m.get(queries[i][1]);
                    int left = bound[0];
                    int right = bound[1];
                    for(int j = left ; j < right ; j++){
                        memory[j] = 0;
                    }
                    ret[i] = right - left;
                    m.remove(queries[i][1]);
                }
                else{
                    ret[i] = -1;
                }
            }
        }
        return ret;
    }



    public static void main(String[] args) {
        String[] members = {"id123", "id234", "id7", "id321"};
        String[] messages = {"Hey @id123,id321 review this PR please! @id123 what do you think about this approach?",
                "Hey @id7 nice appro@ch! Great job! @id800 what do you think?",
                "@id123,id321 thx!"};
        int[] i = {0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0};
        int[][] queries = {{0,2},{0,1},{0,1},{1,2},{1,4},{0,4}};
        Leetcode l = new Leetcode();
        l.solution2(i,queries);
    }
}