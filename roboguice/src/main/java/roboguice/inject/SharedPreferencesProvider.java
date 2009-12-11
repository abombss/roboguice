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

import com.google.inject.Inject;
import com.google.inject.Provider;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * @author Mike Burton
 */
public class SharedPreferencesProvider implements Provider<SharedPreferences> {
    protected static final String DEFAULT = "default";

    @Inject(optional = true)
    @SharedPreferencesName
    protected String preferencesName = DEFAULT;

    @Inject
    protected Provider<Context> contextProvider;

    public SharedPreferencesProvider() {
    }

    public SharedPreferencesProvider(String preferencesName, Provider<Context> contextProvider) {
        this.preferencesName = preferencesName;
        this.contextProvider = contextProvider;
    }

    public SharedPreferences get() {
        return contextProvider.get().getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
    }
}
