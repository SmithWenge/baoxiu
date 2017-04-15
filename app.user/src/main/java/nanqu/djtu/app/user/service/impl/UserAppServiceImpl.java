package nanqu.djtu.app.user.service.impl;

import nanqu.djtu.app.user.repository.UserAppRepositoryI;
import nanqu.djtu.app.user.service.UserAppServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAppServiceImpl implements UserAppServiceI {
    private static final Logger LOG = LoggerFactory.getLogger(UserAppServiceImpl.class);

    @Autowired
    private UserAppRepositoryI userAppRepository;
}
