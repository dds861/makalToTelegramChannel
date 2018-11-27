import Presenter.IPresenter;
import Presenter.Presenter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException, InterruptedException {


        IPresenter iPresenter = new Presenter();
        iPresenter.onStartSendingData();

    }



}