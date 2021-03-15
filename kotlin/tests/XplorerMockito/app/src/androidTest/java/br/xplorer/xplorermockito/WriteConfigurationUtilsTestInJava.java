package br.xplorer.xplorermockito;

import android.content.Context;
import android.util.Log;

import br.xplorer.xplorermockito.utils.WriteConfigurationUtils;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class WriteConfigurationUtilsTestInJava {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    public Context context;

    @Mock
    public FileOutputStream fileOutputStream;

    @Test
    public void writeShouldWriteTwiceToFileSystem() {
        try {
            when(context.openFileOutput(anyString(), anyInt())).thenReturn(fileOutputStream);
            WriteConfigurationUtils.Companion.writeConfiguration(context
                    , "raw/output/output_test_write_twice.txt", new String[]{"teste 1", "teste 2"});

            verify(context, times(1)).openFileOutput(anyString(), anyInt());
            verify(fileOutputStream, atLeast(2)).write(any(byte[].class));
        } catch (Exception ex) {
            Log.e("WRITE_TWICE_TEST_FAIL", ex.getMessage());
            fail();
        }
    }
}
