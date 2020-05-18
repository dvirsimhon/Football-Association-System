package PL.AssociationUI;

import BL.Client.Handlers.AssociationManagementUnit;
import BL.Communication.CommunicationAssociationManagementStub;
import DL.Game.Referee;
import DL.Users.Fan;
import DL.Users.User;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static PL.AlertUtil.showSimpleAlert;

public class RefereeController extends AInitComboBoxObjects {

    private CommunicationAssociationManagementStub clientServerCommunication;

    private AssociationManagementUnit associationManagementUnit;

    @FXML
    public ComboBox<String> comboboxReferee;

    @FXML
    private JFXTextField refereeUsername;

    @FXML
    private JFXTextField refereeName;

    @FXML
    private JFXTextField refereeQualification;

    private static Stage subStage;

    private ObservableList<String> referees_username_FX;

    public void initCreateRefereeComboBoxOptions() {

        clientServerCommunication = new CommunicationAssociationManagementStub(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        associationManagementUnit = new AssociationManagementUnit(clientServerCommunication);

        referees_username_FX = FXCollections.observableArrayList();
        comboboxReferee.setItems(referees_username_FX);
        List<Referee> referees = clientServerCommunication.query("AllReferees", new HashMap<>());
        List<String> referees_username = new ArrayList<>();
        for (Referee referee : referees)
            referees_username.add(referee.getFan().getUsername());
        referees_username_FX.addAll(referees_username);

    }


    public void addReferee(ActionEvent actionEvent)
    {

        Fan fan = getFanByUsername(refereeUsername.getText());
        if (associationManagementUnit.addNewReferee(fan, refereeName.getText(), refereeQualification.getText()))
        {
            referees_username_FX.add(refereeUsername.getText());
            //showMessage(true);
            showSimpleAlert("Success","Referee was added successfully");
        }

        else
        {
            //showMessage(false);
            showSimpleAlert("Error","Please fill the required (*) fields.");
        }

        clearFieldsCreateReferee(actionEvent);

    }

    public void removeReferee(ActionEvent actionEvent)
    {
        Fan fan = getFanByUsername(comboboxReferee.getValue());
        if (fan == null) {
            //showMessage(false);
            showSimpleAlert("Error","Please fill the required (*) fields.");
        }

        HashMap<String, Object> args = new HashMap<>();
        args.put("fan", fan);
        List<Referee> referees = clientServerCommunication.query("refereeByFan", args);
        Referee referee = null;
        if (!referees.isEmpty())
            referee = referees.get(0);

        if (!associationManagementUnit.removeReferee(referee)) //TODO: remove "!"
        {
            referees_username_FX.remove(comboboxReferee.getValue());
            //showMessage(true);
            showSimpleAlert("Success","Referee was removed successfully");

        }

        else
        {
            //showMessage(false);
            showSimpleAlert("Error","Please fill the required (*) fields.");
        }

    }

    public void closeMessage(ActionEvent mouseEvent)
    {
        subStage.close();
    }


    public void clearFieldsCreateReferee(ActionEvent mouseEvent)
    {
        if (refereeName != null) refereeName.clear();
        if (refereeUsername != null) refereeUsername.clear();
        if (refereeQualification != null) refereeQualification.clear();
    }


  /*  private void showMessage(boolean success)
    {
        try {
            subStage = new Stage();
            Parent root;
            if (success)
            {
                subStage.setTitle("Insertion Succeeded");
                FXMLLoader fxmlLoader = new FXMLLoader();
                root = fxmlLoader.load(getClass().getResource("/Window/SuccessMsg.fxml").openStream());
            }
            else
            {
                subStage.setTitle("Insertion Failed");
                FXMLLoader fxmlLoader = new FXMLLoader();
                root = fxmlLoader.load(getClass().getResource("/Window/FailureMsg.fxml").openStream());
            }

            Scene scene = new Scene(root, 400, 350);
            subStage.setScene(scene);
            subStage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            subStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private Fan getFanByUsername(String username)
    {
        HashMap<String, Object> args = new HashMap<>();
        args.put("username", username);
        List<User> user = clientServerCommunication.query("UserByUsername", args);

        if (user == null || user.isEmpty() || !(user.get(0) instanceof Fan)) return null;
        return (Fan) user.get(0);

    }
}
