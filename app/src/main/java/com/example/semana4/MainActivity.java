package com.example.semana4;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        configurarCalendario();
        configurarProgressBar();
        configurarRatingBar();
        configurarSeekBars();
    }

    private void configurarCalendario() {
        CalendarView calendarView = findViewById(R.id.calendarView);
        TextView tvFecha = findViewById(R.id.tvFechaSeleccionada);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        tvFecha.setText(getString(R.string.label_fecha_seleccionada, formato.format(Calendar.getInstance().getTime())));

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar calendario = Calendar.getInstance();
            calendario.set(year, month, dayOfMonth);
            tvFecha.setText(getString(R.string.label_fecha_seleccionada, formato.format(calendario.getTime())));
        });
    }

    private void configurarProgressBar() {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView tvProgreso = findViewById(R.id.tvProgreso);
        tvProgreso.setText(getString(R.string.label_progreso, progressBar.getProgress()));
    }

    private void configurarRatingBar() {
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView tvCalificacion = findViewById(R.id.tvCalificacion);
        tvCalificacion.setText(getString(R.string.label_calificacion, ratingBar.getRating()));

        ratingBar.setOnRatingBarChangeListener((rb, rating, fromUser) ->
                tvCalificacion.setText(getString(R.string.label_calificacion, rating)));
    }

    private void configurarSeekBars() {
        SeekBar seekBarContinuo = findViewById(R.id.seekBarContinuo);
        TextView tvSegmento = findViewById(R.id.tvSegmento);
        tvSegmento.setText(getString(R.string.label_barra_segmento, seekBarContinuo.getProgress()));

        seekBarContinuo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSegmento.setText(getString(R.string.label_barra_segmento, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        SeekBar seekBarDiscreto = findViewById(R.id.seekBarDiscreto);
        TextView tvSegmentoDiscreto = findViewById(R.id.tvSegmentoDiscreto);
        tvSegmentoDiscreto.setText(getString(R.string.label_barra_segmento_discreta, seekBarDiscreto.getProgress()));

        seekBarDiscreto.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSegmentoDiscreto.setText(getString(R.string.label_barra_segmento_discreta, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }
}
