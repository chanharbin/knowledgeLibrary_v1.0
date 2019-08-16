package com.testFileUpload.recommendation;

import java.util.ArrayList;
import java.util.List;

/**
*数据集合
 */
public class UserSet {

    public List<User> users = new ArrayList<>();

    public UserSet() {
    }

    public User put(String username) {
        return new User(username);
    }


    public User getUser(int position) {
        return users.get(position);
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }


    public final class User {
        public String username;
        public List<Set> list = new ArrayList<>();

        private User(String username) {
            this.username = username;
        }

        public User set(String item, int score) {
            this.list.add(new Set(item, score));
            return this;
        }

        public void create() {
            users.add(this);
        }

        public Set find(String itemname) {
            for (Set set : list) {
                if (set.itemname.equals(itemname)) {
                    return set;
                }
            }
            return null;
        }
        public void add(String itemname){list.add(find(itemname));}

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    '}';
        }
    }

    public final class Set implements Comparable<Set> {
        public String itemname;
        public int score;

        public Set(String itemname, int score) {
            this.itemname = itemname;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Set{" +
                    "itemname='" + itemname + '\'' +
                    ", score=" + score +
                    '}';
        }

        @Override
        public int compareTo(Set o) {
            return score > o.score ? -1 : 1;
        }
    }

}
