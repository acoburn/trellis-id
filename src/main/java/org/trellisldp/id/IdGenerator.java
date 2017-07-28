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

import java.util.function.Supplier;

import org.trellisldp.spi.IdSupplierService;

/**
 * The IdGeneratorService provides a mechanism for creating new identifiers.
 *
 * @author acoburn
 */
public class IdGenerator implements IdSupplierService {

    @Override
    public Supplier<String> getSupplier(final String prefix) {
        return new IdSupplier(prefix);
    }

    @Override
    public Supplier<String> getSupplier() {
        return getSupplier("");
    }
}
