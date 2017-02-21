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
package edu.amherst.acdc.trellis.id;

import static java.util.Objects.requireNonNull;
import static java.util.UUID.randomUUID;

import java.util.ServiceLoader;
import java.util.function.Supplier;

import org.apache.commons.rdf.api.IRI;
import org.apache.commons.rdf.api.RDF;

/**
 * A custom Supplier implementation for generating IRI values.
 *
 * @author acoburn
 */
class IdSupplier implements Supplier<IRI> {

    private static final ServiceLoader<RDF> rdfLoader = ServiceLoader.load(RDF.class);

    private static RDF getInstance() {
        for (final RDF impl : rdfLoader) {
            return impl;
        }
        return null;
    }

    private static final RDF rdf = getInstance();

    private String prefix;

    /**
     * Create a new IdSupplier with a configurable prefix
     * @param prefix a prefix used for the newly generated IRIs
     */
    public IdSupplier(final IRI prefix) {
        requireNonNull(prefix, "The Id prefix may not be null!");
        this.prefix = prefix.getIRIString();
    }

    @Override
    public IRI get() {
        return rdf.createIRI(prefix + randomUUID());
    }
}
