import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

public class Main extends Thread {



    public static void main(String[] args) {
        String ACCESS_TOKEN =
                "sl.Amrh4VydQxPBDC7Ol-tSQqD5E4I-tGKlSPLFE5IeTzp7N-KTybpby86ay-DhH8BnsrG21jFb5GTju9bSnb2OOxGJNN-gQDaTR_w2w-gLZcyog3ZhtrQb-M3jwvUccPOWBhO8dLA";

        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);


        MyThread thread1 = new MyThread(client);

        thread1.start();
    }

}
