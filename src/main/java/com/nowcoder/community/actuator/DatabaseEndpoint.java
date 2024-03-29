package com.nowcoder.community.actuator;

import com.nowcoder.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@Endpoint(id="database")
public class DatabaseEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseEndpoint.class);

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    // 该端点只能用get请求来访问
    @ReadOperation
    public String checkConnection(){
        try (Connection conn = dataSource.getConnection();){
            return CommunityUtil.getJSONString(0,"获取数据库连接成功！");
        } catch (SQLException e) {
            logger.error("获取数据库连接失败："+ e.getMessage());
            return CommunityUtil.getJSONString(1,"获取数据库连接失败！");
        }
    }

}
