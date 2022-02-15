package com.example.animaboton;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Actividad principal para mostrar un botón con dos efectos de animación: trasladar y aparecer
 * @author Rafa
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    private Button btBouncer;
    private int displayWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btBouncer = findViewById(R.id.btBouncer);
        //se obtiene el ancho de la pantalla
        Point punto = new Point();
        getWindowManager().getDefaultDisplay().getSize(punto);
        this.displayWidth = punto.x;

        animaBoton();

        //animación de rebote
        btBouncer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BounceAnimation bounceAnimation = new BounceAnimation(btBouncer);
                bounceAnimation.animate();
            }
        });
    }

    /**
     * Método para producir la animación del boton: trasladar y  aparecer
     */
    private void animaBoton(){
        AnimatorSet animadorBoton = new AnimatorSet();

        //1ª animación, trasladar desde la izquierda
        //800 píxeles menos hasta la posición inicial 0
        ObjectAnimator trasladar =
                ObjectAnimator.ofFloat(btBouncer, "translationX",0,
                        this.displayWidth/2);

        trasladar.setDuration(3000); //duración de 5 segundos


        //2ª animación, fade in de 8 segundos
        ObjectAnimator fade =
                ObjectAnimator.ofFloat(btBouncer,"alpha",0f,1f);
        fade.setDuration(5000);

        animadorBoton.setStartDelay(2000);

        //se verán las dos animaciones a la vez
        animadorBoton.play(trasladar).with(fade);

        //comenzar animación trasladar + fade
        animadorBoton.start();
    }

}
