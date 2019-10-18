package com.kirinsidea.utils;

import android.os.Environment;
import android.widget.Toast;

import com.kirinsidea.App;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class FileUtil {
    /*TODO sftp 통신으로 변경, jsch 라이브러리 사용*/

    public static String writeFile(Source source) {
        String state = Environment.getExternalStorageState();
        String filepath = App.instance().getContext().getExternalFilesDir(null).getAbsolutePath();
        String fileName = UUID.randomUUID().toString();
        String path = filepath + "/" + fileName;

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File file = new File(path);
            try {
                BufferedSink bufferedSink = Okio.buffer(Okio.sink(file));
                bufferedSink.writeAll(source);
                bufferedSink.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return path;
        } else {
            Toast.makeText(App.instance().getContext(),"파일을 저장할 수 없습니다.",Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
