package hello.dao;

import hello.entity.RankItem;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 思考：在不使用注解时，之前创建mybatis连接是通过new SqlSessionFactory 一系列操作，那么通过spring呢？
// 只需要通过autowired注入SqlSession直接使用
@Service
public class RankDao {
    @Autowired
    private SqlSession sqlSession;

    public List<RankItem> getRank(){
        return sqlSession.selectList("MyMapper.selectRank");
    }
}
