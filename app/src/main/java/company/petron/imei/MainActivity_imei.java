package company.petron.imei;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.startapp.android.publish.ads.nativead.NativeAdDetails;
import com.startapp.android.publish.ads.nativead.NativeAdPreferences;
import com.startapp.android.publish.ads.nativead.StartAppNativeAd;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppAd.AdMode;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.startapp.android.publish.adsCommon.VideoListener;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;


public class MainActivity_imei extends AppCompatActivity {

    TelephonyManager manager;
    TextView txtmensaje;
    TextView txtreimei;
    TextView txtnombreop;
    TextView txtnumop;
    TextView txtvsv;
    TextView txtsnsim;
    TextView txtpais;
    TextView txtidabonado;
    private StartAppAd startAppAd = new StartAppAd(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, "101423750", "203550305", true);
        setContentView(R.layout.activity_main_activity_imei);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        txtmensaje = (TextView) findViewById(R.id.txtmensaje);
        txtreimei = (TextView) findViewById(R.id.txtreimei);
        txtnombreop = (TextView) findViewById(R.id.txtrenombreop);
        txtnumop = (TextView) findViewById(R.id.txtrenumop);
        txtvsv = (TextView) findViewById(R.id.txtrevsv);
        txtsnsim = (TextView) findViewById(R.id.txtresnsim);
        txtpais = (TextView) findViewById(R.id.txtrepais);
        txtidabonado = (TextView) findViewById(R.id.txtreidabonado);

         manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);


            StringBuilder builder = new StringBuilder();

        builder.append("Imei : ").append(manager.getDeviceId()).append("\n");
        builder.append("Nombre Operador : ").append(manager.getNetworkOperatorName()).append("\n");
        builder.append("Número del operador : ").append(manager.getNetworkOperator()).append("\n");
        builder.append("Versión de software del dispositivo: ").append(manager.getDeviceSoftwareVersion()).append("\n");
        builder.append("Número de serie SIM : ").append(manager.getSimSerialNumber()).append("\n");
        builder.append("Código pais : ").append(manager.getNetworkCountryIso()).append("\n");
        builder.append("ID de abonado : ").append(manager.getSubscriberId()).append("\n");




        //txtmensaje.setText(builder.toString());

        this.txtreimei.setText((manager.getDeviceId()));
        this.txtnombreop.setText((manager.getNetworkOperatorName()));
        this.txtnumop.setText((manager.getNetworkOperator()));
        this.txtvsv.setText((manager.getDeviceSoftwareVersion()));
        this.txtsnsim.setText((manager.getSimSerialNumber()));
        this.txtpais.setText((manager.getNetworkCountryIso()));
        this.txtidabonado.setText((manager.getSubscriberId()));




    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();



        //poner en marcha la publicidad al salir
        MainActivity_imei.this.startAppAd.showAd();
        MainActivity_imei.this.startAppAd.loadAd();


    }
    @Override
    public void onResume() {
        super.onResume();
        startAppAd.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        startAppAd.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_imei, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ayuda:
                //boton ayuda
                //+ Html.fromHtml("<br />")+ nos da un dalto de linea
               txtmensaje.setText("Imei: Número único del terminal"+ Html.fromHtml("<br />")+"Operador: compañia telefónica"
                       + Html.fromHtml("<br />")+"Num op: Número compañia telefónica"+ Html.fromHtml("<br />")+"VSW: Versión del software"
                       + Html.fromHtml("<br />")+"SN SIM: Número de serie SIM"+ Html.fromHtml("<br />")+"País: Código del país"
                       + Html.fromHtml("<br />")+"Id abonado: Número de identificación del abonado");
                return true;
            case R.id.votar:
                //boton votar
                Uri uriUrl = Uri.parse("https://play.google.com/store/apps/details?id=company.petron.imei");
                Intent intent = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                //botón salir
                MainActivity_imei.this.startAppAd.showAd();
                MainActivity_imei.this.startAppAd.loadAd();
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
