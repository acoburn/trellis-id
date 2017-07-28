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

import static java.util.Objects.requireNonNull;

import java.util.function.Supplier;

import org.trellisldp.spi.IdentifierService;

/**
 * The IdentifierService provides a mechanism for creating new identifiers.
 *
 * @author acoburn
 */
public class UUIDGenerator implements IdentifierService {

    private Integer levels;
    private Integer length;

    /**
     * Create a UUID Generator
     */
    public UUIDGenerator() {
        this(0, 0);
    }

    /**
     * Create a UUID Generator with hierarchy
     * @param levels the level of hierarchy to create
     * @param length the length of each hierarchical segment
     */
    public UUIDGenerator(final Integer levels, final Integer length) {
        this.levels = levels;
        this.length = length;
    }

    @Override
    public Supplier<String> getSupplier(final String prefix) {
        requireNonNull(prefix, "The Id prefix may not be null!");
        return new IdSupplier(prefix, levels, length);
    }

    @Override
    public Supplier<String> getSupplier() {
        return getSupplier("");
    }
}
