package es.unican.is2.conjuntoordenado;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConjuntoOrdenadoTest {

    private ConjuntoOrdenado<Integer> sut;

    @BeforeEach
    public void setUp() {
        sut = new ConjuntoOrdenado<>();
    }

    @Test
    public void testAddElementoValidoEnVacio() {
        assertTrue(sut.add(5));
        assertEquals(1, sut.size());
        assertEquals(5, sut.get(0));
    }

    @Test
    public void testAddElementoDuplicado() {
        sut.add(5);
        assertFalse(sut.add(5));   
    }

    @Test
    public void testAddElementoNull() {
        assertThrows(NullPointerException.class, () -> sut.add(null));
    }

    @Test
    public void testGetPrimerElemento() {
        sut.add(3);
        sut.add(7);
        sut.add(10);
        assertEquals(3, sut.get(0));
    }

    @Test
    public void testGetUltimoElemento() {
        sut.add(3);
        sut.add(7);
        sut.add(10);
        assertEquals(10, sut.get(sut.size() - 1));
    }

    @Test
    public void testGetIndiceNegativo() {
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-1));
    }

    @Test
    public void testGetIndiceFueraDeRango() {
        sut.add(1);
        sut.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(2));
    }

    @Test
    public void testRemovePrimerElemento() {
        sut.add(3);
        sut.add(7);
        sut.add(10);
        assertEquals(3, sut.remove(0));
        assertEquals(2, sut.size());
    }

    @Test
    public void testRemoveUltimoElemento() {
        sut.add(3);
        sut.add(7);
        sut.add(10);
        assertEquals(10, sut.remove(sut.size() - 1));
        assertEquals(2, sut.size());
    }

    @Test
    public void testRemoveIndiceNegativo() {
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(-1));
    }

    @Test
    public void testRemoveIndiceFueraDeRango() {
        sut.add(1);
        sut.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(2));
    }

    @Test
    public void testSizeVacio() {
        assertEquals(0, sut.size());
    }

    @Test
    public void testSizeConElementos() {
        sut.add(1);
        sut.add(2);
        sut.add(3);
        assertEquals(3, sut.size());
    }


    @Test
    public void testClear() {
        sut.add(1);
        sut.add(2);
        sut.add(3);
        sut.clear();
        assertEquals(0, sut.size());
    }

    @Test
    public void testAddElementoIntermedio() {
        sut.add(3);
        sut.add(7);
        sut.add(10);

        sut.add(5);  

        assertEquals(3, sut.get(0));
        assertEquals(5, sut.get(1));
        assertEquals(7, sut.get(2));
        assertEquals(10, sut.get(3));
    }

}
