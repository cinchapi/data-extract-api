/*
 * Copyright (c) 2013-2019 Cinchapi Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cinchapi.etl;

import java.util.Map;

/**
 * An {@link Extractor} is a function that can pull data from a source and
 * represents it as a {@link Map mapping} from {@link String} (e.g. the
 * property/attribute key) to {@link Object} (e.g. the property/attribute
 * value).
 * 
 * @author Jeff Nelson
 * @param <T> - The data source type
 */
@FunctionalInterface
public interface Extractor<T> {

    /**
     * Extract the data from the {@code source} as a {@link Map mapping} from
     * {@link String} (e.g. the property/attribute key) to {@link Object} (e.g.
     * the property/attribute
     * value)
     * 
     * @param source
     * @return the extracted data
     */
    public Map<String, Object> extract(T source);

}
