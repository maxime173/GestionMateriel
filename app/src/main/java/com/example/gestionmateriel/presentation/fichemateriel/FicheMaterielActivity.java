package com.example.gestionmateriel.presentation.fichemateriel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gestionmateriel.R;
import com.example.gestionmateriel.entite.Interface;
import com.example.gestionmateriel.entite.Materiel;
import com.example.gestionmateriel.presentation.ajoutinterface.AjoutInterfaceActivity;
import com.example.gestionmateriel.presentation.ajoutmateriel.AjoutMaterielActivity;
import com.example.gestionmateriel.repository.MaterielRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FicheMaterielActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Materiel materiel;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_materiel);
        setTitle(R.string.title_activity_fiche_materiel);

        MaterielRepository mr = MaterielRepository.getInstance();
        this.materiel  = mr.getById(getIntent().getIntExtra("idMateriel", 0));

        TextView tvLibelle = (TextView) findViewById(R.id.libelleFicheMateriel);
        TextView tvNumSerie = (TextView) findViewById(R.id.numSerieFicheMateriel);
        TextView tvTypeMateriel = (TextView) findViewById(R.id.typeMaterielFicheMateriel);

        tvLibelle.setText(materiel.getLibelle());
        tvNumSerie.setText(materiel.getNumSerie());
        tvTypeMateriel.setText(materiel.getTypeMateriel().getLibelle());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListeInterfaceAdapter(materiel.getInterfaces()) {});

        FloatingActionButton fab = findViewById(R.id.fabAjoutInterface);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { ajouterInterface(); }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fiche_materiel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_fiche_materiel_Ajouter:
                ajouterInterface();
                return true;
            case R.id.menu_fiche_materiel_Modifier:
                modifierMateriel();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void ajouterInterface() {
        Intent i = new Intent(FicheMaterielActivity.this, AjoutInterfaceActivity.class);
        i.putExtra("idMateriel", materiel.getId());
        startActivity(i);
    }

    private void modifierMateriel() {
        Intent i = new Intent(FicheMaterielActivity.this, AjoutMaterielActivity.class);
        i.putExtra("idMateriel", materiel.getId());
        startActivity(i);
    }

    private class ListeInterfaceViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNomInterface;
        private TextView tvTypeInterface;
        private TextView tvMac;
        private TextView tvIpv4;
        private TextView tvIpv6;
        private TextView tvMasque;
        private TextView tvAffectation;
        private Interface interf;

        public View view;

        public ListeInterfaceViewHolder(final View itemView) {
            super(itemView);

            tvNomInterface = (TextView) itemView.findViewById(R.id.nomInterfaceListeInterface);
            tvTypeInterface = (TextView) itemView.findViewById(R.id.typeInterfaceListeInterface);
            tvMac = (TextView) itemView.findViewById(R.id.macListeInterface);
            tvIpv4 = (TextView) itemView.findViewById(R.id.ipv4ListeInterface);
            tvIpv6 = (TextView) itemView.findViewById(R.id.ipv6ListeInterface);
            tvMasque = (TextView) itemView.findViewById(R.id.masqueListeInterface);
            tvAffectation = (TextView) itemView.findViewById(R.id.affectationListeInterface);
            view = itemView;
        }

        //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
        public void bind(Interface i){
            tvNomInterface.setText(i.getNom());
            tvTypeInterface.setText(i.getTypeInterface().getLibelle());
            tvMac.setText(i.getMac());
            tvIpv4.setText(i.getAdresseIp().getIpv4());
            tvIpv6.setText(i.getAdresseIp().getIpv6());
            tvMasque.setText(i.getAdresseIp().getMasque());
            tvAffectation.setText(i.getAdresseIp().getTypeAffectation().getLibelle());
            this.interf = i;
        }
    }

    private  class ListeInterfaceAdapter extends RecyclerView.Adapter<ListeInterfaceViewHolder>
    {
        List<Interface> list;
        public ListeInterfaceAdapter(List<Interface> list) {
            this.list = list;
        }

        @Override
        public ListeInterfaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.liste_interface_layout,viewGroup,false);
            return new ListeInterfaceViewHolder(view);
        }

        public void onBindViewHolder(ListeInterfaceViewHolder myViewHolder, int position) {
            Interface i = list.get(position);
            myViewHolder.bind(i);
        }
        @Override
        public int getItemCount() {
            return list.size();
        }

    }
}
