package Model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model implements IModel {


    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string

        String url = "jdbc:sqlite:makal/src/main/java/Database/makal.db";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    /**
     * select all rows in the warehouses table
     */
    @Override
    public ArrayList<String> getResultSetFromDatabase() {

        ArrayList<String> strings = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM makal";

        for (int i = 2; i < 138; i++) {
            try (Connection conn = this.connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                // loop through the result set
                String s1;

                while (rs.next()) {

                    s1 = rs.getString(i);
                    if (!s1.isEmpty()) {
                        s1 = s1.replaceAll("\\\\n", "%0A");
                        strings.add(s1);
                    }


//                System.out.println(
////                        rs.getInt("adam") +  "\t" +
//                        rs.getString("adam") + "\t"
////                       + rs.getString("til") + "\t"
////                                + rs.getDouble("adam")
//                );
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("array Size: " + strings.size());
        return strings;
    }

    @Override
    public void onSendToTelegram(String message) {
//        System.out.println("isMessageSentToTelegram() started");

        //The url to send message to Telegram
        String urlString = Keys.TELEGRAM_URL;

        //Your Token
        String apiToken = Keys.TELEGRAM_BOT_TOKEN_2;

        //Your chatId
        String chatId = Keys.TELEGRAM_CHANNEL_CHAT_ID_2;


        //Inserting your parameters to String
        urlString = String.format(urlString, apiToken, chatId, message);


        //Start sending data
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
//            System.out.println("Message Sent to Telegram");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}