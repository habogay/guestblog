package articles.data;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class QnAPersistenceManager {
	private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private QnAPersistenceManager() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}
