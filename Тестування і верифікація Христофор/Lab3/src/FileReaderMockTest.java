import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FileReaderMockTest {

    //Used mostly as a test stub
    @Mock
    private FileReaderClass freader;

    //A dummy object
    private Path path = mock(Path.class);

    @Spy
    FileReaderClass fc2 = new FileReaderClass();

    @Before
    public void setUp(){
        fc2 =mock(FileReaderClass.class);
        when(path.getPath()).thenReturn("123.log");
    }

    @Test
    public void testFileReaderWithTest(){
        when(freader.getText(path)).thenReturn("This is a content of my file...");
        assertEquals("Now fileReader should return a specific string",
                "This is a content of my file...", freader.getText(path));
    }

    @Test
    public void testEmptyFileReader(){
        assertNull("Empty fileReader should return null", freader.getText(path));
    }

    @Test
    public void testCollaborationWithVoidReadingMethod(){
        freader.getText(path);

        //verify that the method had been invoked with this parameter
        verify(freader).getText(path);
    }

    @Test
    public void testThatCollaborationHappenedWithWrongParams(){
        freader.getText(new Path("wrong path . x"));

        /*The method was invoked with wrong parameter
         * Check that it's never been called with the default param
         * */

        verify(freader, never()).getText(path);
    }

    @Test
    public void testThatCollaborationHappenedExactNumberOfTimes(){
        freader.getText(path);
        freader.getText(path);

        verify(freader, times(2)).getText(path);
    }

    @Test
    public void testAnotherCollaboratorWithTheSameParams(){
        freader.getText(path);

        /*The wrong instance was used with the same params
         * Check that main instance's never been called
         * */

        verify(fc2, never()).getText(path);
    }

    @Test
    public void testUsingSpy(){
        doReturn("another string").when(fc2).getText(path);
        assertThat(fc2.getText(path), is("another string"));
    }

    @Test(expected = Exception.class)
    public void testExceptions(){
        //doThrow(new IOException()).when(freader).getText(path);
        //freader.getText(path);
        doThrow(new IOException()).when(freader).readFile();
        when(freader.getText(path)).thenCallRealMethod();
    }//

    //Using partial mocks
    @Test
    public void testMethodInvocationFromAnotherMethod(){
        when(fc2.getText(path)).thenCallRealMethod();
        fc2.getText(path);
        verify(fc2).readFile();
    }
}