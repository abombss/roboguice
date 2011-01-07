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
package roboguice.inject;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 *
 * @author Mike Burton
 * @author Pierre-Yves Ricau (py.ricau+roboguice@gmail.com)
 */
public class SharedPreferencesProvider implements Provider<SharedPreferences> {
    protected static final String DEFAULT = "default";

    protected String preferencesName;

    @Inject protected Provider<Context> contextProvider;

    public SharedPreferencesProvider() {
        preferencesName = DEFAULT;
    }

    @Inject
    public SharedPreferencesProvider(PreferencesNameHolder preferencesNameHolder) {
        preferencesName = preferencesNameHolder.value;
    }

    public SharedPreferencesProvider(String preferencesName) {
        this.preferencesName = preferencesName;
    }

    public SharedPreferences get() {
        return contextProvider.get().getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
    }

    // http://code.google.com/p/google-guice/wiki/FrequentlyAskedQuestions => How can I inject optional parameters into a constructor?
    public static class PreferencesNameHolder {
        @Inject(optional = true)
        @SharedPreferencesName
        protected String value = DEFAULT;
    }

}
