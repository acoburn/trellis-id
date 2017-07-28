/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.trellisldp.id;

import static java.util.UUID.randomUUID;
import static java.util.stream.IntStream.rangeClosed;

import java.util.StringJoiner;
import java.util.function.Supplier;

/**
 * A custom Supplier implementation for generating identifiers.
 *
 * @author acoburn
 */
class IdSupplier implements Supplier<String> {

    private String prefix;
    private Integer levels;
    private Integer length;

    /**
     * Create a new IdSupplier with a configurable prefix
     * @param prefix a prefix used for the newly generated IRIs
     * @param levels the number o levels of hierarchy to add
     * @param length the length of each hierarchy segment
     */
    public IdSupplier(final String prefix, final Integer levels, final Integer length) {
        this.prefix = prefix;
        this.levels = levels;
        this.length = length;
    }

    @Override
    public String get() {
        final String id = randomUUID().toString();
        final String nodash = id.replaceAll("-", "");
        final StringJoiner joiner = new StringJoiner("/");
        rangeClosed(0, levels - 1).forEach(x -> joiner.add(nodash.substring(x * length, (x + 1) * length)));
        joiner.add(id);

        return prefix + joiner;
    }
}
