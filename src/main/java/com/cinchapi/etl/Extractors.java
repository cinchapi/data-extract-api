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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import com.cinchapi.common.base.CheckedExceptions;
import com.cinchapi.common.base.Verify;

/**
 * Utilities and factories for {@link Extractor Extractors}.
 *
 * @author Jeff Nelson
 */
public final class Extractors {

    /**
     * Return an {@link Extractor} for a CSV {@code file} that contains column
     * headers in the first row. The {@link Extractor} will parse the data from
     * the CSV file as a collection of {@link Map mappings} where each
     * associates a column header (e.g. key) to the analogous raw value.
     * 
     * @param file
     * @return the {@link Extractor}
     */
    public static Extractor<Path> csv() {
        return file -> {
            Verify.thatArgument(file.toFile().exists(),
                    "Cannot extract dta from {} because it does not exist",
                    file);
            try {
                CSVParser parser = CSVParser.parse(file, StandardCharsets.UTF_8,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader());
                return parser.getRecords().stream()
                        .map(record -> record.toMap().entrySet().stream()
                                .collect(Collectors.toMap(e -> e.getKey(),
                                        e -> Object.class.cast(e.getValue()))))
                        .collect(Collectors.toList());

            }
            catch (IOException e) {
                throw CheckedExceptions.wrapAsRuntimeException(e);
            }
        };
    }

    private Extractors() {/* no-init */}

}
