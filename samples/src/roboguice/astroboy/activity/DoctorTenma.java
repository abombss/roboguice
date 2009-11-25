/*
 * Copyright 2009 Michael Burton
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package roboguice.astroboy.activity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

import java.util.Date;

import roboguice.activity.GuiceActivity;
import roboguice.astroboy.AstroboyModule;
import roboguice.astroboy.R;
import roboguice.astroboy.bean.DateExtraConverter;
import roboguice.astroboy.bean.DateTwiceExtraConverter;
import roboguice.astroboy.bean.Person;
import roboguice.astroboy.bean.PersonExtraConverter;
import roboguice.astroboy.bean.PersonFromNameExtraProvider;
import roboguice.astroboy.service.TalkingThing;
import roboguice.inject.ExtrasListener;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.inject.Inject;
import com.google.inject.internal.Nullable;

public class DoctorTenma extends GuiceActivity {
    // You can inject arbitrary View, String, and other types of resources.
    // See ResourceListener for details.
    @InjectResource(R.id.widget1)
    protected TextView          helloView;
    @InjectResource(R.string.hello)
    protected String            hello;

    /**
     * You can inject Extras from the intent that started this activity with
     * {@link InjectExtra}, this annotation is basically equivalent to the
     * following code : {@code
     * nameExtra=getIntent().getStringExtra("nameExtra");}
     * 
     * @see ExtrasListener
     */
    @InjectExtra("nameExtra")
    protected String            nameExtra;

    /**
     * The extra must exists when the activity is injected, unless you specify
     * {@code optional=true} in the {@link InjectExtra} annotation. If optional
     * is set to true and no extra is found, no value will be injected in the
     * field.
     */
    @InjectExtra(value = "optionalExtra", optional = true)
    protected Date              myDateExtra        = new Date(0);

    /**
     * The default behavior of the {@link InjectExtra} annotation is to forbid
     * null values. However, if you wish to allow injection of null values, you
     * should use the {@link Nullable} annotation. In the following example, the
     * extra "nullExtra" MUST exist, but CAN be null.
     */
    @InjectExtra("nullExtra")
    @Nullable
    protected Object            nullInjectedMember = new Object();

    /**
     * This example shows how to inject a bean converted from an extra value
     * using a provider. The String extra is converted into a Person. See
     * {@link PersonFromNameExtraProvider} to see how a Person is created. A
     * binding is actually done in {@link AstroboyModule}.<br />
     * <br />
     * The extra key is defined in the provider. This is useful if you want to
     * inject a bean created from multiple extra values (e.g. creating a person
     * from its name and age).
     */
    @Inject
    protected Person            personFromExtra;

    /**
     * This is a more flexible way to inject a bean : a converter is configured
     * and bound, and robo-guice uses this converter to create a Person from a
     * String extra. See {@link PersonExtraConverter} and {@link AstroboyModule}
     * .
     */
    @InjectExtra("nameExtra")
    protected Person            personFromConvertedExtra;

    /**
     * This date is injected using the converter {@link DateExtraConverter} that
     * converts a {@link Long} to a {@link Date}.
     */
    @InjectExtra("timestampExtra")
    protected Date              dateFromTimestampExtra;

    /**
     * This date is injected using the converter {@link DateTwiceExtraConverter}
     * that converts an {@link Integer} (doubled) to a {@link Date}.
     */
    @InjectExtra("timestampTwiceExtra")
    protected Date              dateFromTimestampTwiceExtra;

    // You can inject various useful android objects.
    // See GuiceApplication.configure to see what's available.
    @Inject
    protected SharedPreferences prefs;

    // Injecting a collaborator
    @Inject
    protected TalkingThing      talker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); // Injection doesn't happen until you
        // call setContentView()

        helloView.setText(hello + ", " + this.getClass().getSimpleName());

        assertEquals(prefs.getString("dummyPref", "la la la"), "la la la");
        assertNull(nullInjectedMember);
        assertEquals(myDateExtra, new Date(0));
        assertEquals(nameExtra, "Atom");
        assertEquals(personFromExtra.getName(), "Atom");
        assertEquals(personFromExtra.getAge().getTime(), 3000L);
        assertEquals(personFromConvertedExtra.getName(), "Atom");
        assertEquals(dateFromTimestampExtra.getTime(), 1000L);
        assertEquals(dateFromTimestampTwiceExtra.getTime(), 2000L);

        Log.d("DoctorTenma", talker.talk());

    }
}
