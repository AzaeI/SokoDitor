package storage.bean;

public class Score {

    private long user_id;
    private long level_id;
    private int score;

    public long getUsername() {
        return user_id;
    }

    public void setUsername(long user_id) {
        this.user_id = user_id;
    }

    public long getLevel() {
        return level_id;
    }

    public void setLevel(long level_id) {
        this.level_id = level_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
