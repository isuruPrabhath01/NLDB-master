package controller;

import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ReportsFormController implements Initializable {
    public javafx.scene.chart.BarChart BarChart;
    public Label lblDayIncome;
    public Label lblAnnualIncome;
    public Label lblMonthlyIncome;



    
   /* private void sales () throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT \n" + "    SUM(cost) SalesQuantity\n" + "FROM\n" + "    Orders")
                .executeQuery();
        if (set.next()) {
            String customerCount = set.getString(1);
            lblIncom.setText(String.valueOf(customerCount));
            lblIncom1.setText(String.valueOf(customerCount));
            lblIncom2.setText(String.valueOf(customerCount));
        }
    }*/

  /*  private void getIncome() {
        String year, month, day;
        date = new Date();
        sdf = new SimpleDateFormat("yyyy");
        year = sdf.format(date);
        sdf = new SimpleDateFormat("yyyy-MM");
        month = sdf.format(date);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
         day = sdf.format(date);

        todayIncome = 0;
        thisMonthIncome = 0;
        thisYerIncome = 0;
        try {
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item_Reports where rdate  LIKE '" + year + "%'");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                thisYerIncome += Double.parseDouble(resultSet.getString(5));
            }
            lblAnnualIncome.setText("" + thisYerIncome);

            preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item_Reports where rdate  LIKE '" + month + "%'");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                thisMonthIncome += Double.parseDouble(resultSet.getString(5));
            }
            lblMonthlyIncome.setText("" + thisMonthIncome);

            preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item_Reports where rdate = '" + day + "'");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                todayIncome += Double.parseDouble(resultSet.getString(5));
            }
            lblDailyIncome.setText("" + todayIncome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    public javafx.scene.chart.BarChart getBarChart() {
        return BarChart;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

  /*      try {

            sales();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/


        BarChart.setTitle("INCOME");


        XYChart.Series series = new XYChart.Series();
        series.setName("Sales");

        series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 14));
        series.getData().add(new XYChart.Data("March", 15));
        series.getData().add(new XYChart.Data("April", 24));
        series.getData().add(new XYChart.Data("May", 34));
        series.getData().add(new XYChart.Data("Jun", 36));
        series.getData().add(new XYChart.Data("Jul", 22));
        series.getData().add(new XYChart.Data("Aug", 45));
        series.getData().add(new XYChart.Data("Sep", 43));
        series.getData().add(new XYChart.Data("Oct", 17));
        series.getData().add(new XYChart.Data("Nov", 29));
        series.getData().add(new XYChart.Data("Dec", 25));

        BarChart.getData().add(series);


    }
}
