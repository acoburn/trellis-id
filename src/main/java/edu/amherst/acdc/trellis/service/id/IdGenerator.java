/*
 * Copyright Amherst College
 *
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
package edu.amherst.acdc.trellis.service.id;

import java.util.function.Supplier;

import edu.amherst.acdc.trellis.spi.IdGeneratorService;
import org.apache.commons.rdf.api.IRI;

/**
 * The IdGeneratorService provides a mechanism for creating new identifiers.
 *
 * @author acoburn
 */
public class IdGenerator implements IdGeneratorService {

    @Override
    public Supplier<IRI> getGenerator(final IRI prefix) {
        return new IdSupplier(prefix);
    }
}
