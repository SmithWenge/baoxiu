package nanqu.djtu.log.repository;

import nanqu.djtu.pojo.LogContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ILogRepository {
    void insertLog(LogContent logContent);
    Page<LogContent> select4Page(LogContent logContent, Pageable pageable);
}
