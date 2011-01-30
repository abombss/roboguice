package roboguice.event;

import android.content.Context;

import java.lang.reflect.Method;

/**
 *
 * Manager class handling the following:
 *
 *   Registration of event observing methods:
 *      registerObserver()
 *      unregisterObserver()
 *      clear()
 *   Raising Events:
 *      fire()
 *      notifyWithResult()
 *
 * @author Adam Tybor
 * @author John Ericksen
 */
public interface EventManager {

    /**
     * Register an observer EventListener with the current context (provided).
     *
     * @param event to observe
     * @param listener to be triggered
     * @param <T> event type
     */
    <T> void registerObserver( Class<T> event, EventListener listener );

    /**
     * Register a method observer with the current context (provided).
     * 
     * @param instance to be called
     * @param method to be called
     * @param event observed
     * @param <T> event type
     */
    <T> void registerObserver(Object instance, Method method, Class<T> event);

    /**
     * Unregister the given EventListener with the current context (provided).
     * 
     * @param event observed
     * @param listener to be unregistered
     * @param <T> event type
     */
    <T> void unregisterObserver(Class<T> event, EventListener<T> listener );

    /**
     * Unregister the given event from the current context (provided).
     *
     * @param instance to be unregistered
     * @param event observed
     * @param <T> event type
     */
    <T> void unregisterObserver(Object instance, Class<T> event);

    /**
     * Unregister all of the current context's event observers.
     */
    void clear();

    /**
     * Register the given EventListener to the contest and event class.
     *
     * @param context associated with event
     * @param event observed
     * @param listener to be triggered
     * @param <T> event type
     */
    <T> void registerObserver( Context context, Class<T> event, EventListener listener );

    /**
     * Registers given method with provided context and event.
     * 
     * @param context associated with event
     * @param instance to be called
     * @param method to be called
     * @param event observed
     */
    <T> void registerObserver(Context context, Object instance, Method method, Class<T> event);

    /**
     * Unregisters the provided event listener from the given event
     *
     * @param context associated with event
     * @param event observed
     * @param listener to be unregistered
     * @param <T> event type
     */
    <T> void unregisterObserver(Context context, Class<T> event, EventListener<T> listener );

    /**
     * Unregister all methods observing the given event from the provided context.
     *
     * @param context associated with event
     * @param instance to be unregistered
     * @param event observed
     */
    <T> void unregisterObserver(Context context, Object instance, Class<T> event);

    /**
     * Clears all observers of the given context.
     *
     * @param context associated with event
     */
    void clear( Context context );

    /**
     * Raises the event's class' event on the current context.  This event object is passed (if configured) to the
     * registered observer's method.
     *
     * @param event observed
     */
    void fire( Object event );

    /**
     * Raises the event's class' event on the given context.  This event object is passed (if configured) to the
     * registered observer's method.
     * 
     * @param context associated with event
     * @param event observed
     */
    void fire(Context context, Object event);
}
