public class PlayerId {
    int playerId;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) { this.playerId = playerId; }

    static void switchPlayer(PlayerId playerId) {
        //zmiana gracza
        if (playerId.getPlayerId() == 1)
            playerId.setPlayerId(2);
        else
            playerId.setPlayerId(1);
    }
}
