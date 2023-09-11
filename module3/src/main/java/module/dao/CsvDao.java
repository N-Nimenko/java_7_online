package module.dao;

import module.config.JdbcConfig;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CsvDao {
    private final JdbcConfig config = JdbcConfig.getInstance();

    public boolean exportAccountStatementToCsv(String accountNumber, Date startDate, Date endDate) {
        String csvFilePath = "output.csv";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try (Connection connection = config.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "SELECT id, amount, comment, date, receiver_account_number, sender_account_number " +
                             "FROM operation " +
                             "WHERE receiver_account_number = ? OR sender_account_number = ? AND date >= ? AND date <= ?");
             FileWriter csvWriter = new FileWriter(csvFilePath)) {

            ps.setString(1, accountNumber);
            ps.setString(2, accountNumber);
            ps.setTimestamp(3, new java.sql.Timestamp(startDate.getTime()));
            ps.setTimestamp(4, new java.sql.Timestamp(endDate.getTime()));

            try (ResultSet rs = ps.executeQuery()) {
                csvWriter.append("ID,Amount,Comment,Date,Receiver Account Number,Sender Account Number");
                csvWriter.append("\n");

                while (rs.next()) {
                    String id = rs.getString("id");
                    String amount = String.format("%.2f", rs.getDouble("amount"));
                    String comment = rs.getString("comment");
                    java.util.Date date = rs.getTimestamp("date");
                    String receiverAccountNumber = rs.getString("receiver_account_number");
                    String senderAccountNumber = rs.getString("sender_account_number");

                    String formattedDate = dateFormat.format(date);

                    csvWriter.append(String.format("ID: %s, Amount: %s, Comment: %s, Date: %s, Receiver Account Number: %s, Sender Account Number: %s",
                            id, amount, comment, formattedDate, receiverAccountNumber, senderAccountNumber));
                    csvWriter.append("\n");
                }
                return true;
            }
        } catch (SQLException | IOException e) {
            System.out.println("An exception happened: " + e);
            return false;
        }
    }
}
