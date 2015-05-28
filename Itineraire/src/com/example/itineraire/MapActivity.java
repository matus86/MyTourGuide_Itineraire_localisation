package com.example.itineraire;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.*;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;


/**
 * MapActivity
 * @author Ludovic
 */
public class MapActivity extends Activity {
  private GoogleMap gMap;

  /**
  * {@inheritDoc}
  */
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_map);

      //On r�cup�re les composants graphiques
      gMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();

      //On r�cup�re le d�part et l'arriv�e
      final String editDepart = getIntent().getStringExtra("DEPART");
      final String editArrivee = getIntent().getStringExtra("ARRIVEE");

      //Appel de la m�thode asynchrone
      new ItineraireTask(this, gMap, editDepart, editArrivee).execute();
  }
}