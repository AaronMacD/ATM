package Project1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*

   Here is a sample test class that contains several test cases.
   You are required to:

        1. After running your test cases, 100 percent coverage of
        	ChangeMaker.java
        2. Have at least two @Test methods for each method in
        	ChangeMaker.java class
        3. Test every Method in ChangeMaker (requirement 1 should cover this
        	requirement as well)
        4. Make sure you have tested possible thing to ensure complete and
        	correct functionality of ChangeMaker.
        5. Suggestion: Trade test files with your classmate.
 */
public class ChangeMakerTest {

    @Before
    public void initial() {
        ChangeMaker.setQuartersAvail(true);
        ChangeMaker.setDimesAvail(true);
        ChangeMaker.setNickelsAvail(true);
        ChangeMaker.setPenniesAvail(true);
    }

    @Test
    public void testConstructor1a() {
        ChangeMaker s1 = new ChangeMaker(2.34);
        assertEquals(2.34, s1.getAmount(),0);

        ChangeMaker s2 = new ChangeMaker(34);
        assertEquals(34, s2.getAmount(),0);
    }

    @Test
    public void testConstructor1b(){
        ChangeMaker s1 = new ChangeMaker(1000000);
        assertEquals(1000000, s1.getAmount(),0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2a() {
        new ChangeMaker(-10.3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2b() {new ChangeMaker(0);}

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2() {
        new ChangeMaker(-10);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor3() {
        new ChangeMaker(10.123);
    }
    @Test
    public void testConstructor4(){
        ChangeMaker s1 = new ChangeMaker(602);
        ChangeMaker s2 = new ChangeMaker(s1);
    }

    @Test
    public void testGetAmount(){
        ChangeMaker s1 = new ChangeMaker(100);
        assertEquals(100, s1.getAmount(),0);

        ChangeMaker s2 = new ChangeMaker(5000);
        assertEquals(5000, s2.getAmount(),0);
    }
    @Test
    public void testSetAmount(){
        ChangeMaker s1 = new ChangeMaker(100);
        assertEquals(100, s1.getAmount(),0);
        s1.setAmount(5000);
        assertEquals(5000, s1.getAmount(),0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetAmount2(){
        ChangeMaker s1 = new ChangeMaker(100);
        s1.setAmount(-200);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetAmount3(){
        ChangeMaker s1 = new ChangeMaker(100);
        s1.setAmount(100.001512);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetAmount4(){
        ChangeMaker s1 = new ChangeMaker(100);
        s1.setAmount(0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetAmount5(){
        ChangeMaker s1 = new ChangeMaker(100);
        s1.setAmount(1.1E15);
    }

    @Test
    public void testGetSetQuartersAvail(){
        ChangeMaker.setQuartersAvail(false);
        assertFalse(ChangeMaker.getQuartersAvail());
        
        ChangeMaker.setQuartersAvail(true);
        assertTrue(ChangeMaker.getQuartersAvail());
    }

    @Test
    public void testGetSetDimesAvail(){
        ChangeMaker.setDimesAvail(false);
        assertFalse(ChangeMaker.getDimesAvail());

        ChangeMaker.setDimesAvail(true);
        assertTrue(ChangeMaker.getDimesAvail());
    }

    @Test
    public void testGetSetNickelsAvail(){
        ChangeMaker.setNickelsAvail(false);
        assertFalse(ChangeMaker.getNickelsAvail());

        ChangeMaker.setNickelsAvail(true);
        assertTrue(ChangeMaker.getNickelsAvail());
    }

    @Test
    public void testGetSetPenniesAvail(){
        ChangeMaker.setPenniesAvail(false);
        assertFalse(ChangeMaker.getPenniesAvail());

        ChangeMaker.setPenniesAvail(true);
        assertTrue(ChangeMaker.getPenniesAvail());
    }
    
    @Test
    public void testEquals() {
        ChangeMaker s1 = new ChangeMaker(2);
        ChangeMaker s2 = new ChangeMaker(2);
        assertEquals(s1,s2);
        assertTrue(s1.equals(s2));

        s1.setAmount(5);
        assertFalse(s1.equals(s2));

        ChangeBag s3 = new ChangeBag(1, 1, 1, 1);
        assertFalse(s1.equals(s3));
    }

    @Test
    public void takeout1() {
        ChangeMaker s1 = new ChangeMaker(.44);
        s1.takeOut(.44);
        assertEquals(0,s1.getAmount(),0);

        s1 = new ChangeMaker(2.00);
        s1.takeOut(.44);
        assertEquals(1.56,s1.getAmount(),0);

        s1 = new ChangeMaker(10);
        s1.takeOut(.5);
        assertEquals(9.5,s1.getAmount(),0);

        s1 = new ChangeMaker(.44);
        s1.takeOut(0.0);
        assertEquals(.44,s1.getAmount(),0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void takeout2() {
        ChangeMaker s1 = new ChangeMaker(0);
        s1.takeOut(.44);
    }
    @Test(expected = IllegalArgumentException.class)
    public void takeout3() {
        ChangeMaker s1 = new ChangeMaker(100);
        s1.takeOut(.443);
        assert
    }

    @Test(expected = IllegalArgumentException.class)
    public void takeout4() {
        ChangeMaker s1 = new ChangeMaker(100);
        s1.takeOut(101.0);
    }

    @Test
    public void takeout12 () {
        ChangeMaker s1 = new ChangeMaker(4.4);
        ChangeMaker.setQuartersAvail(false);
        ChangeMaker.setNickelsAvail(false);
        s1.takeOut(.25);
    }

    @Test
    public void takeout13(){
        ChangeMaker s1 = new ChangeMaker(50);
        ChangeBag cb = s1.takeOut(1.39);
        assertEquals(5, cb.getQuarters());
        assertEquals(1, cb.getDimes());
        assertEquals(0, cb.getNickels());
        assertEquals(4, cb.getPennies());
    }

    @Test
    public void testCompareTo() {
        ChangeMaker s1 = new ChangeMaker(2);
        ChangeMaker s2 = new ChangeMaker(2);
        ChangeMaker s3 = new ChangeMaker(1);

        assertTrue(s1.compareTo(s2) == 0);
        assertTrue(s1.compareTo(s3) > 0);
        assertTrue(s3.compareTo(s1) < 0);
    }

    @Test
    public void testLoadMachine1 () {
        ChangeMaker s1 = new ChangeMaker();
        s1.loadMachine(123.12);
        assertTrue(s1.getAmount() == 123.12);
    }

    @Test
    public void testSaveLoad1 () {
        ChangeMaker s1 = new ChangeMaker(4.4);
        s1.save("file.txt");
        s1.setAmount(5.75);
        s1.load("file.txt");
        assertEquals(4.4, s1.getAmount(), 0);

        ChangeMaker s2 = new ChangeMaker(1);
        s2.save("file2.txt");
        s2.load("file.txt");
        s1.load("file2.txt");
        assertEquals(1,s1.getAmount(),0);
        assertEquals(4.4,s2.getAmount(),0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSaveLoad2(){
        ChangeMaker s1 = new ChangeMaker(100);
        s1.load("file3.txt");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSaveLoad3(){
        ChangeMaker s1 = new ChangeMaker(100.99);
        s1.load("squigly.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveLoad4(){
        ChangeMaker s1 = new ChangeMaker(100.25);
        s1.save("file / 5.txt");
    }

    @Test
    public void testToString(){
        ChangeMaker s1 = new ChangeMaker(100);
        ChangeMaker s2 = new ChangeMaker(100);
        assertEquals(s1.toString(), s1.toString());

        s2.setAmount(105);
        assertNotEquals(s1.toString(),s2.toString());
    }



}
