package PL.AssociationUI;

import DL.Game.LeagueSeason.League;
import DL.Game.LeagueSeason.LeagueSeason;
import DL.Game.LeagueSeason.Season;
import DL.Game.Policy.ScorePolicy;
import PL.main.App;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;


import static PL.AlertUtil.showSimpleAlert;

public class ScorePolicyController extends AInitComboBoxObjects {

    //create new score policy screen
    @FXML
    private JFXTextField winPoints;
    @FXML
    private JFXTextField drawPoints;
    @FXML
    private JFXTextField losePoints;

    //change score policy screen
    @FXML
    JFXComboBox<LeagueSeason> leagues;
    @FXML
    JFXComboBox<Season> seasons;
    @FXML
    JFXComboBox<ScorePolicy> scorePolicies;

    public void initComboBoxOptions() {
        if (!initSeasonChoices(seasons) || !initScorePolicyChoices(scorePolicies)) {
            closeWindow();
            return;
        }
        //inits leagueSeason combo box after choosing season
        seasons.setOnAction((e) -> {
            if (seasons.getValue() != null) {
                leagues.setItems(null);
                if (!initLeagueSeasonsChoices(leagues, seasons.getValue()))
                    closeWindow();
            }
        });
    }

    public void createScorePolicy() {
        int win, draw, lose;
        win = draw = lose = 0;

        try {

            if (winPoints.getText() != null && !winPoints.getText().equals("")
                    && drawPoints.getText() != null && !drawPoints.getText().equals("")
                    && losePoints.getText() != null && !losePoints.getText().equals("")) {

                win = Integer.parseInt(winPoints.getText());
                draw = Integer.parseInt(drawPoints.getText());
                lose = Integer.parseInt(losePoints.getText());

                if (App.clientSystem.policiesUnit.addNewScorePolicy(win, draw, lose)) {
                    showSimpleAlert("Success", "Score Policy added successfully!");
                } else {
                    showSimpleAlert("Error", "There was a problem with the server. Please try again later");
                }
                closeWindow();
            } else {
                showSimpleAlert("Error", "Please fill the required (*) fields.");
            }

        } catch (NumberFormatException e) {
            showSimpleAlert("Error", "Please insert only numbers.");
        } catch (Exception e) {
            showSimpleAlert("Error", e.getMessage());
        }


    }

    public void changeScorePolicy() {
        League league;
        Season season;
        ScorePolicy newScorePolicy;
        LeagueSeason leagueSeason;
        try {
            if (seasons.getValue() == null || leagues.getValue() == null || scorePolicies.getValue() == null) {
                throw new Exception("Please fill all the fields.");
            }

            //season = seasons.getValue();
            leagueSeason = leagues.getValue();
            newScorePolicy = scorePolicies.getValue();

            //leagueSeason = App.clientSystem.leagueSeasonUnit.getLeagueSeason(season, league);

            if (App.clientSystem.leagueSeasonUnit.changeScorePolicy(leagueSeason, newScorePolicy)) {
                showSimpleAlert("Success", "Score Policy changed successfully!");
            } else {
                showSimpleAlert("Error", "There was a problem with the server. Please try again later");
            }

            closeWindow();
        } catch (Exception e) {
            showSimpleAlert("Error", e.getMessage());
        }

    }

    public void closeWindow() {
        AssociationController.loadScreen("AssociationManagePoliciesFXML");
    }
}
