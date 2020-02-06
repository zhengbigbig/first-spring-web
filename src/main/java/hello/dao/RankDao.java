package hello.dao;

import hello.entity.RankItem;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankDao {
    private SqlSession sqlSession;

    @Autowired
    public RankDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<RankItem> getRank(){
        return sqlSession.selectList("MyMapper.selectRank");
    }

}
