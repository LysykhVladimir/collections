package ru.lysykh.collections.ArrayList;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

import static org.junit.Assert.*;
/**
 * Created by Владимир on 06.08.2017.
 */
public class ArrayListTest {
    private ArrayList <Integer> testInstance;
    private ListIterator<Integer> listIterator;

    @Before
    public void setUp() throws Exception {
        testInstance = new ArrayList<>();
        listIterator = testInstance.listIterator();
    }

    @Test
    public void testSizeWhenSizeIs0() throws Exception {
        assertEquals(0, testInstance.size());
    }

    @Test
    public void testIsEmptyWhenEmpty() throws Exception {
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testToArrayWhenInputArrayHaveSizeOne() throws Exception {

        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[1];

        final Integer[] result = testInstance.toArray(input);
        assertNotEquals(input, result);
        assertEquals((Integer)1, result[0]);
        assertEquals((Integer)2, result[1]);
        assertEquals((Integer)3, result[2]);
        assertEquals(3, result.length);
    }

    @Test
    public void testToArrayWhenInputArrayHaveCorrectSize() throws Exception {

        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[3];

        final Integer[] result = testInstance.toArray(input);
        assertArrayEquals(input, result);
    }

    @Test
    public void testContains() throws Exception {

        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        assertTrue(testInstance.contains(3));
        assertFalse(testInstance.contains(0));
    }

    @Test
    public void testAdd() throws Exception {

        testInstance.add(1);
        testInstance.add(1);

        assertEquals(2, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveFirstElement() throws Exception {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.remove(1);
        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveLastElement() throws Exception {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.remove(1);
        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testContainsAll() throws Exception {

        final Collection<Integer> testInstance2 = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(1);

        assertTrue(testInstance.containsAll(testInstance2));
    }

    @Test
    public void testAddAll() throws Exception {

        testInstance.add(1);
        testInstance.add(2);

        testInstance.add(3);
        testInstance.add(4);

        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(4));
    }

    @Test
    public void testRemoveAll() throws Exception {

        final Collection<Integer> testInstance2 = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(3);

        testInstance.removeAll(testInstance2);

        assertEquals(1, testInstance.size());
        assertTrue(testInstance.contains(1));
    }

    @Test
    public void testRetainAll() throws Exception {

        final Collection<Integer> testInstance2 = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(3);

        testInstance.retainAll(testInstance2);

        assertEquals(1, testInstance.size());
        assertTrue(testInstance.contains(2));
    }

    @Test
    public void testClear() throws Exception {

        testInstance.add(1);
        testInstance.add(1);

        testInstance.clear();

        assertTrue(testInstance.isEmpty());
        assertEquals(0, testInstance.size());
    }

    @Test
    public void testRemoveBeforeNext() throws Exception {

        testInstance.add(2);
        final Iterator<Integer> iter = testInstance.iterator();
        try {
            iter.remove();
            fail("remove do not throw the Exception when called before next");
        } catch (final IllegalStateException e) {}
    }

    @Test
    public void testNextOnEmptyCollection() throws Exception {

        testInstance.add(1);
        testInstance.add(2);

        final Iterator<Integer> iter = testInstance.iterator();
        iter.next();
        iter.remove();
        iter.next();
        iter.remove();
        try {
            iter.next();
            fail("next do not throw the Exception when no more ellements");
        } catch (final java.util.NoSuchElementException e) {}
    }

    @Test
    public void testHasPreviousWhenIteratorAtTheEndOfTheCollection() {

        testInstance.add(1);
        testInstance.add(2);
        assertFalse(listIterator.hasPrevious());
        listIterator.next();
        assertTrue(listIterator.hasPrevious());
    }

    @Test
    public void testPreviousIndexWhenItEqualsTo1() {

        testInstance.add(1);
        testInstance.add(1);
        listIterator.next();
        listIterator.next();

        assertEquals(1, listIterator.previousIndex());
    }

    @Test
    public void testAddInIteratorLastIsNotSet() {

        listIterator.add(1);
        listIterator.add(2);
        listIterator.add(3);
        try {
            listIterator.set(222);
            fail("set method can not be called after add (E). Wrong last element.");
        } catch (final IllegalStateException e){}

        listIterator.add(4);
    }

    @Test
    public void testAddInIteratorAfterNext() {

        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        listIterator.next();
        listIterator.next();
        assertSame("previousIndex wrong",1, listIterator.previousIndex());
        assertSame("nextIndex wrong",2, listIterator.nextIndex());
        assertSame("previous element ",2, listIterator.previous());
        assertSame(1, testInstance.get(0));
        assertEquals("size",4, testInstance.size());
        listIterator.add(9);
        assertSame("previous element ",9, listIterator.previous());
        listIterator.add(10);
        assertEquals("size",6, testInstance.size());
        assertSame("previousIndex wrong",1, listIterator.previousIndex());
        assertSame("nextIndex wrong",2, listIterator.nextIndex());
        assertSame("previous element ",10, listIterator.previous());
    }

    @Test
    public void testAddInIteratorWhenEmptyList() {

        listIterator.add(1);
        listIterator.add(2);
        assertSame("previousIndex ",1, listIterator.previousIndex());
        assertSame("previous element ", 2, listIterator.previous());
        assertSame("First element ", 1, testInstance.get(0));
        assertEquals("size",2, testInstance.size());
    }

    @Test
    public void testAddInIteratorWhenIsNotEmptyListToTheBeginning() {


        testInstance.add(0);
        testInstance.add(0);
        testInstance.add(0);
        listIterator.add(1);
        assertSame("previousIndex ",0, listIterator.previousIndex());
        assertSame("nextIndex ",1, listIterator.nextIndex());
        assertSame("previous element ", 1, listIterator.previous());
        assertSame("Get first element ",1, testInstance.get(0));
        assertEquals("size",4, testInstance.size());
    }


    @Test
    public void testSetWhenNeitherNextNorPreviousHaveBeenCalled() {
        testInstance.add(1);
        try {
            listIterator.set(null);
            fail("set method do not throw IllegalStateException the if neither next nor previous have been called");
        } catch (final IllegalStateException e){}
        listIterator.add(2);
        try {
            listIterator.set(null);
            fail("set method do not throw IllegalStateException the if neither next nor previous have been called");
        } catch (final IllegalStateException e){}
    }

    @Test
    public void testSet() {
        testInstance.add(1);
        listIterator.next();
        listIterator.set(2);
        assertEquals((Integer)2, testInstance.get(0));
    }

    @Test
    public void testPreviouseOnCollectionWithOneElement() {
        testInstance.add(1);
        final Integer next = listIterator.next();
        final Integer previous = listIterator.previous();
        assertEquals(next, previous);
    }
    @Test
    public void testPreviouseIndex() {
        testInstance.add(1);
        listIterator.next();
        assertEquals(0, listIterator.previousIndex());
    }

    @Test
    public void testPreviousIndexWhenEmptyCollection() {
        assertEquals(-1, listIterator.previousIndex());
    }

    @Test
    public void testPreviouseWhenEmptyCollection() {

        try {
            listIterator.previous();
            fail("list iterator do not throw the Exception when called previous method on empty collection");
        } catch (final java.util.NoSuchElementException e) {}
    }

    @Test
    public void testHasPreviouseWhenEmptyCollection() {
        assertFalse(listIterator.hasPrevious());
    }

    @Test
    public void testRemoveTwoTimeInTheRow() throws Exception {
        testInstance.add(1);
        testInstance.add(2);

        final Iterator<Integer> iter = testInstance.iterator();
        iter.next();
        iter.remove();
        assertEquals("Expected collection size is 1, however actual is not", 1, testInstance.size());
        try {
            iter.remove();
            fail("remove do not throw the Exception when called twice");
        } catch (final IllegalStateException e) {}
    }

    @Test

    public void testSubList() throws Exception {
        final ArrayList<Integer> testInstance = new ArrayList<>();

        testInstance.add(1);
        testInstance.add(2);

        Collection<Integer> subList = testInstance.subList(0,1);
        assertEquals("Expected collection size is 1, however actual is not", 1, subList.size());
        assertEquals("No 2 expected,", false, subList.contains(2));

        try {
            testInstance.subList(-1,2);
            fail("subList do not throw the Exception when called with out bounds indexes");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            testInstance.subList(2,1);
            fail("subList do not throw the Exception when called with wrong order indexes");
        } catch (final IllegalArgumentException e) {}
    }

    @Test
    public void testAddToEnd() {
        final ArrayList<Integer> testInstance = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(3, 4);

        final Iterator<Integer> iter = testInstance.listIterator(3);
        assertEquals("Expected iterator to have next after adding new element", true, iter.hasNext());
        assertEquals("Expected 4 but it's not", 4, (int)iter.next());

        try {
            testInstance.add(-1,4);
            fail("add do not throw the Exception when called with out bounds indexes");
        } catch (final IndexOutOfBoundsException e) {}

        try {
            testInstance.add(5,4);
            fail("add do not throw the Exception when called with out bounds indexes");
        } catch (final IndexOutOfBoundsException e) {}
    }

    @Test

    public void testAddToMiddle() {
        final ArrayList<Integer> testInstance = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(4);
        testInstance.add(2, 3);

        final Iterator<Integer> iter = testInstance.listIterator(2);
        assertEquals("Expected iterator to have next after adding new element", true, iter.hasNext());
        assertEquals("Expected 4 but it's not", 3, (int)iter.next());
    }

    @Test
    public void testAddAllToEnd() {

        final ArrayList<Integer> testInstance = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        final ArrayList<Integer> testInstance2 = new ArrayList<>();
        testInstance2.add(1);
        testInstance2.add(2);
        testInstance2.add(3);
        testInstance2.add(4);
        testInstance.addAll(4, testInstance2);

        assertEquals("Expected new testInstance size to be 8", 8, testInstance.size());
        assertEquals("Expected 5-th element of new List to be 1 but it's not", 1, (int)testInstance.get(4));

        final ArrayList<Integer> testInstance3 = new ArrayList<>();
        try {
            testInstance.addAll(1,testInstance3);
            fail("addAll do not throw the Exception when called with null List");
        } catch (final NullPointerException e) {}
    }

    @Test
    public void testAddAllToEndEnoughSize() {
        final ArrayList<Integer> testInstance = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);

        final ArrayList<Integer> testInstance2 = new ArrayList<>();
        testInstance2.add(1);
        testInstance2.add(2);
        testInstance.addAll(5, testInstance2);

        assertEquals("Expected new testInstance size to be 7", 7, testInstance.size());
        assertEquals("Expected 6-th element of new List to be 1 but it's not", 1, (int)testInstance.get(5));
    }

    @Test
    public void testAddAllToMiddle() {
        final ArrayList<Integer> testInstance = new ArrayList<>();

        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        final ArrayList<Integer> testInstance2 = new ArrayList<>();
        testInstance2.add(1);
        testInstance2.add(2);
        testInstance2.add(3);
        testInstance2.add(4);
        testInstance.addAll(2, testInstance2);

        assertEquals("Expected new testInstance size to be 8", 8, testInstance.size());
        assertEquals("Expected 3-th element of new List to be 1 but it's not", 1, (int)testInstance.get(2));
    }

    @Test

    public void testAddAllToMiddleEnoughSize() {

        final ArrayList<Integer> testInstance = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);

        final ArrayList<Integer> testInstance2 = new ArrayList<>();

        testInstance2.add(1);
        testInstance2.add(2);
        testInstance.addAll(2, testInstance2);

        assertEquals("Expected new testInstance size to be 7", 7, testInstance.size());
        assertEquals("Expected 3-th element of new List to be 1 but it's not", 1, (int)testInstance.get(2));
    }

    @Test

    public void testLastIndexOf() {

        final ArrayList<Integer> testInstance = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(3);
        testInstance.add(4);

        assertEquals("Expected index to be 3", 3, testInstance.lastIndexOf(3));
        assertEquals("Expected to see -1, but it's not", -1, testInstance.lastIndexOf(0));
    }

    @Test

    public void testIndexOf() {

        final ArrayList<Integer> testInstance = new ArrayList<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(3);
        testInstance.add(4);

        assertEquals("Expected index to be 2", 2, testInstance.indexOf(3));
        assertEquals("Expected to see -1, but it's not", -1, testInstance.lastIndexOf(0));
    }
}