package roboguice.event;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static junit.framework.Assert.assertEquals;

/**
 * Test class exercising the ObserverReferences
 *
 * @author John Ericksen
 */
public class ObserverReferenceTest {

    protected EqualityTestClass test;
    protected EqualityTestClass test2;
    protected Method methodOneBase;
    protected Method methodOne;
    protected Method methodTwoBase;
    protected Method methodTwo;


    @Before
    public void setup() throws NoSuchMethodException {
        methodOne = EqualityTestClass.class.getDeclaredMethod("one", Integer.TYPE, Character.TYPE, Boolean.TYPE);
        methodOneBase = EqualityTestOverrideClass.class.getDeclaredMethod("one", Integer.TYPE, Character.TYPE, Boolean.TYPE);
        methodTwo = EqualityTestClass.class.getDeclaredMethod("two", Object.class, String.class);
        methodTwoBase = EqualityTestOverrideClass.class.getDeclaredMethod("two", Object.class, String.class);

        test =  new EqualityTestClass();
        test2 = new EqualityTestClass();
    }

    @Test
    public void testEquality() {

        EventManagerImpl.ObserverMethodListener<EqualityTestClass> observerRefOne = new EventManagerImpl.ObserverMethodListener<EqualityTestClass>(test, methodOne);
        EventManagerImpl.ObserverMethodListener<EqualityTestClass> observerRefTwo = new EventManagerImpl.ObserverMethodListener<EqualityTestClass>(test, methodOneBase);

        assertEquals(observerRefOne, observerRefTwo);
        assertEquals(observerRefOne.hashCode(), observerRefTwo.hashCode());
    }

    @Test
    public void testEqualityOfSameGuts() {

        EventManagerImpl.ObserverMethodListener<EqualityTestClass> observerRefOne = new EventManagerImpl.ObserverMethodListener<EqualityTestClass>(test, methodOne);
        EventManagerImpl.ObserverMethodListener<EqualityTestClass> observerRefTwo = new EventManagerImpl.ObserverMethodListener<EqualityTestClass>(test, methodOne);

        assertEquals(observerRefOne, observerRefTwo);
        assertEquals(observerRefOne.hashCode(), observerRefTwo.hashCode());
    }

    @Test
    public void testInequalityBetweenSameClass() {

        EventManagerImpl.ObserverMethodListener<EqualityTestClass> observerRefOne = new EventManagerImpl.ObserverMethodListener<EqualityTestClass>(test, methodOne);
        EventManagerImpl.ObserverMethodListener<EqualityTestClass> observerRefTwo = new EventManagerImpl.ObserverMethodListener<EqualityTestClass>(test, methodTwo);

        assert !observerRefOne.equals(observerRefTwo) ;
        assert !Integer.valueOf(observerRefOne.hashCode()).equals(observerRefTwo.hashCode());
    }

    @Test
    public void testInequalityBetweenDifferentClass() {

        EventManagerImpl.ObserverMethodListener<EqualityTestClass> observerRefOne = new EventManagerImpl.ObserverMethodListener<EqualityTestClass>(test, methodOne);
        EventManagerImpl.ObserverMethodListener<EqualityTestClass> observerRefTwo = new EventManagerImpl.ObserverMethodListener<EqualityTestClass>(test, methodTwoBase);

        assert !observerRefOne.equals(observerRefTwo) ;
        assert !Integer.valueOf(observerRefOne.hashCode()).equals(observerRefTwo.hashCode());
    }

    @Test
    public void testInequalityBetweenDifferentInstances() {

        EventManagerImpl.ObserverMethodListener<EqualityTestClass> observerRefOne = new EventManagerImpl.ObserverMethodListener<EqualityTestClass>(test, methodOne);
        EventManagerImpl.ObserverMethodListener<EqualityTestClass> observerRefTwo = new EventManagerImpl.ObserverMethodListener<EqualityTestClass>(test2, methodOne);

        assert !observerRefOne.equals(observerRefTwo) ;
        assert !Integer.valueOf(observerRefOne.hashCode()).equals(observerRefTwo.hashCode());
    }

    @Test
    public void testInequalityBetweenDifferentInstancesAndDifferentMethods() {

        EventManagerImpl.ObserverMethodListener<EqualityTestClass> observerRefOne = new EventManagerImpl.ObserverMethodListener<EqualityTestClass>(test, methodOne);
        EventManagerImpl.ObserverMethodListener<EqualityTestClass> observerRefTwo = new EventManagerImpl.ObserverMethodListener<EqualityTestClass>(test2, methodTwoBase);

        assert !observerRefOne.equals(observerRefTwo) ;
        assert !Integer.valueOf(observerRefOne.hashCode()).equals(observerRefTwo.hashCode());
    }

    public class EqualityTestClass{

        public void one(int i, char c, boolean b){}

        public void two(Object one, String two){}
    }

    public class EqualityTestOverrideClass extends EqualityTestClass{
        public void one(int i, char c, boolean b){}

        public void two(Object one, String two){}
    }
}
