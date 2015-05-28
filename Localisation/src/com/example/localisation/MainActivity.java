package com.example.localisation;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;
import android.support.v7.*;

/**
 *
 */
public class MainActivity extends Activity implements LocationListener {
  /*******************************************************/
  /** ATTRIBUTS.
 /*******************************************************/
  private LocationManager locationManager;
  private GoogleMap gMap;
  private Marker marker;

  /*******************************************************/
  /** METHODES / FONCTIONS.
 /*******************************************************/
  /**
  * {@inheritDoc}
  */
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      gMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
      marker = gMap.addMarker(new MarkerOptions().title("Vous �tes ici").position(new LatLng(0, 0)));
  }

  /**
  * {@inheritDoc}
  */
  @Override
  public void onResume() {
      super.onResume();

      //Obtention de la r�f�rence du service
      locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

      //Si le GPS est disponible, on s'y abonne
      if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
          abonnementGPS();
      }
  }

  /**
  * {@inheritDoc}
  */
  @Override
  public void onPause() {
      super.onPause();

      //On appelle la m�thode pour se d�sabonner
      desabonnementGPS();
  }

  /**
  * M�thode permettant de s'abonner � la localisation par GPS.
  */
  public void abonnementGPS() {
      //On s'abonne
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
  }

  /**
  * M�thode permettant de se d�sabonner de la localisation par GPS.
  */
  public void desabonnementGPS() {
      //Si le GPS est disponible, on s'y abonne
      locationManager.removeUpdates(this);
  }

  /**
  * {@inheritDoc}
  */
  @Override
  public void onLocationChanged(final Location location) {
      //On affiche dans un Toat la nouvelle Localisation
      final StringBuilder msg = new StringBuilder("lat : ");
      msg.append(location.getLatitude());
      msg.append( "; lng : ");
      msg.append(location.getLongitude());

      Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show();

      //Mise � jour des coordonn�es
      final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());      
      gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
      marker.setPosition(latLng);
  }

  /**
  * {@inheritDoc}
  */
  @Override
  public void onProviderDisabled(final String provider) {
      //Si le GPS est d�sactiv� on se d�sabonne
      if("gps".equals(provider)) {
          desabonnementGPS();
      }        
  }

  /**
  * {@inheritDoc}
  */
  @Override
  public void onProviderEnabled(final String provider) {
      //Si le GPS est activ� on s'abonne
      if("gps".equals(provider)) {
          abonnementGPS();
      }
  }

  /**
  * {@inheritDoc}
  */
  @Override
  public void onStatusChanged(final String provider, final int status, final Bundle extras) { }
}