package com.arafa.mohamed.darsidraapp.models;

public class RatingTeacherModel {
    private String  notesAttendanceDeparture, scoreAttendanceDeparture, notesClassroomCleanTidy, scoreClassroomCleanTidy,
            notesValueGame, scoreValueGame, notesDealingAtmosphere, scoreDealingAtmosphere, notesTimeManagement,
            scoreTimeManagement, total, achievements;


    public RatingTeacherModel() {
    }

    public RatingTeacherModel(String notesAttendanceDeparture, String scoreAttendanceDeparture,
                              String notesClassroomCleanTidy, String scoreClassroomCleanTidy, String notesValueGame,
                              String scoreValueGame, String notesDealingAtmosphere, String scoreDealingAtmosphere,
                              String notesTimeManagement, String scoreTimeManagement, String total, String achievements) {
        this.notesAttendanceDeparture = notesAttendanceDeparture;
        this.scoreAttendanceDeparture = scoreAttendanceDeparture;
        this.notesClassroomCleanTidy = notesClassroomCleanTidy;
        this.scoreClassroomCleanTidy = scoreClassroomCleanTidy;
        this.notesValueGame = notesValueGame;
        this.scoreValueGame = scoreValueGame;
        this.notesDealingAtmosphere = notesDealingAtmosphere;
        this.scoreDealingAtmosphere = scoreDealingAtmosphere;
        this.notesTimeManagement = notesTimeManagement;
        this.scoreTimeManagement = scoreTimeManagement;
        this.total = total;
        this.achievements = achievements;
    }

    public String getNotesAttendanceDeparture() {
        return notesAttendanceDeparture;
    }

    public void setNotesAttendanceDeparture(String notesAttendanceDeparture) {
        this.notesAttendanceDeparture = notesAttendanceDeparture;
    }

    public String getScoreAttendanceDeparture() {
        return scoreAttendanceDeparture;
    }

    public void setScoreAttendanceDeparture(String scoreAttendanceDeparture) {
        this.scoreAttendanceDeparture = scoreAttendanceDeparture;
    }

    public String getNotesClassroomCleanTidy() {
        return notesClassroomCleanTidy;
    }

    public void setNotesClassroomCleanTidy(String notesClassroomCleanTidy) {
        this.notesClassroomCleanTidy = notesClassroomCleanTidy;
    }

    public String getScoreClassroomCleanTidy() {
        return scoreClassroomCleanTidy;
    }

    public void setScoreClassroomCleanTidy(String scoreClassroomCleanTidy) {
        this.scoreClassroomCleanTidy = scoreClassroomCleanTidy;
    }

    public String getNotesValueGame() {
        return notesValueGame;
    }

    public void setNotesValueGame(String notesValueGame) {
        this.notesValueGame = notesValueGame;
    }

    public String getScoreValueGame() {
        return scoreValueGame;
    }

    public void setScoreValueGame(String scoreValueGame) {
        this.scoreValueGame = scoreValueGame;
    }

    public String getNotesDealingAtmosphere() {
        return notesDealingAtmosphere;
    }

    public void setNotesDealingAtmosphere(String notesDealingAtmosphere) {
        this.notesDealingAtmosphere = notesDealingAtmosphere;
    }

    public String getScoreDealingAtmosphere() {
        return scoreDealingAtmosphere;
    }

    public void setScoreDealingAtmosphere(String scoreDealingAtmosphere) {
        this.scoreDealingAtmosphere = scoreDealingAtmosphere;
    }

    public String getNotesTimeManagement() {
        return notesTimeManagement;
    }

    public void setNotesTimeManagement(String notesTimeManagement) {
        this.notesTimeManagement = notesTimeManagement;
    }

    public String getScoreTimeManagement() {
        return scoreTimeManagement;
    }

    public void setScoreTimeManagement(String scoreTimeManagement) {
        this.scoreTimeManagement = scoreTimeManagement;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }
}
