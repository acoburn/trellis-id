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

import org.trellisldp.spi.IdentifierService;
import org.junit.Test;

/**
 * @author acoburn
 */
public class IdServiceTest {

    @Test
    public void testSupplier() {
        final String prefix = "trellis:repository/";
        final Supplier<String> supplier = new IdSupplier(prefix);
        final String id1 = supplier.get();
        final String id2 = supplier.get();

        assertTrue(id1.startsWith(prefix));
        assertTrue(id2.startsWith(prefix));
        assertFalse(id1.equals(id2));
    }

    @Test
    public void testGenerator() {
        final String prefix1 = "http://example.org/";
        final String prefix2 = "trellis:repository/a/b/c/";
        final IdentifierService svc = new UUIDGenerator();
        final Supplier<String> gen1 = svc.getSupplier(prefix1);
        final Supplier<String> gen2 = svc.getSupplier(prefix2);

        final String id1 = gen1.get();
        final String id2 = gen2.get();

        assertTrue(id1.startsWith(prefix1));
        assertFalse(id1.equals(prefix1));
        assertTrue(id2.startsWith(prefix2));
        assertFalse(id2.equals(prefix2));
    }
}
