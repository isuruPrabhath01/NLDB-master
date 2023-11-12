package controller;

public class DisplayDashBoardFormController {

   /* private void loardChart() throws SQLException, ClassNotFoundException {
        PreparedStatement prst = DbConnection.getInstance().getConnection().prepareStatement("select * from Item WHERE `type`=?");
        prst.setObject(1,"Mobile");
        ResultSet resultSet = prst.executeQuery();
        int accessories=0;
        int mobile=0;

        while (resultSet.next()){
            accessories+=Integer.parseInt(resultSet.getString(6));
        }
        prst = DbConnection.getInstance().getConnection().prepareStatement("select * from Item WHERE `type`= ?");
        prst.setObject(1,"Accessories");
        resultSet = prst.executeQuery();
        while (resultSet.next()){
            mobile+=Integer.parseInt(resultSet.getString(6));
        }

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Accessories", accessories),
                        new PieChart.Data("Mobile", mobile));

        mainChart.setData(pieChartData);

    }*/

}
