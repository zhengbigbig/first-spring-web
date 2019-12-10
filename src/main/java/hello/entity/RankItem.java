package hello.entity;

import java.io.Serializable; // 负责将java object -> 字节流给redis

public class RankItem implements Serializable {
    private int score;
    private User user;

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
