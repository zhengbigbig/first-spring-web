package hello.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RankDaoTest {
    private SqlSession mockSession = Mockito.mock(SqlSession.class);
    private RankDao rankDao = new RankDao(mockSession);

    @Test
    public void testGetBlogs() {
        // 当我传递 page = 2 pageSize = 10, user_id = 3
        // 数据库得到的参数应该是
        // user_id = 3,offset = 10,limit = 10

    }


}