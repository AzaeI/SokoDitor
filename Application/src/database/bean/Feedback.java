package database.bean;

public class Feedback {

    private long user_id;
    private long level_id;
    private String commentary;
    private short satisfaction_ladder;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getLevel_id() {
        return level_id;
    }

    public void setLevel_id(long level_id) {
        this.level_id = level_id;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public short getSatisfaction_ladder() {
        return satisfaction_ladder;
    }

    public void setSatisfaction_ladder(short satisfaction_ladder) {
        this.satisfaction_ladder = satisfaction_ladder;
    }
}
