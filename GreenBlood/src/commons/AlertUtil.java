package commons;

import android.app.AlertDialog;
import android.content.Context;

public class AlertUtil {
    public static void showSimpleDialog(Context ctx, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setMessage(msg);
        builder.show();
    }
}
