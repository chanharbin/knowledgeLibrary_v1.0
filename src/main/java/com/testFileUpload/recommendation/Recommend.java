package com.testFileUpload.recommendation;

import java.util.*;
import java.util.stream.Collectors;

public class Recommend {
    /**
     * 余弦相似度
    */
    public double Euclidean(List<Integer> one,List<Integer> two){
        double result = 0;
        double upPart = 0;
        double powerx_2 = 0;
        double powery_2 = 0;

        for(int i =0;i<one.size();i++){
            int key1 = one.get(i);
            int key2 = two.get(i);
            upPart += key1 * key2;
            powerx_2 += Math.pow(key1,2);
            powery_2 += Math.pow(key2,2);
        }
        result = upPart / (Math.sqrt(powerx_2) * Math.sqrt(powery_2));
        //System.out.println(result);
        return result;
    }

    public String Recommend(UserSet userSet,String username){
        Map<Double,String> map = new TreeMap<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return -1;
            }
        });
        UserSet.User user = userSet.getUser(username);
        List<UserSet.Set> list = user.list;
        List<Integer> userList = list.stream().map(set -> {
            int score = set.score;
            return score;
        }).collect(Collectors.toList());
        //若用户无评分，随机推荐
        int sum = userList.stream().mapToInt(Integer::intValue).sum();
        if(sum == 0){
            Random random = new Random();
            int randomNum = random.nextInt(list.size());
            String recommend = list.get(randomNum).itemname;
            return recommend;
        }
        List<UserSet.User> users = userSet.users;
        //将与指定用户的权重用map存储起来
        for(int i = 0;i<users.size();i++){
            if(!username.equals(users.get(i).username)){
                List<UserSet.Set> list1 = users.get(i).list;
                List<Integer> userByList = list1.stream().map(set -> set.score).collect(Collectors.toList());
                double euclidean = Euclidean(userList, userByList);
                map.put(euclidean,users.get(i).username);
            }
        }
        //获取最接近的2个人的姓名 和对应的Euclidean
        List<Double> listEuByTop = new LinkedList<>();
        List<String> listByTop = new LinkedList<>();
        Iterator<Map.Entry<Double, String>> iterator = map.entrySet().iterator();
        int i =0;
        while(iterator.hasNext()){
            if(i == 2){
                break;
            }
            Map.Entry<Double, String> entry = iterator.next();
            Double key = entry.getKey();
            String value = entry.getValue();
            //System.out.println(i + 1 + value);
            listByTop.add(value);
            listEuByTop.add(key);
            i++;
        }

        //根据最接近的两个人的姓名获取得分矩阵
        String top = listByTop.get(0);
        String second = listByTop.get(1);
        UserSet.User user1 = userSet.getUser(top);
        UserSet.User user2 = userSet.getUser(second);
        List<UserSet.Set> list1 = user1.list;
        List<UserSet.Set> list2 = user2.list;
        List<Integer> list1Score = list1.stream().map(set -> set.score).collect(Collectors.toList());
        List<Integer> list2Score = list2.stream().map(set -> set.score).collect(Collectors.toList());
        Map<Double,String> map1 = new TreeMap<>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return -1;
            }
        });
        for(int j =0;j<userList.size();j++){
            Integer score = userList.get(j);
            if(score == 0){
                //加权求预测得分
                Double score_byPre = (list1Score.get(j)*listEuByTop.get(0) + list2Score.get(j)*listEuByTop.get(1))/(listEuByTop.get(0) + listEuByTop.get(1));
                //System.out.println(score_byPre);
                map1.put(score_byPre,list1.get(j).itemname);
            }
        }
        String recommenderItem = map1.values().iterator().next();
        return recommenderItem;
    }
}
