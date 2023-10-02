package tropcioassessment2.tropico2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.stage.StageStyle;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene sceneSign;
    private static Scene sceneMain;
    
    private static Scene sceneAddInventory;
    private static Scene sceneEditInventory;
    private static Scene sceneMainInventory;
    private static Scene sceneStocktakeInventory;
    private static Scene sceneCurrentStockInventory;

    private static Scene sceneAddCustomer;
    private static Scene sceneEditCustomer;
    private static Scene sceneMainCustomer;
    private static Scene sceneOrderFormCustomer;

    private static Scene sceneAddVendor;
    private static Scene sceneEditVendor;
    private static Scene sceneMainVendor;
    private static Scene scenePurchaseOrderVendor;

    private static Scene sceneCustomerListReporting;
    private static Scene sceneVendorListReporting;
    private static Scene sceneMainReporting;
    private static Scene sceneOrderReceiptReporting;
    private static Scene sceneOrderHistoryReporting;
    private static Scene scenePriceListReporting;
    private static Scene sceneProfitMarginReporting;
    private static Scene scenePurchaseHistoryReporting;
    private static Scene sceneRankingReporting;

    private static Stage stage;
    private static Database data;
    private static DataHandlerPeople dataHandlerPeople;//this is for the people class
    private static DataHandlerInventory dataHandlerInventory;//this is for the people class
    private static DataHandlerTransaction dataHandlerTransaction;//this is for the transaction class

  //adds reference to the data object
    public static DataHandlerPeople getDataHandlerPeople() {
    return dataHandlerPeople;
}
    
      //adds reference to the data object
    public static DataHandlerInventory getDataHandlerInventory() {
    return dataHandlerInventory;
}
    
      //adds reference to the data object
    public static DataHandlerTransaction getDataHandlerTransaction() {
    return dataHandlerTransaction;
}
    
    @Override
    public void start(Stage stage) throws IOException {
        //Instatiates the DataHandler object
        data = new Database("data.txt");
        dataHandlerPeople = new DataHandlerPeople("people.txt");
        dataHandlerInventory = new DataHandlerInventory("inventory.txt");
        dataHandlerTransaction = new DataHandlerTransaction("transaction.txt");
        
        //Creates the Main and AddNumber scene 
        Parent rootSign = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        Parent rootMain = FXMLLoader.load(getClass().getResource("mainMain.fxml"));

        Parent rootAddInventory = FXMLLoader.load(getClass().getResource("inventoryAdd.fxml"));
        Parent rootEditInventory = FXMLLoader.load(getClass().getResource("inventoryEdit.fxml"));
        Parent rootMainInventory = FXMLLoader.load(getClass().getResource("inventoryMain.fxml"));
        Parent rootStocktakeInventory = FXMLLoader.load(getClass().getResource("inventoryStocktake.fxml"));
        Parent rootCurrentStockInventory = FXMLLoader.load(getClass().getResource("inventoryCurrentStock.fxml"));

        Parent rootAddCustomer = FXMLLoader.load(getClass().getResource("customerAdd.fxml"));
        Parent rootEditCustomer = FXMLLoader.load(getClass().getResource("customerEdit.fxml"));
        Parent rootMainCustomer = FXMLLoader.load(getClass().getResource("customerMain.fxml"));
        Parent rootOrderFormCustomer = FXMLLoader.load(getClass().getResource("customerOrderForm.fxml"));

        Parent rootAddVendor = FXMLLoader.load(getClass().getResource("vendorAdd.fxml"));
        Parent rootEditVendor = FXMLLoader.load(getClass().getResource("vendorEdit.fxml"));
        Parent rootMainVendor = FXMLLoader.load(getClass().getResource("vendorMain.fxml"));
        Parent rootPurchaseOrderVendor = FXMLLoader.load(getClass().getResource("vendorPurchaseOrder.fxml"));

        Parent rootCustomerList = FXMLLoader.load(getClass().getResource("reportingListCustomer.fxml"));
        Parent rootVendorList = FXMLLoader.load(getClass().getResource("reportingListVendor.fxml"));
        Parent rootReportingMain = FXMLLoader.load(getClass().getResource("reportingMain.fxml"));
        Parent rootOrderReceipt = FXMLLoader.load(getClass().getResource("reportingOrderReceipt.fxml"));
        Parent rootOrderHistory = FXMLLoader.load(getClass().getResource("reportingOrderHistory.fxml"));
        Parent rootPriceList = FXMLLoader.load(getClass().getResource("reportingPriceList.fxml"));
        Parent rootProfitMargin = FXMLLoader.load(getClass().getResource("reportingProfitMargin.fxml"));
        Parent rootPurchaseHistory = FXMLLoader.load(getClass().getResource("reportingPurchaseHistory.fxml"));
        Parent rootRanking = FXMLLoader.load(getClass().getResource("reportingRanking.fxml"));

        sceneSign = new Scene(rootSign);
        sceneMain = new Scene(rootMain);

        sceneAddInventory = new Scene(rootAddInventory);
        sceneEditInventory = new Scene(rootEditInventory);
        sceneMainInventory = new Scene(rootMainInventory);
        sceneStocktakeInventory = new Scene(rootStocktakeInventory);
        sceneCurrentStockInventory = new Scene(rootCurrentStockInventory);

        sceneAddCustomer = new Scene(rootAddCustomer);
        sceneEditCustomer = new Scene(rootEditCustomer);
        sceneMainCustomer = new Scene(rootMainCustomer);
        sceneOrderFormCustomer = new Scene(rootOrderFormCustomer);

        sceneAddVendor = new Scene(rootAddVendor);
        sceneEditVendor = new Scene(rootEditVendor);
        sceneMainVendor = new Scene(rootMainVendor);
        scenePurchaseOrderVendor = new Scene(rootPurchaseOrderVendor);

        sceneCustomerListReporting = new Scene(rootCustomerList);
        sceneVendorListReporting = new Scene(rootVendorList);
        sceneMainReporting = new Scene(rootReportingMain);
        sceneOrderReceiptReporting = new Scene(rootOrderReceipt);
        sceneOrderHistoryReporting = new Scene(rootOrderHistory);
        scenePriceListReporting = new Scene(rootPriceList);
        sceneProfitMarginReporting = new Scene(rootProfitMargin);
        scenePurchaseHistoryReporting = new Scene(rootPurchaseHistory);
        sceneRankingReporting = new Scene(rootRanking);

        this.stage = stage;
       
        // Sets style to to remove the title bar - minimize etc
stage.initStyle(StageStyle.UNDECORATED);

        //scene = new Scene(loadFXML("mainMenu"), 620, 510);
               stage.setScene(sceneSign);
        stage.show();
    }

    //Method for passing a reference to the data object
   // public static Database getDataHandler() {
   //     return data;
  //  }
    
   


    //Method for switching scenes
    public static void changeScene(int sc) {
        switch (sc) {
            case 0:
                stage.setScene(sceneSign);
                break;
            case 1:
                stage.setScene(sceneMain);
                break;
            case 2:
                stage.setScene(sceneMainInventory);
                break;
            case 3:
                stage.setScene(sceneAddInventory);
                break;
            case 4:
                stage.setScene(sceneEditInventory);
                break;
            case 5:
                stage.setScene(sceneStocktakeInventory);
                break;
            case 6:
                stage.setScene(sceneCurrentStockInventory);
                break;
            case 7:
                stage.setScene(sceneMainCustomer);
                break;
            case 8:
                stage.setScene(sceneAddCustomer);
                break;
            case 9:
                stage.setScene(sceneEditCustomer);
                break;
            case 10:
                stage.setScene(sceneOrderFormCustomer);
                break;
            case 11:
                stage.setScene(sceneMainVendor);
                break;
            case 12:
                stage.setScene(sceneAddVendor);
                break;
            case 13:
                stage.setScene(sceneEditVendor);
                break;
            case 14:
                stage.setScene(scenePurchaseOrderVendor);
                break;
            case 15:
                stage.setScene(sceneCustomerListReporting);
                break;
            case 16:
                stage.setScene(sceneVendorListReporting);
                break;
            case 17:
                stage.setScene(sceneMainReporting);
                break;
            case 18:
                stage.setScene(sceneOrderReceiptReporting);
                break;
            case 19:
                stage.setScene(sceneOrderHistoryReporting);
                break;
            case 20:
                stage.setScene(scenePriceListReporting);
                break;
            case 21:
                stage.setScene(sceneProfitMarginReporting);
                break;
            case 22:
                stage.setScene(scenePurchaseHistoryReporting);
                break;
            case 23:
                stage.setScene(sceneRankingReporting);
                break;
            default:
        }
    }

    //Method for exiting the application
    public static void exit() {
        stage.close();
    }

    public static void main(String[] args) {
        launch();
    }

}
