package nanqu.djtu.log.service;

import nanqu.djtu.pojo.LogContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ILogService {
    Page<LogContent> query4Page(LogContent logContent, Pageable pageable);
}
