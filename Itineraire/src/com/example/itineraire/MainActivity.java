package com.example.itineraire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.itineraire.R;

/**
 * MainActivity
 * @author Ludovic
 */
public class MainActivity extends Activity {
  private EditText editDepart;
  private EditText editArrivee;
  private Button btnRechercher;

  /**
  * {@inheritDoc}
  */
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      //On récupère les composants graphiques
      editDepart = (EditText) findViewById(R.id.editDepart);
      editArrivee = (EditText) findViewById(R.id.editArrivee);
      btnRechercher = (Button) findViewById(R.id.btnSearch);

      btnRechercher.setOnClickListener(new OnClickListener() {
          /**
          * {@inheritDoc}
          */
          @Override
          public void onClick(final View v) {
              if("".equals(editDepart.getText().toString().trim())) {
                  Toast.makeText(MainActivity.this, "Merci de saisir un lieu de départ", Toast.LENGTH_SHORT).show();
              }
              else if("".equals(editArrivee.getText().toString().trim())) {
                  Toast.makeText(MainActivity.this, "Merci de saisir un lieu d'arrivée", Toast.LENGTH_SHORT).show();
              }
              else {
                  //On transmet les données à l'activité suivante
                  final Intent intent = new Intent(MainActivity.this, MapActivity.class);
                  intent.putExtra("DEPART", editDepart.getText().toString().trim());
                  intent.putExtra("ARRIVEE", editArrivee.getText().toString().trim());

                  MainActivity.this.startActivity(intent);
              }                
          }
      });
  }
}