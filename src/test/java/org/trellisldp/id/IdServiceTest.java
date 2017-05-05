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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.function.Supplier;

import org.trellisldp.spi.IdGeneratorService;
import org.apache.commons.rdf.api.IRI;
import org.apache.commons.rdf.api.RDF;
import org.apache.commons.rdf.simple.SimpleRDF;
import org.junit.Test;

/**
 * @author acoburn
 */
public class IdServiceTest {

    private static final RDF rdf = new SimpleRDF();

    @Test
    public void testSupplier() {
        final String prefix = "trellis:repository/";
        final Supplier<IRI> supplier = new IdSupplier(rdf.createIRI(prefix));
        final IRI id1 = supplier.get();
        final IRI id2 = supplier.get();

        assertTrue(id1.getIRIString().startsWith(prefix));
        assertTrue(id2.getIRIString().startsWith(prefix));
        assertFalse(id1.equals(id2));
    }

    @Test
    public void testGenerator() {
        final String prefix1 = "http://example.org/";
        final String prefix2 = "trellis:repository/a/b/c/";
        final IdGeneratorService svc = new IdGenerator();
        final Supplier<IRI> gen1 = svc.getGenerator(rdf.createIRI(prefix1));
        final Supplier<IRI> gen2 = svc.getGenerator(rdf.createIRI(prefix2));

        final IRI id1 = gen1.get();
        final IRI id2 = gen2.get();

        assertTrue(id1.getIRIString().startsWith(prefix1));
        assertFalse(id1.getIRIString().equals(prefix1));
        assertTrue(id2.getIRIString().startsWith(prefix2));
        assertFalse(id2.getIRIString().equals(prefix2));
    }
}