package nanqu.djtu.log.service.impl;

import nanqu.djtu.log.repository.ILogRepository;
import nanqu.djtu.log.service.ILogService;
import nanqu.djtu.pojo.LogContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements ILogService {
    @Autowired
    private ILogRepository logRepository;

    @Override
    public Page<LogContent> query4Page(LogContent logContent, Pageable pageable) {
        return logRepository.select4Page(logContent, pageable);
    }
}
