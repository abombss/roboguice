package roboguice.event.eventListener;

import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;

/**
 * @author John Ericksen
 */
public class RunnableAsyncTaskAdaptorTest {

    protected Runnable runnable;

    protected RunnableAsyncTaskAdaptor runnableAdaptor;

    @Before
    public void setup(){
        runnable = createMock(Runnable.class);

        runnableAdaptor = new RunnableAsyncTaskAdaptor(runnable);
    }

    @Test
    public void test() throws Exception {
        reset(runnable);

        runnable.run();

        replay(runnable);

        runnableAdaptor.call();

        verify(runnable);
    }

}
