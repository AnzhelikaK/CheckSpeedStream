package com.anzhelika;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class Help {
    static private Random random = new Random();
    private Connection connection = DBConnection.getConnection();

    List<String> getList(int sizeOflist, int maxLongWords) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < sizeOflist; i++) {
            list.add(randomWord(maxLongWords));

        }
        return list;
    }

    private String randomWord(int maxLongWords) {
        int lengthWord = random.nextInt(maxLongWords);
        StringBuilder word = new StringBuilder();
        for (int j = 0; j < lengthWord; j++) {
            word.append((char) (97 + random.nextInt(26)));
        }
        return word.toString();
    }

    void save(long m1, long m2, long m3, long m4, String size, long time) throws SQLException {

        String sql = "insert into checkspeedstream(For_i, For_each, Stream, ParallelStream,sizeOfList,TimeOfCreateList) values (?,?,?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, m1);
        preparedStatement.setLong(2, m2);
        preparedStatement.setLong(3, m3);
        preparedStatement.setLong(4, m4);
        preparedStatement.setString(5, size);
        preparedStatement.setLong(6, time);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }

}

