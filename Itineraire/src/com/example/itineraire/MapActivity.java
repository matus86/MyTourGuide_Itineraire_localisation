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

      //On récupère les composants graphiques
      gMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();

      //On récupère le départ et l'arrivée
      final String editDepart = getIntent().getStringExtra("DEPART");
      final String editArrivee = getIntent().getStringExtra("ARRIVEE");

      //Appel de la méthode asynchrone
      new ItineraireTask(this, gMap, editDepart, editArrivee).execute();
  }
}