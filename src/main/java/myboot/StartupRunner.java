package myboot;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import myboot.dao.UserDao;

public class StartupRunner implements CommandLineRunner {
	private static final Logger LOGGER = Logger.getLogger(StartupRunner.class);

    @Autowired
    private UserDao userDao;

    @Override
    public void run(String... strings) throws Exception {
    	LOGGER.warn("Number of users: " + userDao.count());
    }

}
