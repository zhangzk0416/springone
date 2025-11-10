package springone.com.demo.demos.web.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import springone.com.demo.demos.web.utile.ThreadLocalUserId;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段填充");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createUser", Integer.class, ThreadLocalUserId.getCurrentId());
        this.strictInsertFill(metaObject, "updateUser", Integer.class, ThreadLocalUserId.getCurrentId());
        this.strictInsertFill(metaObject, "isDekete", int.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        strictInsertFill(metaObject, "updateUser", Integer.class, ThreadLocalUserId.getCurrentId());
    }
}
